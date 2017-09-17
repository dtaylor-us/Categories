package msyql;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    public static Connection getConnection() throws URISyntaxException {
        String herokuURL = System.getenv("DATABASE_URL");
        System.out.println("HEROKU URL: " + herokuURL);

        URI dbUri = new URI(herokuURL);
        System.out.println("DB URI: " + dbUri);

        String dbUser = dbUri.getUserInfo().split(":")[0];
        System.out.println("DB USERNAME: " + dbUser);

        String dbPassword = dbUri.getUserInfo().split(":")[1];
        boolean passInd = (dbPassword != null || !"".equals(dbPassword));
        System.out.println("DB PASSWORD: " + passInd);


        String port = "";
        if (dbUri.getPort() > 0) {
            port = ":" + String.valueOf(dbUri.getPort());
            System.out.println("PORT #: " + port);
        } else {
            System.out.println("PORT #: " + "no port provided");
        }

        String dbHost = dbUri.getHost();
        System.out.println("HOST: " + dbHost);


        String path = dbUri.getPath();
        System.out.println("PATH: " + path);


        String url = "jdbc:mysql://" + dbHost + port + path;
        System.out.println("TEST-ME: " + url);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("MySQL Driver not Found!" + ex);
            return null;
        }

        System.out.println("MySQL Driver Registered");
        Connection connection = null;

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
}
