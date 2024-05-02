package com.sphere.backend.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseSetup {


    public void createTable(String sqlCreate) {
//        String sqlCreate = "CREATE TABLE IF NOT EXISTS spheredb (" +
//                "id INT AUTO_INCREMENT PRIMARY KEY, " +
//                "name VARCHAR(100), " +
//                "email VARCHAR(100), " +
//                "age INT)";

        try (Connection conn = DBConnection.getConnection();  //尝试建立与数据库的连接，并返回一个 Connection 对象。
             Statement stmt = conn.createStatement()) {  // 使用刚刚创建的 Connection 对象 conn 来创建一个 Statement 对象。该对象用于执行静态 SQL 语句。
            stmt.execute(sqlCreate);
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage()); //catch抛出可能的异常，并打印异常信息
        }
    }

//    public static void main(String[] args) {
//        DatabaseSetup dbSetup = new DatabaseSetup();
//        dbSetup.createTable();

    }




    

