package skx.coding.projetocadastro.Codigo;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args){
        try (Connection conexao = ConexaoDB.conectarGenerico("jdbc:mysql://127.0.0.1:3306/javaprojects", "Gamer", "gustavo1967")){
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);

            mostrarProduto(produtoDAO);

            Produto notebook = new Produto("Notebook", 10, 1500, "Em estoque");
            Produto teclado = new Produto("Teclado redDrangon", 5, 50, "Em estoque");
            Produto mouse = new Produto("Mouse", 2, 20, "Estoque baixo");

            produtoDAO.inserir(notebook);
            produtoDAO.inserir(teclado);
            produtoDAO.inserir(mouse);

            //Listar todos os produtos
            mostrarProduto(produtoDAO);

            //Consulta por id
            Produto produtoConsultado = produtoDAO.consultarPorId(1);
            if (produtoConsultado != null){
                System.out.println("Produto encontrado: " + produtoDAO.consultarPorId(1));
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    //método para listar produto
    private  static void mostrarProduto(ProdutoDAO produtoDAO){
        List<Produto> todosProdutos = produtoDAO.listarTudo();
        if (todosProdutos.isEmpty()){
            System.out.println("Nenhum produto encontrado");
        } else {
            System.out.println("Lista de produtos");
            for (Produto p : todosProdutos){
                System.out.println(p.getId() + ": " + p.getNome() + " - " + p.getPreco());
            }
        }
    }
}
