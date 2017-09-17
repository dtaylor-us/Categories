package servlet;

import msyql.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("emailAddress");

        try {
            Connection con = DBConnector.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into user values(?,?,?)");

            ps.setString(1, userName);
            ps.setString(2, password);
            ps.setString(3, email);

            int i = ps.executeUpdate();
            if (i > 0)
                out.print("You are successfully registered...");

        } catch (Exception e2) {
            System.out.println(e2);
        }

        out.close();
    }

}