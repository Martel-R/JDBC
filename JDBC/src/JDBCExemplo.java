import java.sql.*;
import java.util.Scanner;

public class JDBCExemplo {
    public static String nome = null;
    public static String endereco = null;
    public static String email = null;
    public static int contato;
    public static Date date = null;
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args){
        System.out.println("Agenda de Contatos!\n1 - Inserir\n2 - Deletar\n3 - Editar\n4 - Listar contatos");
        System.out.print("Opcao: ");
        int opcao = input.nextInt();
        switch (opcao){
            case "Inserir" || 1:
                System.out.print("Nome: ");
                nome = input.next();
                System.out.print("Contato: ");
                contato = input.nextInt();

        }
    }

    public void insert(){
        try {
            Connection mysql = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/agendaaula?useTimezone=true&serverTimezone=UTC",
                    "root",
                    "1993Martel"
            );

            String sql = "insert into contato " + "(id_contato, nome, endereco, data_nasc, email)" + "values (?, ?, ?, ?, ?)";
            String select = "select * from contato";
            PreparedStatement instucao = mysql.prepareStatement(sql);
            PreparedStatement instucaoSelect = mysql.prepareStatement(select);
            instucao.setInt(1, 8);
            instucao.setString(2, nome);
            instucao.setString(3, endereco);
            instucao.setDate(4, date);
            instucao.setString(5, email);

            ResultSet rs = instucaoSelect.executeQuery();
//            instucao.execute();
//            instucaoSelect.executeQuery();
            if (!instucao.execute()){
                System.out.println("foi");
            } else {
                System.out.println("não foi");
            }
        } catch (SQLException e){
            System.out.println("Ocorreu um erro!");
            throw new RuntimeException(e.getMessage());
        }
    }

    private void validaInfo(){
        if (nome.isEmpty()){

        }
    }
}