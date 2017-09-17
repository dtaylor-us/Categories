package servlet;

import msyql.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Register extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            Connection conn = DBConnector.getConnection();
        } catch (URISyntaxException ex) {
            System.out.println("URI provided has an error in it's syntax.");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String n = request.getParameter("userName");
        String p = request.getParameter("password");
        String e = request.getParameter("emailAddress");


        try {
            Connection con = DBConnector.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into user values(?,?,?)");

            ps.setString(1, n);
            ps.setString(2, p);
            ps.setString(3, e);

            int i = ps.executeUpdate();
            if (i > 0)
                out.print("You are successfully registered...");

        } catch (Exception e2) {
            System.out.println(e2);
        }

        out.close();
    }

}