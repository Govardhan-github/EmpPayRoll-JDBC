package com.bridgelabz;
/*
Importing Java Packages
 */
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class EmpPayRollDBService {
    private PreparedStatement employeePayrollDataStatement;
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
    /*
    Declaring Read Method To Read The Data From The Database
     */
    public List<EmployeePayrollData> readData() {
        String sql = "SELECT * FROM emp_payroll";
        return this.getEmployeePayrollDataUsingDB(sql);
    }
    /*
    Declaring Get Employee Data Method After Executing query From The Database
     */
    public List<EmployeePayrollData> getEmployeePayrollData(String name) {
        List<EmployeePayrollData> employeePayrollList = null;
        if (this.employeePayrollDataStatement == null)
            this.prepareStatementForEmployeeData();
        try {
            employeePayrollDataStatement.setString(1, name);
            ResultSet result;
            result = employeePayrollDataStatement.executeQuery();
            employeePayrollList = this.getEmployeePayrollData(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePayrollList;
    }
    private List<EmployeePayrollData> getEmployeePayrollData(ResultSet result) {
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        try {
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                double salary = result.getDouble("salary");
                LocalDate startDate = result.getDate("start").toLocalDate();
                employeePayrollList.add(new EmployeePayrollData(id, name, salary, startDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePayrollList;
    }
    /*
    Method To Prepared Statement For Employee Data for Queries
     */
    private void prepareStatementForEmployeeData() {
        try {
            Connection connection = this.getConnection();
            String sql = "SELECT * FROM payroll_service WHERE name = ?";
            employeePayrollDataStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int updateEmployeeData(String name, double salary) {
        return this.updateEmployeeDataUsingStatement(name, salary);
    }
    /*
    Declaring Update Employee Method To Update The Details Of Employee From Database
     */
    private int updateEmployeeDataUsingStatement(String name, double salary) {
        String sql = String.format("update emp_payroll set salary = %.2f where name = '%s';", salary, name);
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    /*
    Declaring get Employee Payroll For Date Range Method
    For Retrieving The Data Of Employee For Particular Date Range
     */
    public List<EmployeePayrollData> getEmployeePayrollForDateRange(LocalDate sDate, LocalDate endDate) {
        String sql = String.format("SELECT * FROM emp_payroll WHERE START BETWEEN '%s' AND '%s';",
                Date.valueOf(sDate), Date.valueOf(endDate));
        return this.getEmployeePayrollDataUsingDB(sql);
    }
    private List<EmployeePayrollData> getEmployeePayrollDataUsingDB(String sql) {
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                double salary = result.getDouble("salary");
                LocalDate startDate = result.getDate("start").toLocalDate();
                employeePayrollList.add(new EmployeePayrollData(id, name, salary, startDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePayrollList;
    }
}