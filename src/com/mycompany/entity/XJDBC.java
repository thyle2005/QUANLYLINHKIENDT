/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Duy Nin
 */
public class XJDBC {
    public static Connection conn = null; // Kết nối với sql
    public static PreparedStatement ps = null; // Câu lệnh SQL được biên dịch trước
    public static ResultSet rs = null; // Trả về kết quả truy vấn

    public static String username = "sa";
    public static String password = "123456";
    public static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String url = "jdbc:sqlserver://localhost:1433;databaseName=QLDUAN1;encrypt=false";

    /*
     * Nạp driver
     */
    static {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    /*
     * Xây dựng PreparedStatement
     *
     */
    public static PreparedStatement getStmt(String sql, Object... args) {
        try {
            conn = DriverManager.getConnection(url, username, password);
            if (sql.trim().startsWith("{")) {
                ps = conn.prepareCall(sql);
            } else {
                ps = conn.prepareStatement(sql);
            }
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /*
     * Thực hiện câu lệnh SQL thao tác (INSERT, UPDATE, DELETE) hoặc thủ tục lưu thao tác dữ liệu
     *
     */
    public static void update(String sql, Object... args) {
        try {
            ps = XJDBC.getStmt(sql, args);
            try {
                ps.executeUpdate();
            } finally {
                ps.getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /*
     * Thực hiện câu lệnh SQL truy vấn (SELECT) hoặc thủ tục lưu truy vấn dữ liệu
     */
    public static ResultSet query(String sql, Object... args) {
        try {
            ps = XJDBC.getStmt(sql, args);
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
}
