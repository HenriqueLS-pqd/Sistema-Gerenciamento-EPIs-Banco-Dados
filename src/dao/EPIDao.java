package dao;

import conexao.Conexao;
import modelo.EPI;
import java.sql.*;
import java.util.ArrayList;

public class EPIDao {

    public void inserirEPI(EPI epi) {
        String sql = "INSERT INTO epi (nome, validade, quantidade) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, epi.getNome());
            stmt.setString(2, epi.getValidade());
            stmt.setInt(3, epi.getQuantidade());
            stmt.executeUpdate();
            System.out.println("EPI inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir EPI: " + e.getMessage());
        }
    }

    public ArrayList<EPI> listarEPIs() {
        ArrayList<EPI> lista = new ArrayList<>();
        String sql = "SELECT * FROM epi";
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                EPI epi = new EPI(
                        rs.getInt("id_epi"),
                        rs.getString("nome"),
                        rs.getString("validade"),
                        rs.getInt("quantidade")
                );
                lista.add(epi);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar EPIs: " + e.getMessage());
        }
        return lista;
    }

    public void atualizarEPI(EPI epi) {
        String sql = "UPDATE epi SET nome = ?, validade = ?, quantidade = ? WHERE id_epi = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, epi.getNome());
            stmt.setString(2, epi.getValidade());
            stmt.setInt(3, epi.getQuantidade());
            stmt.setInt(4, epi.getId_epi());
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("EPI atualizado com sucesso!");
            } else {
                System.out.println("EPI não encontrado para atualização.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar EPI: " + e.getMessage());
        }
    }

    public void excluirEPI(int id_epi) {
        if (EmprestimoDao.existeEmprestimoParaEpi(id_epi)) {
            System.out.println("Não é possível excluir a EPI. Existem empréstimos vinculados a ela.");
            return;
        }

        String sql = "DELETE FROM epi WHERE id_epi = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id_epi);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("EPI excluído com sucesso!");
            } else {
                System.out.println("EPI não encontrado para exclusão.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir EPI: " + e.getMessage());
        }
    }
}
