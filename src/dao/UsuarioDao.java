package dao;

import conexao.Conexao;
import modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe responsável pelas operações de persistência dos dados da tabela 'usuario'.
 */
public class UsuarioDao {

    /**
     * Cadastra um novo usuário no banco de dados.
     *
     * @param usuario Objeto Usuario a ser cadastrado.
     */
    public void cadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, senha, perfil) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getPerfil().name());
            stmt.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    /**
     * Retorna todos os usuários cadastrados no banco.
     *
     * @return Lista de objetos Usuario.
     */
    public ArrayList<Usuario> buscarTodosUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        Usuario.Perfil.valueOf(rs.getString("perfil").toUpperCase()),
                        rs.getString("senha")
                );
                lista.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuários: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Atualiza os dados de um usuário existente.
     *
     * @param usuario Objeto Usuario com os dados atualizados.
     */
    public void atualizarDadosUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, perfil = ? WHERE id_usuario = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getPerfil().name());
            stmt.setInt(5, usuario.getId_usuario());
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Usuário atualizado com sucesso!");
            } else {
                System.out.println("Nenhum usuário encontrado para atualização.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    /**
     * Remove um usuário do banco de dados, desde que ele não tenha empréstimos vinculados.
     *
     * @param id_usuario ID do usuário a ser removido.
     */
    public void removerUsuario(int id_usuario) {
        if (EmprestimoDao.verificarEmprestimoPorUsuario(id_usuario)) {
            System.out.println("Não é possível remover o usuário. Existem empréstimos vinculados.");
            return;
        }

        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_usuario);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Usuário removido com sucesso!");
            } else {
                System.out.println("Nenhum usuário encontrado para remoção.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover usuário: " + e.getMessage());
        }
    }
}
