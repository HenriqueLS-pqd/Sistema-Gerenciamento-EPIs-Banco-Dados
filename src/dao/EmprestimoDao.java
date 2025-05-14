package dao;

import conexao.Conexao;
import modelo.Emprestimo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDao {

    private Connection conexao;

    public EmprestimoDao() {
        conexao = Conexao.conectar();
    }

    public void inserirEmprestimo(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo (id_usuario, id_epi, data_retirada, data_prevista_devolucao, confirmacao_retirada) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, emprestimo.getId_usuario());
            stmt.setInt(2, emprestimo.getId_epi());
            stmt.setString(3, emprestimo.getData_retirada());
            stmt.setString(4, emprestimo.getData_prevista_devolucao());
            stmt.setInt(5, emprestimo.getConfirmacao_retirada());
            stmt.executeUpdate();
            System.out.println("Empréstimo inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir Empréstimo: " + e.getMessage());
        }
    }

    public List<Emprestimo> listarEmprestimos() {
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
            System.out.println("Erro ao listar Empréstimos: " + e.getMessage());
        }
        return lista;
    }

    public void atualizarEmprestimo(Emprestimo emprestimo) {
        String sql = "UPDATE emprestimo SET id_usuario = ?, id_epi = ?, data_retirada = ?, data_prevista_devolucao = ?, confirmacao_retirada = ? WHERE id_emprestimo = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, emprestimo.getId_usuario());
            stmt.setInt(2, emprestimo.getId_epi());
            stmt.setString(3, emprestimo.getData_retirada());
            stmt.setString(4, emprestimo.getData_prevista_devolucao());
            stmt.setInt(5, emprestimo.getConfirmacao_retirada());
            stmt.setInt(6, emprestimo.getId_emprestimo());
            stmt.executeUpdate();
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Empréstimo atualizado com sucesso!");
            } else {
                System.out.println("Empréstimo não encontrado para atualização.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir Empréstimo: " + e.getMessage());
        }
    }

    public void excluirEmprestimo(int id_emprestimo) {
        if (DevolucaoDao.existeDevolucaoParaEmprestimo(id_emprestimo)) {
            System.out.println("Não é possível excluir o empréstimo. Já existe uma devolução vinculada a ele.");
            return;
        }

        String sql = "DELETE FROM emprestimo WHERE id_emprestimo = ?";
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id_emprestimo);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Empréstimo excluído com sucesso!");
            } else {
                System.out.println("Empréstimo não encontrado para exclusão.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir Empréstimo: " + e.getMessage());
        }
    }

    public static boolean existeEmprestimoParaUsuario(int id_usuario) {
        String sql = "SELECT COUNT(*) FROM emprestimo WHERE id_usuario = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_usuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar se o usuário está vinculado a um empréstimo: " + e.getMessage());
        }
        return false;
    }

    public static boolean existeEmprestimoParaEpi(int id_epi) {
        String sql = "SELECT COUNT(*) FROM emprestimo WHERE id_epi = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_epi);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar se a EPI está vinculada a um empréstimo: " + e.getMessage());
        }
        return false;
    }
}
