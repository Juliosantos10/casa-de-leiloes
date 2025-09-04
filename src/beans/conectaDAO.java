package beans;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

/**
 *
 * @author Adm
 */
public class conectaDAO {
    
     private static final String URL = "jdbc:mysql://localhost:3306/seu_banco?useSSL=false"; // desabilita SSL
    private static final String USER = "seu_usuario";
    private static final String PASS = "sua_senha";

    
    public Connection connectDB() {
        Connection conn = null;
        try {
            // Carrega o driver JDBC
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Driver JDBC carregado");
            System.out.println("Conexão com o banco realizada com sucesso!");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver JDBC não encontrado: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar no banco: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    // Método para fechar a conexão de forma segura
    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexão encerrada com sucesso!");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
}
