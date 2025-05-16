package dao;

import conexao.Conexao;
import modelo.Devolucao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por acessar e manipular os dados da tabela 'devolucao' no banco de dados.
 */
public class DevolucaoDao {

    private Connection conexao;

    /**
     * Construtor que estabelece a conexão com o banco de dados.
     */
    public DevolucaoDao() {
        conexao = Conexao.conectar();
    }

    /**
     * Adiciona uma nova devolução no banco de dados.
     *
     * @param devolucao Objeto Devolucao contendo os dados a serem inseridos.
     */
    public void adicionarDevolucao(Devolucao devolucao) {
        String sql = "INSERT INTO devolucao (id_emprestimo, data_devolucao) VALUES (?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, devolucao.getId_emprestimo());
            stmt.setString(2, devolucao.getData_devolucao());
            stmt.executeUpdate();
            System.out.println("Devolução adicionada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar devolução: " + e.getMessage());
        }
    }

    /**
     * Retorna uma lista com todas as devoluções cadastradas no banco de dados.
     *
     * @return Lista de objetos Devolucao.
     */
    public List<Devolucao> buscarTodasDevolucoes() {
        List<Devolucao> devolucoes = new ArrayList<>();
        String sql = "SELECT * FROM devolucao";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Devolucao devolucao = new Devolucao(
                        rs.getInt("id_devolucao"),
                        rs.getInt("id_emprestimo"),
                        rs.getString("data_devolucao")
                );
                devolucoes.add(devolucao);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar devoluções: " + e.getMessage());
        }
        return devolucoes;
    }

    /**
     * Atualiza os dados de uma devolução existente no banco de dados.
     *
     * @param devolucao Objeto Devolucao com os novos dados.
     */
    public void editarDevolucao(Devolucao devolucao) {
        String sql = "UPDATE devolucao SET id_emprestimo = ?, data_devolucao = ? WHERE id_devolucao = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, devolucao.getId_emprestimo());
            stmt.setString(2, devolucao.getData_devolucao());
            stmt.setInt(3, devolucao.getId_devolucao());
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Devolução atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma devolução encontrada para atualizar.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar devolução: " + e.getMessage());
        }
    }

}