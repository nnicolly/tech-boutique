package br.com.nicolly.lojaroupasfx.dao;

import br.com.nicolly.lojaroupasfx.model.Produto;
import br.com.nicolly.lojaroupasfx.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void salvar(Produto p) {
        String sql = "INSERT INTO produto (nome, tamanho, cor, preco, estoque) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getTamanho());
            stmt.setString(3, p.getCor());
            stmt.setDouble(4, p.getPreco());
            stmt.setInt(5, p.getEstoque());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Produto p) {
        String sql = "UPDATE produto SET nome=?, tamanho=?, cor=?, preco=?, estoque=? WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getTamanho());
            stmt.setString(3, p.getCor());
            stmt.setDouble(4, p.getPreco());
            stmt.setInt(5, p.getEstoque());
            stmt.setInt(6, p.getId());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM produto WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Produto> listarTodos() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto ORDER BY id";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setTamanho(rs.getString("tamanho"));
                p.setCor(rs.getString("cor"));
                p.setPreco(rs.getDouble("preco"));
                p.setEstoque(rs.getInt("estoque"));
                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Produto> listarPorFiltros(String nome, String tamanho, String cor) {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE 1=1";

        if (nome != null && !nome.isEmpty()) sql += " AND nome ILIKE ?";
        if (tamanho != null && !tamanho.isEmpty()) sql += " AND tamanho ILIKE ?";
        if (cor != null && !cor.isEmpty()) sql += " AND cor ILIKE ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int index = 1;

            if (nome != null && !nome.isEmpty()) stmt.setString(index++, "%" + nome + "%");
            if (tamanho != null && !tamanho.isEmpty()) stmt.setString(index++, "%" + tamanho + "%");
            if (cor != null && !cor.isEmpty()) stmt.setString(index++, "%" + cor + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setTamanho(rs.getString("tamanho"));
                p.setCor(rs.getString("cor"));
                p.setPreco(rs.getDouble("preco"));
                p.setEstoque(rs.getInt("estoque"));
                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
