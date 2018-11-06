import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "SalvarContatoServlet")
public class SalvarContatoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        try {
            Connection mysql = DriverManager.getConnection("jdbc:mysql://localhost/agendaaula", "root", "1993Martel");
            Statement instrucao = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
