
package Connection;

/**
 *
 * @author julio
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de teste para verificar a conexão com o banco de dados MySQL.
 * Utiliza o driver JDBC para se conectar ao banco de dados cenaflix.
 */

public class Testeconexao{
//    Setando as constantes
    /** URL de conexão com o banco de dados */
    private static final String URL = "jdbc:mysql://localhost:3306/uc11";
    /** Nome de usuário do banco de dados */
    private static final String USER = "root";
    /** Senha do banco de dados (vazia por padrão) */
    private static final String PASS = "";

//    Conexão
    public static Connection getConection() throws SQLException {
        try{
              // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver JDBC carregado");
            
            // Retorna a conexão estabelecida
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e){
              // Se o driver não for encontrado, lança uma exceção SQL
            throw new SQLException("Driver não encontrado: " + e.getMessage());
        }

    }
    
    /**
     * Método principal para testar a conexão com o banco de dados.
     * Caso a conexão seja bem-sucedida, exibe uma mensagem de sucesso.
     */

    public static void main(String[] args) {
        try (Connection con  = Testeconexao.getConection()){
            System.out.println("Conexao bem sucedida!");
        } catch (SQLException e){
            System.err.println("Erro na conexaoTESTE: " + e.getMessage());
        }
    }

}