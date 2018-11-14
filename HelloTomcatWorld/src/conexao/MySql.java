package conexao;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySql {
    private Connection mysql;
    public Connection conecta(){
        try {
            DriverManager.registerDriver(new Driver());
            mysql = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/agendaaula?useTimezone=true&serverTimezone=UTC",
                    "root",
                    "1993Martel");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mysql;
    }
}
