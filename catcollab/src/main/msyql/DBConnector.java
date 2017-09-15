package main.msyql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class DBConnector {

    public static Connection getConnection() {
        String dbHost = System.getenv().get("DB_HOST");
        String dbName = System.getenv().get("DB_NAME");
        String dbUser = System.getenv().get("DB_USER");
        String dbPass = System.getenv().get("DB_PASSWORD");
        String useSSL = "false";
        String procBod = "true";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("MySQL Driver not Found!" + ex);
            return null;
        }

        System.out.println("MySQL Driver Registered");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":3306/" + dbName + "?useSSL=" + useSSL + "&noAccessToProcedureBodies=" + procBod, dbUser, dbPass);
        } catch (SQLException ex) {
            System.out.println("Connection failed!" + ex);
            return null;
        }

        if (connection != null) {
            System.out.println("Successfully connected to MySQL database");
            return connection;
        } else {
            System.out.println("Connection failed!");
            return null;
        }
    }
}
