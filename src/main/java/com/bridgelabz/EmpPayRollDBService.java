package com.bridgelabz;
import com.mysql.jdbc.Driver;
/*
Importing Java Packages
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
public class EmpPayRollDBService {
    /*
    Declaring Main Method Here
    For Getting The Connection Of DataBase
     */
    public static void main(String[] args) {
        String jdbcURL= "jdbc:mysql://localhost:3306/payroll_service?userSSL=false";
        String userName = "root";
        String password = "Gopi@9666";
        Connection connection = null;
        try {
            System.out.println("connecting to database : "+ jdbcURL);
            connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection to the Database Successfully! :" + connection);
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        listDrivers();
    }
    private static void listDrivers() {
        Enumeration<java.sql.Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()) {
            System.out.println(driverList.nextElement());
        }
    }
}