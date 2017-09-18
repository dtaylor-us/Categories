package servlet;

import db.msyql.DBConnector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("./login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (checkUser(email, password)) {
            request.getRequestDispatcher("./index.jsp").forward(request, response);
        } else {
            out.println("Username or Password incorrect");
            request.getRequestDispatcher("./register.jsp").include(request, response);
        }
    }

    private static boolean checkUser(String email, String password) {
        boolean st = false;
        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DBConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement
                    ("select * from user where emailAddress=? and password=?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            st = rs.next();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }

}