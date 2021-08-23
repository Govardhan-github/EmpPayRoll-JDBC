package com.bridgelabz;
/*
Importing Java Packages
 */
import java.util.ArrayList;
import java.util.List;
/*
Declaring Employee Pay Roll Service Class
 */
public class EmployeePayrollService {
    public enum IOService {
        DB_IO
    }
    private List<EmployeePayrollData> employeePayrollList;
    EmpPayRollDBService empPayRollDBService = new EmpPayRollDBService();
    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
        this.employeePayrollList = employeePayrollList;
    }
    public EmployeePayrollService() {
        this.employeePayrollList = new ArrayList<EmployeePayrollData>();
    }
    public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService) {
        this.employeePayrollList = empPayRollDBService.readData();
        return employeePayrollList;
    }
    /*
    Declaring Method To Check The Payroll Should Sync With The Database
     */
    public boolean checkEmployeePayrollInSyncWithDB(String name) {
        List<EmployeePayrollData> employeePayrollList = empPayRollDBService.getEmployeePayrollData(name);
        return employeePayrollList.get(0).equals(getEmployeePayrollData(name));
    }

    /*
    Declaring Update Employee Method To Update The Salary
     */
    public void updateEmployeeSalary(String name, double salary) {
        int result = empPayRollDBService.updateEmployeeData(name, salary);
        if (result == 0)
            return;
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
        if (employeePayrollData != null)
            employeePayrollData.salary = salary;
    }
    /*
    Declaring Get Employee Method To get The Details Of Employee
     */
    private EmployeePayrollData getEmployeePayrollData(String name) {
        return this.employeePayrollList.stream()
                .filter(employeePayrollData -> employeePayrollData.name.equals(name))
                .findFirst()
                .orElse(null);
    }
}