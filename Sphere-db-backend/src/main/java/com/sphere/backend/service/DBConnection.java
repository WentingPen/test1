package com.sphere.backend.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/world";
    private static final String user = "PengWenting";
    private static final String pwd = "010318Pwt.";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,user,pwd);
    }
}