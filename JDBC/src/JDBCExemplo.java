import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class JDBCExemplo {
    private static Connection mysql;
    static Statement stmt = null;
    public static String nome = null;
    public static String endereco = null;
    public static String email = null;
    public static BigDecimal contato;
    public static Date date = null;
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
            try {
                mysql = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/agendaaula?useTimezone=true&serverTimezone=UTC",
                        "root",
                        "1993Martel"
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }

        System.out.println("Agenda de Contatos!\n1 - Inserir\n2 - Deletar\n3 - Editar\n4 - Listar contatos");
        System.out.print("Opcao: ");
        int opcao = input.nextInt();
        switch (opcao){
            case 1:
                int ano=0,mes = 0,dia=0;
                System.out.print("Nome: ");
                nome = input.next();
                System.out.print("Contato: ");
                contato = input.nextBigDecimal();
                System.out.print("Endereco: ");
                endereco = input.next();
                System.out.print("Email: ");
                email = input.next();
                System.out.print("Data de Nascimento\nAno: ");
                ano=input.nextInt();
                System.out.print("Mes: ");
                mes=input.nextInt();
                System.out.print("Dia: ");
                dia=input.nextInt();
                date = new Date(ano,mes,dia);
                validaInfo();
                insert();
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:
                listaUsuario();
                break;
                default:


        }
    }

    private static void listaUsuario() {

        try {
            String sql = "select * from contato";
            PreparedStatement instucao = mysql.prepareStatement(sql);
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

    private static void listarUmUsuario(int id){
        try {
            String sql = "select * from contato where id_contato = " + id;
            PreparedStatement instucao = mysql.prepareStatement(sql);
            stmt = mysql.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                id = rs.getInt("id_contato");
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

    public static void insert(){
        int pk = Integer.valueOf(String.valueOf(contato));
        try {

            String sql = "insert into contato " + "(id_contato, nome, endereco, data_nasc, email)" + "values (?, ?, ?, ?, ?)";
            String select = "select * from contato";
            PreparedStatement instucao = mysql.prepareStatement(sql);
            PreparedStatement instucaoSelect = mysql.prepareStatement(select);
            instucao.setInt(1, pk);
            instucao.setString(2, nome);
            instucao.setString(3, endereco);
            instucao.setDate(4, date);
            instucao.setString(5, email);

//            instucao.execute();
//            instucaoSelect.executeQuery();
            if (!instucao.execute()){
                ResultSet rs = instucao.getResultSet();
                System.out.println("foi "+contato.intValue()+ " " + date);
                System.out.println(rs);
            } else {
                System.out.println("não foi");
            }
        } catch (SQLException e){
            System.out.println("Ocorreu um erro!");
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void update(int id, int idnovo){

        int pk = Integer.valueOf(String.valueOf(contato));
        try {
            String sql = "UPDATE contato SET id_contato = "+idnovo+" WHERE id_contato = "+id+";";
            PreparedStatement instucao = mysql.prepareStatement(sql);
            instucao.setInt(1, pk);
            instucao.setString(2, nome);
            instucao.setString(3, endereco);
            instucao.setDate(4, date);
            instucao.setString(5, email);

//            instucao.execute();
//            instucaoSelect.executeQuery();
            if (!instucao.execute()){
                ResultSet rs = instucao.getResultSet();
                System.out.println("foi "+contato.intValue()+ " " + date);
                System.out.println(rs);
            } else {
                System.out.println("não foi");
            }
        } catch (SQLException e){
            System.out.println("Ocorreu um erro!");
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void validaInfo(){
        int pk = Integer.valueOf(contato.toString());
        if (nome.isEmpty()){
            System.out.print("Insira um nome: ");
            nome = input.next();
            validaInfo();
        }
        if (pk < 10000000){
            System.out.print("Contato: ");
            contato = input.nextBigDecimal();
            validaInfo();
        }
        return;
    }
}