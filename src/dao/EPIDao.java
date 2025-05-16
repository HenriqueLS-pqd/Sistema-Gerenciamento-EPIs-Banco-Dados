package dao;

import conexao.Conexao;
import modelo.EPI;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe responsável pelo acesso e manipulação dos dados da tabela 'epi' no banco de dados.
 */
public class EPIDao {

    /**
     * Cadastra um novo EPI no banco de dados.
     *
     * @param epi Objeto EPI a ser cadastrado.
     */
    public void cadastrarEPI(EPI epi) {
        String sql = "INSERT INTO epi (nome, validade, quantidade) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, epi.getNome());
            stmt.setString(2, epi.getValidade());
            stmt.setInt(3, epi.getQuantidade());
            stmt.executeUpdate();
            System.out.println("EPI cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar EPI: " + e.getMessage());
        }
    }

    /**
     * Retorna uma lista com todos os EPIs cadastrados no banco.
     *
     * @return Lista de objetos EPI.
     */
    public ArrayList<EPI> buscarTodosEPIs() {
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
            System.out.println("Erro ao buscar EPIs: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Atualiza os dados de um EPI existente no banco de dados.
     *
     * @param epi Objeto EPI com os dados atualizados.
     */
    public void atualizarDadosEPI(EPI epi) {
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
                System.out.println("Nenhum EPI encontrado para atualização.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar EPI: " + e.getMessage());
        }
    }

    /**
     * Remove um EPI do banco de dados, desde que não haja empréstimos vinculados a ele.
     *
     * @param id_epi ID do EPI a ser removido.
     */
    public void removerEPI(int id_epi) {
        if (EmprestimoDao.verificarEmprestimoPorEpi(id_epi)) {
            System.out.println("Não é possível remover o EPI. Existem empréstimos vinculados.");
            return;
        }

        String sql = "DELETE FROM epi WHERE id_epi = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_epi);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("EPI removido com sucesso!");
            } else {
                System.out.println("Nenhum EPI encontrado para remoção.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover EPI: " + e.getMessage());
        }
    }
}