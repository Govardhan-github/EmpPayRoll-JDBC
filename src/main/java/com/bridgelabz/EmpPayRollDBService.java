package com.bridgelabz;
import com.mysql.jdbc.Driver;
/*
Importing Java Packages
 */
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class EmpPayRollDBService {
    /*
    Declaring Main Method Here
    For Getting The Connection Of DataBase
     */
    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?userSSL=false";
        String userName = "root";
        String password = "Gopi@9666";
        Connection connection;
        System.out.println("connecting to database : " + jdbcURL);
        connection = DriverManager.getConnection(jdbcURL, userName, password);
        System.out.println("Connection to the Database Successfully! :" + connection);
        return connection;
    }
    public List<EmployeePayrollData> readData() {
        String sql = "SELECT * FROM emp_payroll";
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        try {
            Connection connection=this.getConnection();
            Statement statement= connection.createStatement();
            ResultSet result=statement.executeQuery(sql);
            while (result.next()){
                int id = result.getInt("id");
                String name = result.getString("name");
                double salary = result.getDouble("salary");
                LocalDate startDate = result.getDate("start").toLocalDate();
                employeePayrollList.add(new EmployeePayrollData(id,name,salary,startDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePayrollList;
    }
}
