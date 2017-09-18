package db.msyql;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    public static Connection getConnection() throws URISyntaxException {
        String herokuURL = System.getenv("DATABASE_URL");
        URI dbUri = new URI(herokuURL);
        String dbUser = dbUri.getUserInfo().split(":")[0];
        String dbPassword = dbUri.getUserInfo().split(":")[1];
        String port = getPortFromURI(dbUri);
        String dbHost = dbUri.getHost();
        String path = dbUri.getPath();
        String url = "jdbc:mysql://" + dbHost + port + path;

        //region PRINT DB CREDENTIALS
//        System.out.println("HEROKU URL: " + herokuURL);
//        System.out.println("DB URI: " + dbUri);
//        System.out.println("DB USERNAME: " + dbUser);
//        System.out.println("DB PASSWORD: " + passInd);
//        System.out.println("HOST: " + dbHost);
//        System.out.println("PATH: " + path);
//        System.out.println("TEST-ME: " + url);
//end region

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("MySQL Driver not Found!" + ex);
            return null;
        }

        System.out.println("MySQL Driver Registered");
        Connection connection;

        try {
            connection = DriverManager.getConnection(url, dbUser, dbPassword);
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

    private static String getPortFromURI(URI dbUri) {
        if (dbUri.getPort() > 0) {
            return ":" + String.valueOf(dbUri.getPort());
        } else {
            System.out.println("PORT #: " + "no port provided");
            return "";
        }

    }
}
