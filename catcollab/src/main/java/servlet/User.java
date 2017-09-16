package servlet;

import msyql.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class User extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = null;

        Connection conn = DBConnector.getConnection();
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

        userName = userName != null ? userName : "user";
        request.setAttribute("userName", userName);

        request.getRequestDispatcher("./user.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}