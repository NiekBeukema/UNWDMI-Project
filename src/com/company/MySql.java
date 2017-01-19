package com.company;
import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;

/**
 * Created by RonOS on 16-12-2016.
 */
public class MySql {

    Connection connection;

    public MySql(String address, int port, String database, String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + address + "/" + database, password, username);
        }

        catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }

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

    public String getStringFromQuery(String query, int index) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(query);
            while(result.next()) {
                return result.getString(index);
            }

            return null;
        }

        catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }

    }

    public int getIntFromQuery(String query, int index) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(query);
            while(result.next()) {
                return result.getInt(index);
            }

            return 0;
        }

        catch (Exception ex) {
            System.out.println(ex.toString());
            return 0;
        }

    }

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
