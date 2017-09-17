package servlet;

import msyql.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = null;

        try {
            Connection conn = DBConnector.getConnection();
            userName = getUserName(userName, conn);
        } catch (URISyntaxException ex) {
            System.out.println("URI provided has an error in it's syntax.");
        }

        request.setAttribute("userName", setUserResponse(userName));
        request.getRequestDispatcher("./user.jsp").forward(request, response);
    }

    private String setUserResponse(String userName) {
        return userName != null ? userName : "user not found";
    }

    private String getUserName(String userName, Connection conn) {
        try {
            Statement st = conn.createStatement();
            String sql = "SELECT userName FROM user";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                userName = rs.getString(1);
            }
            conn.close();

        } catch (SQLException sqlEx) {
            System.out.println("CAUGHT SQL: " + sqlEx);
        }
        return userName;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}