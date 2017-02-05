import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;

/**
 * Created by RonOS on 16-12-2016.
 */
public class MySql {

    Connection connection;

    /**
     * The mysql object is responsible for facilitating a connection to the Mysql database and
     * allows for some generic methods such as select queries and insertions.
     * @param address The adress of the database engine
     * @param port The port of the database engine
     * @param database The database to connect to
     * @param username Database username
     * @param password Database password
     */
    public MySql(String address, int port, String database, String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + address + "/" + database, password, username);
        }

        catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }

    /**
     * This method allows the programmer to select entries from a database
     * @param query The select query to be executed.
     * @return A resultset is returned containing the result(s) of the query
     */
    public ResultSet getSelectFromQuery(String query) {
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(query);
        }

        catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    /**
     * This method allows the deletion of items from a table
     * @param table The table from which items need to be deleted
     * @param condition A condition allow the filtering of items such as a where clause
     * @param deleteAll The option to delete everything froma a table
     */
    public void delete(String table, String condition, Boolean deleteAll) {
        try {
            Statement stmt = connection.createStatement();
            String deleteString = "DELETE FROM " + table +  " " + condition;
            stmt.executeUpdate(deleteString);
        }

        catch (Exception ex) {
            System.out.println(ex);
        }

    }

    /**
     * This will execute a given insert query
     * @param query The insert to be made
     */
    public void insertQuery(String query) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);


        }

        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }


}
