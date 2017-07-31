package com.catCollab.console;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        DBConnection();
    }

    private static Connection DBConnection() {
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