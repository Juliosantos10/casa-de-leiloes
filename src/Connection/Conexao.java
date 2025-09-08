
package Connection;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe responsável por gerenciar a conexão com o banco de dados MySQL.
 * Utiliza o driver JDBC para estabelecer a conexão com o banco cenaflix.
 * 
 * Banco: cenaflix  
 * Usuário: root  
 * Senha: "" (sem senha, pode ser alterada conforme necessário)
 */

public class Conexao {
     /** URL de conexão com o banco de dados MySQL */
    private static final String URL = "jdbc:mysql://localhost:3306/uc11";
     /** Nome de usuário para acessar o banco de dados */
    private static final String USER = "root"; // Altere conforme seu usuário
    /** Senha para acesso ao banco de dados */
    private static final String PASSWORD = "";  // Altere conforme sua senha, "" se não tem senha
    public static Connection getConnection;
    
    

    /**
     * Estabelece e retorna uma conexão com o banco de dados MySQL.
     */
    // Método para obter a conexão
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        try {
            // Ajuste importante: adicionar ?useSSL=false&allowPublicKeyRetrieval=true
            return DriverManager.getConnection(
                URL + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                USER,
                PASSWORD
            );
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
             
        }
       
    

