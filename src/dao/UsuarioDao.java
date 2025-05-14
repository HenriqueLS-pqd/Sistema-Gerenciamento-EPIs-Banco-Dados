package dao;

import conexao.Conexao;
import modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;

public class UsuarioDao {

    public void inserirUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, senha, perfil) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getPerfil().name());
            stmt.executeUpdate();
            System.out.println("Usuário inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    public ArrayList<Usuario> listarUsuarios() {
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
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
        return lista;
    }

    public void atualizarUsuario(Usuario usuario) {
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
                System.out.println("Usuário não encontrado para atualização.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public void excluirUsuario(int id_usuario) {

        if (EmprestimoDao.existeEmprestimoParaUsuario(id_usuario)) {
            System.out.println("Não é possível deletar o usuário. Existem empréstimos vinculados a ele.");
            return;
        }

        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_usuario);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Usuário excluído com sucesso!");
            } else {
                System.out.println("Usuário não encontrado para exclusão.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }

}
