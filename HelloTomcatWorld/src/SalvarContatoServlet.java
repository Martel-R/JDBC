import com.mysql.jdbc.Driver;
import conexao.MySql;

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
        request.getRequestDispatcher("/WEB-INF/criar-contato.jsp").forward(request,response);
        Contato insere = null;

        insere.setId(Long.valueOf(request.getParameter("contato")));
        insere.setNome(request.getParameter("nome"));
        insere.setEndereco(request.getParameter("endereco"));
        insere.setEmail(request.getParameter("email"));

        MySql db = new MySql();

        String sql = "insert into contato (id_contato, nome, endereco, data_nasc, email) values (?, ?, ?, ?, ?)";

        Connection connection= db.conecta();

        try {
            PreparedStatement instrucao = connection.prepareCall(sql);
            instrucao.setLong(1, insere.getId());
            instrucao.setString(2, insere.getNome());
            instrucao.setString(3,insere.getEndereco());
            instrucao.setDate(4, new Date(insere.getDataNascimento().getTimeInMillis()));
            instrucao.setString(5, insere.getEmail());
            if (!instrucao.execute()){
                System.out.println("FOI");
            } else {
                System.out.println("cagou tudo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
