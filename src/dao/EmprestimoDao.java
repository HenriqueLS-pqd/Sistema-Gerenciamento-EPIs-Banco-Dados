package dao;

import conexao.Conexao;
import modelo.Emprestimo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar as operações de acesso aos dados da tabela 'emprestimo'.
 */
public class EmprestimoDao {

    private Connection conexao;

    /**
     * Construtor que estabelece a conexão com o banco de dados.
     */
    public EmprestimoDao() {
        conexao = Conexao.conectar();
    }


    /**
     * Cadastra um novo empréstimo no banco de dados.
     *
     * @param emprestimo Objeto contendo os dados do empréstimo.
     */
    public void cadastrarEmprestimo(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo (id_usuario, id_epi, data_retirada, data_prevista_devolucao, confirmacao_retirada) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, emprestimo.getId_usuario());
            stmt.setInt(2, emprestimo.getId_epi());
            stmt.setString(3, emprestimo.getData_retirada());
            stmt.setString(4, emprestimo.getData_prevista_devolucao());
            stmt.setInt(5, emprestimo.getConfirmacao_retirada());
            stmt.executeUpdate();
            System.out.println("Empréstimo cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar empréstimo: " + e.getMessage());
        }
    }

    /**
     * Retorna uma lista com todos os empréstimos cadastrados.
     *
     * @return Lista de objetos Emprestimo.
     */
    public List<Emprestimo> buscarTodosEmprestimos() {
        List<Emprestimo> lista = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo(
                        rs.getInt("id_emprestimo"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_epi"),
                        rs.getString("data_retirada"),
                        rs.getString("data_prevista_devolucao"),
                        rs.getInt("confirmacao_retirada")
                );
                lista.add(emprestimo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar empréstimos: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Atualiza os dados de um empréstimo existente.
     *
     * @param emprestimo Objeto contendo os dados atualizados do empréstimo.
     */
    public void atualizarEmprestimo(Emprestimo emprestimo) {
        String sql = "UPDATE emprestimo SET id_usuario = ?, id_epi = ?, data_retirada = ?, data_prevista_devolucao = ?, confirmacao_retirada = ? WHERE id_emprestimo = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, emprestimo.getId_usuario());
            stmt.setInt(2, emprestimo.getId_epi());
            stmt.setString(3, emprestimo.getData_retirada());
            stmt.setString(4, emprestimo.getData_prevista_devolucao());
            stmt.setInt(5, emprestimo.getConfirmacao_retirada());
            stmt.setInt(6, emprestimo.getId_emprestimo());
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Empréstimo atualizado com sucesso!");
            } else {
                System.out.println("Nenhum empréstimo encontrado para atualizar.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar empréstimo: " + e.getMessage());
        }
    }



    /**
     * Verifica se existe algum empréstimo vinculado a um determinado usuário.
     *
     * @param id_usuario ID do usuário.
     * @return true se existir pelo menos um empréstimo, false caso contrário.
     */
    public static boolean verificarEmprestimoPorUsuario(int id_usuario) {
        String sql = "SELECT COUNT(*) FROM emprestimo WHERE id_usuario = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_usuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar empréstimos do usuário: " + e.getMessage());
        }
        return false;
    }

    /**
     * Verifica se existe algum empréstimo vinculado a um determinado EPI.
     *
     * @param id_epi ID do EPI.
     * @return true se existir pelo menos um empréstimo, false caso contrário.
     */
    public static boolean verificarEmprestimoPorEpi(int id_epi) {
        String sql = "SELECT COUNT(*) FROM emprestimo WHERE id_epi = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_epi);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar empréstimos do EPI: " + e.getMessage());
        }
        return false;
    }
}