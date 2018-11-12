import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "SalvarContatoServlet", urlPatterns = "/salvar")
public class SalvarContatoServlet extends HttpServlet {
    private  String nome = null;
    private  String endereco = null;
    private  String email = null;
    private  int contato;
    private  Date date = null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        response.setContentType("text/html");
        nome = request.getParameter("nome");
        endereco = request.getParameter("endereco");
        email = request.getParameter("email");
        contato = request.getIntHeader("contato");
        date = request.getDateHeader("data_nascimento");

        try {
            Connection mysql = DriverManager.getConnection("jdbc:mysql://localhost/agendaaula", "root", "1993Martel");
            String sql = "insert into contato " +
                    "(id_contato, nome, endereco, data_nasc, email) values (?, ?, ?, ?, ?)";
            PreparedStatement instucao = mysql.prepareStatement(sql);
            instucao.setInt(1, contato);
            instucao.setString(2, nome);
            instucao.setString(3, endereco);
            instucao.setDate(4, date);
            instucao.setString(5, email);
            if (!instucao.execute()){
                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
