import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class JDBCExemplo {
    private static Connection mysql;
    private static Statement stmt = null;
    private static String nome = null;
    private static String endereco = null;
    private static String email = null;
    private static BigDecimal contato;
    private static Date date = null;
    private static final Scanner input = new Scanner(System.in);

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
            case 1: // INSERIR
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
                insert();
                break;
            case 2: // DELETAR
                System.out.print("Editar contato\n1 - Listar contatos\n2 - Inserir número para exclusão");
                int b = input.nextInt();
                switch (b){
                    case 1:
                        listaUsuario();
                        int id;
                        System.out.print("Digite o numero para exclusão de cadastro: ");
                        id = input.nextInt();
                        delete(id);
                }
                break;
            case 3: // ATUALIZAR
                System.out.print("Editar contato\n1 - Listar contatos\n2 - Inserir número para atualização");
                int a = input.nextInt();
                switch (a){
                    case 1:
                        listaUsuario();
                        int id, alterarOpcao;
                        String valor=null;
                        System.out.print("Digite o numero para alteração de cadastro: ");
                        id = input.nextInt();
                        System.out.println("O que você quer alterar? \n1 - Número\n2 - Nome\n3 - Endereço\n4 - Data de nascimento\n5 - Email");
                        alterarOpcao = input.nextInt();
                        switch (alterarOpcao){
                            case 1: //Número
                                System.out.println("Qual o novo número: ");
                                valor = input.next();
                                update(id,"id_contato", valor);
                                break;
                            case 2: // Nome
                                System.out.println("Qual o novo nome: ");
                                nome = input.next();
                                update(id,"nome", nome);
                                break;
                            case 3: // Endereço
                                System.out.println("Qual o novo Endereço: ");
                                endereco= input.next();
                                update(id,"endereco", endereco);
                                break;
                            case 4: // Data de Nascimento
                                int y=0,m = 0,d=0;
                                System.out.println("Qual o novo Data de nascimento\nAno: ");
                                y=input.nextInt();
                                System.out.print("Mes: ");
                                m=input.nextInt();
                                System.out.print("Dia: ");
                                d=input.nextInt();
                                valor = String.valueOf(y)+"-"+String.valueOf(m)+"-"+String.valueOf(d);
                                update(id,"id_contato", valor);
                                break;
                            case 5: // EMAIL
                                System.out.println("Qual o novo email: ");
                                email = input.next();
                                update(id,"email", valor);
                                break;

                            default:
                                System.out.println("Opção invalida, tente novamente ");
                                System.out.println("O que você quer alterar? \n1 - Número\n2 - Nome\n3 - Endereço\n4 - Data de nascimento\n5 - Email");


                        }
                        break;
                    case 2:

                }

                break;
            case 4: // LISTAR
                listaUsuario();
                break;
            default:


        }
    }

    private static void listaUsuario() {

        try {
            String sql = "select * from contato;";
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
        return;
    }

    private static void insert(){
        int pk = Integer.valueOf(String.valueOf(contato));
        try {

            String sql = "insert into contato " +
                         "(id_contato, nome, endereco, data_nasc, email)" +
                         "values (?, ?, ?, ?, ?)";
            String select = "select * from contato";
            PreparedStatement instucao = mysql.prepareStatement(sql);
            instucao.setInt(1, pk);
            instucao.setString(2, nome);
            instucao.setString(3, endereco);
            instucao.setDate(4, date);
            instucao.setString(5, email);

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

    private static void update(int id, String atributo, String valor){

        try {
            String sql = "UPDATE contato SET "+atributo+" = ? WHERE id_contato = "+id+";";
            PreparedStatement instucao = mysql.prepareStatement(sql);
            instucao.setString(1,valor);
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

    public static void delete(int id){

        String sql = "DELETE FROM contatos WHERE id = ?";
        try {

            PreparedStatement instrucao = mysql.prepareStatement(sql);
            instrucao.setInt(1, id);
            instrucao.execute();
            System.out.println("Deletado");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}