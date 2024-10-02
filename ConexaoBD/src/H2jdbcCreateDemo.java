import java.sql.Connection;
import java.sql.Statement;

public class H2jdbcCreateDemo {

    public static void main(String[] args) {
        Statement statement = null;
        try{
            Connection conn = ConexaoDB.getConexao();
            System.out.println("Conectou? "+!conn.isClosed());

            statement = conn.createStatement();

            String sql =  "CREATE TABLE   REGISTRO " +
                    "(id INTEGER not NULL, " +
                    " nome VARCHAR(255), " +
                    " sobrenome VARCHAR(255), " +
                    " idade INTEGER, " +
                    " PRIMARY KEY ( id ))";

            statement.execute(sql);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(statement != null) {
                try {
                    statement.close();
                }catch (Exception e) {
                    System.out.println("já fechado");
                }
            }

        }
    }
}