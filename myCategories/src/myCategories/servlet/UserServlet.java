package myCategories.servlet;

import java.io.IOException;
import java.sql.*;
import javax.servlet.http.*;

public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        request.getRequestDispatcher("./user.jsp").forward(request, response);
    }

    private static void execQuery(HttpServletRequest request){
        try {
            Connection conn = getDBConnection();
            Statement statement = conn.createStatement();
            String sql ="SELECT userName, emailAddress FROM user";
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                String userName = rs.getString(1);
                String emailAddress = rs.getString(2);
                request.setAttribute("firstName", userName);
                System.out.println(userName + ": " + emailAddress);
            }
            conn.close();


        } catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }
    }

    private static Connection getDBConnection() {
        String dbHost = "localhost";
        String dbName = "catCollab";
        String dbUser = "root";
        String dbPass = "password";
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
