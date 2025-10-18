package br.com.facilitareabi.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Fábrica de conexões com o banco de dados Oracle.
 * Fornece método estático para obter uma conexão JDBC.
 */
public class ConnectionFactory {
    /**
     * Obtém uma conexão com o banco de dados Oracle.
     *
     * @return Objeto Connection pronto para uso
     * @throws RuntimeException Se a classe do driver não for encontrada
     */
    public static Connection obterConexao(){
        Connection conexao = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conexao = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl",
                    "rm563509", "140607");
        }catch (SQLException erro){
            erro.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conexao;
    }

}