import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistroDB {

    private Registro id;

    public void inserir(Registro registro) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConexaoDB.getConexao();

            String sql = "INSERT INTO REGISTRO " +
                    "(id, nome, sobrenome, idade) "
                    + "VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setInt(1, registro.getId());
            preparedStatement.setString(2,  registro.getNome());
            preparedStatement.setString(3, registro.getSobreNome());
            preparedStatement.setInt(4, registro.getIdade());

            int linhas = preparedStatement.executeUpdate();

            System.out.println("Linhas alteradas: "+linhas);

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                }catch (Exception e){
                    System.out.println("Já fechado");
                }
            }
        }
    }

    public void remover (int id) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            conn = ConexaoDB.getConexao();
            String sql = "DELETE FROM REGISTRO WHERE ID = ?";
            preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            int linhas = preparedStatement.executeUpdate();

            System.out.println("Linhas alteradas: "+linhas);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement != null) {
                try{
                    preparedStatement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void selectPorId (int id) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            conn = ConexaoDB.getConexao();
            String sql = "SELECT * FROM REGISTRO WHERE ID = ?";

            preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String nome = resultSet.getString("nome");
                System.out.println("Registro encontrado: ID = " + id + ", Nome = " + nome);
            }

            //System.out.println("Linhas alteradas: "+ linhas);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (resultSet  != null) {
                try{
                    resultSet .close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Registro> getRegistros() {
        List<Registro> registros = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            connection = ConexaoDB.getConexao();

            String sql = "SELECT id, nome, sobrenome, idade FROM REGISTRO";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                // Retrieve by column name
                int id = resultSet.getInt("id");
                int idade = resultSet.getInt("idade");
                String nome = resultSet.getString("nome");
                String sobrenome = resultSet.getString("sobrenome");

                registros.add(new Registro(id, nome, sobrenome, idade));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                }catch (Exception e){
                    System.out.println("Já fechado");
                }
            }

            if(resultSet != null) {
                try {
                    resultSet.close();
                }catch (Exception e){
                    System.out.println("Já fechado");
                }
            }
        }

        return  registros;
    }


}
