package mercado.teste;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {
    public static final String url = "jdbc:mysql://localhost:3306/mercado";
    public static final String user = "root";
    public static final String password = "";

    public static Connection getConexao() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }
}