package skx.coding.projetocadastro.Codigo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB{

    //public static final String url_jdbc = "jdbc:mysql://127.0.0.1:3306/javaprojects";
    //public static final String user_jdbc = "Gamer";
    //public static final String password_jdbc = "gustavo1967";


   /* public static Connection conectar(){
        try {
            return DriverManager.getConnection(url_jdbc);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
*/

    public static Connection conectarGenerico(String url, String usuario, String senha){
        try {
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}




