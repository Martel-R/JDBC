import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCExemplo {
    public static void main(String[] args){
        String conexao = "valor";
        try {
            Connection mysql = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/agendaaula?useTimezone=true&serverTimezone=UTC",
                    "root",
                    "1993Martel"
            );
            System.out.println("Conectado!");
        } catch (SQLException e){
            System.out.println("Ocorreu um erro!");
            throw new RuntimeException(e.getMessage());
        }
    }
}