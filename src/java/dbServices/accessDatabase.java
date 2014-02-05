/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author paulo
 */
@WebService(serviceName = "accessDatabase")
public class accessDatabase {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "consultInfo")
    public void hello(@WebParam(name = "name") String txt) {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:/home/paulo/Projetos/Prod-Announcer---Web/prod_announcer/db.sqlite");
            statement = connection.createStatement();
            resultSet = statement
                    .executeQuery("select nome_loja from loja_fisica_loja\n"
                    + "where id = '2'");
            while (resultSet.next()) {
                System.out.println("Nome da loja:"
                        + resultSet.getString("nome_loja"));
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
            }
        }
    }
}
