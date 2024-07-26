/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Duy Nin
 */
public class ConnectDB {
    public static String username = "sa";
    public static String password = "123456";
    public static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String url = "jdbc:sqlserver://localhost:1433;databaseName=QLDUAN1;encrypt=false";
     public static Connection connection;
     public static Connection getConnection(){
         try{
             Class.forName(driverName);
             try{
                 connection = DriverManager.getConnection(url,username,password);
             }catch(Exception e){
                 System.out.println("Ket noi thanh cong");
             }
         }catch(ClassNotFoundException e){
             System.err.println("Ket noi khong thanh cong");
         }
         return connection;
     }
}
