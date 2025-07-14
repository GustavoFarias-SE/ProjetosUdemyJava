package skx.coding.projetocadastro.Codigo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Tables {
    public static void main(String[] args){
        try (Connection conexao = ConexaoDB.conectarGenerico("jdbc:mysql://127.0.0.1:3306/javaprojects", "Gamer", "gustavo1967");
             Statement stm = conexao.createStatement()){

            String comandosSql = "CREATE TABLE produtos (" +
                    "id_produto INTEGER PRIMARY KEY AUTO_INCREMENT,"+
                    "nome_produto TEXT NOT NULL," +
                    "quantidade INTEGER," +
                    "preco REAL," +
                    "status TEXT" +
                    ");";
            System.out.println(comandosSql);

            stm.execute(comandosSql);

            System.out.println("Tabela criada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao criar a tabela: " + e.getMessage());
            e.printStackTrace();
        }

    }
}

