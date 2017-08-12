package main.msyql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    public static Connection getConnection() {
        String dbHost = "us-cdbr-iron-east-03.cleardb.net";
        String dbName = "heroku_4e22acf2c863394";
        String dbUser = "be5a5f924b1322";
        String dbPass = "9766bc85";
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

//  TODO: REFACTOR TU USE THIS DB CONNECTION
//    private static Connection prodDBConnection() throws URISyntaxException, SQLException{
//        URI dbUri = new URI("mysql://be5a5f924b1322:9766bc85@us-cdbr-iron-east-03.cleardb.net/heroku_4e22acf2c863394?reconnect=true");
//
//        String username = dbUri.getUserInfo().split(":")[0];
//        String password = dbUri.getUserInfo().split(":")[1];
//        String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
//
//        return DriverManager.getConnection(dbUrl, username, password);
//    }
}
