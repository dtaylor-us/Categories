package servlet;

import db.msyql.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("./register.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String userName = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");


        try {
            Connection conn = DBConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO user(userName, emailAddress, password) VALUES(?,?,?);");

            ps.setString(1, userName);
            ps.setString(2, email);
            ps.setString(3, password);

            int i = ps.executeUpdate();
            if (i > 0)
                out.print("You have successfully registered...");
            conn.close();

        } catch (Exception e2) {
            System.out.println(e2);
        }

        out.close();
    }

}