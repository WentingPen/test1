package com.sphere.backend.service;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.StringJoiner;

@Service
public class DatabaseManagement {
    public String addUser(String name, String email, Integer age , String db) {
        String sql = "INSERT INTO "+ db +" (name, email, age) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, age);
            pstmt.executeUpdate();
            return "User added successfully into " + db;
        } catch (SQLException e) {
            if (e.getMessage().contains("Unknown database")) {
                return "Database does not exist";
            }
            e.printStackTrace();
            return "SQL Error: " + e.getMessage();
        }

    }

    public String delUser(String name, String db){
        if (db == null || db.trim().isEmpty()) {
            return "Database name is invalid";
        }
//        String displaysql = " DELETE FROM " + db +" WHERE name = '" + name + "'";

        String sql = " DELETE FROM " + db +" WHERE name = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            return "User deleted successfully from " + db;
        }catch (SQLException e){
            if (e.getMessage().contains("Unknown database")) {
                return "Database does not exist";
            }
            e.printStackTrace();
            return "SQL Error: " + e.getMessage();
        }

    }

    public String updateUser(Map<String, String> updates , String condition , String db){
        String sql = buildSqlUpdate(updates,condition,db);
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int i = 1;
            for(String value : updates.values()){
            pstmt.setString(i++,value);}
            pstmt.executeUpdate();
            return "User update successfully in " + db;
        } catch (SQLException e){
            if (e.getMessage().contains("Unknown database")) {
                return "Database does not exist";
            }
            e.printStackTrace();
            return "SQL Error: " + e.getMessage();

        }
    }
    public static String buildSqlUpdate(Map<String,String> updates,String condition,String db){
        StringJoiner setClause = new StringJoiner(", ");
        for (String cloumn : updates.keySet()){
            setClause.add(cloumn + " = ?");
        }
        return String.format("UPDATE %s SET %s WHERE %s", db, setClause, condition);
    }
}
