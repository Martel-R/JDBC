import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<HTML>");
        out.println("<HEAD><TITLE>Hello World</TITLE></HEAD>");
        out.println("<BODY>");
        out.println("<form method='GET' action='/HelloTomcatWorld/salvar-contato'>");
        out.println("Nome: ");
        out.println("<input type='text' name='nome'/>");
        out.println("Endereco: ");
        out.println("<input type='text' name='endereco'/>");
        out.println("Data Nascimento: ");
        out.println("<input type='text' name='data_nascimento'>");
        out.println("");
        out.println("</BODY></HTML>");

    }
}
