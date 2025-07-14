package skx.coding.projetocadastro.Codigo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private final Connection CONEXAO_DB;

    public ProdutoDAO(Connection conexao){
        CONEXAO_DB = conexao;

    }

    //Método Inserir
    public void inserir(Produto produto){
        String sql = "INSERT INTO produtos (nome_produto, quantidade, preco, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stm = CONEXAO_DB.prepareStatement(sql)){
            stm.setString(1, produto.getNome());
            stm.setInt(2, produto.getQuantidade());
            stm.setDouble(3, produto.getPreco());
            stm.setString(4, produto.getStatus());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir o produto: " + e.getMessage());
        }
    }

    //Método Excluir todos :O
    public void excluirTudo(){
        String sql = "DELETE FROM produtos";
        try (PreparedStatement stm = CONEXAO_DB.prepareStatement(sql)){
            stm.executeUpdate();
        } catch (SQLException e){
            System.err.println("Erro ao excluir todos os produtos " + e.getMessage());
        }
    }

    //Método para consultar um id
    public Produto consultarPorId(int id) {
        String sql = "SELECT * FROM produtos WHERE id_produto = ?";
        try (PreparedStatement stm = CONEXAO_DB.prepareStatement(sql)){
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()){
                if (rs.next()){
                    Produto produto = new Produto();
                    produto.setId(rs.getInt("id_produto"));
                    produto.setNome(rs.getString("nome_produto"));
                    produto.setQuantidade(rs.getInt("quantidade"));
                    produto.setPreco(rs.getDouble("preco"));
                    produto.setStatus(rs.getString("status"));
                    return produto;
                }
            }
        } catch (SQLException e){
            System.err.println("Erro ao consultar o produto por id " + e.getMessage());
        }
        return null;
    }

    //Método para atualizar um produto
    public void atualizar(Produto produto){
        String sql = "UPDATE produtos SET nome_produto = ?, quantidade = ?, preco = ?, status = ? WHERE id_produto = ?";
        try (PreparedStatement stm = CONEXAO_DB.prepareStatement(sql)){
            stm.setString(1, produto.getNome());
            stm.setInt(2, produto.getQuantidade());
            stm.setDouble(3, produto.getPreco());
            stm.setString(4, produto.getStatus());
            stm.setInt(5, produto.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o produto: " + e.getMessage());
        }
    }

    //Método para excluir um produto pelo seu id
    public void excluir(int id){
        String sql = "DELETE FROM produtos WHERE id_produto = ?";
        try (PreparedStatement stm = CONEXAO_DB.prepareStatement(sql)){
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException e){
            System.err.println("Erro ao excluir um produto: " + e.getMessage());
        }
    }

    //Método para listar todos os produtos do banco de dados
    public List<Produto> listarTudo(){
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (PreparedStatement stm = CONEXAO_DB.prepareStatement(sql)){
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Produto produto = new Produto();
                produto.setId(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome_produto"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setStatus(rs.getString("status"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        }
        return produtos;
    }
}

