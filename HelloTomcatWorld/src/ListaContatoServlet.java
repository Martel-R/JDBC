import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "ListaContatoServlet")
public class ListaContatoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            Connection mysql;
            Statement stmt = null;
            String sql;
            mysql = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/agendaaula?useTimezone=true&serverTimezone=UTC",
                    "root",
                    "1993Martel");
            sql = "select * from contato;";
//            PreparedStatement instucao = mysql.prepareStatement(sql);
            stmt = mysql.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("id_contato");
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                String data = String.valueOf(rs.getDate("data_nasc"));
                String email = rs.getString("email");
                System.out.print("NÚMERO: " + id +
                        " NOME: " + nome +
                        " ENDEREÇO: " + endereco +
                        " DATA DE NASCIMENTO " + data +
                        " EMAIL: " +email +
                        "\n");
            }
        } catch (SQLException e){
            System.out.println("Ocorreu um erro!");
            throw new RuntimeException(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
