package com.bridgelabz;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class EmployeePayrollDBServiceTest {
    /*
    Test Method To Given Employee PayRoll DB Retrieved Should Match The Count
     */
    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount(){
        EmpPayRollDBService empPayRollDBService=new EmpPayRollDBService();
        List<EmployeePayrollData> employeePayrollDataList = empPayRollDBService.readData();
        Assertions.assertEquals(3,employeePayrollDataList.size());
    }
    /*
    Test Method To Update The Salary Of Employee In Data Base
     */
    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB() {
        EmpPayRollDBService empPayRollDBService=new EmpPayRollDBService();
        EmployeePayrollService employeePayrollService= new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = empPayRollDBService.readData();
        employeePayrollService.updateEmployeeSalary("Terisa", 3500000.00);
        boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Terisa");
        Assertions.assertTrue(result);
    }
    /*
    Test Method To Retreive The Data Of Employee Payroll For Date Range
     */
    @Test
    public void givenDateRange_WhenRetrived_ShouldReturnCount(){
        EmployeePayrollService employeePayrollService= new EmployeePayrollService();
        LocalDate sDate = LocalDate.of(2009,04,05);
        LocalDate endDate = LocalDate.now();
        List<EmployeePayrollData> EmployeePayrollData =employeePayrollService.readEmployeePayrollForDateRange(sDate,endDate);
        Assertions.assertEquals(3,EmployeePayrollData.size());
    }
    /*
    Test Method To Get The Average Salary Of Employees By Group Of Gender
     */
    @Test
    public void givenPayRollData_WhenAverageSalaryRetriveByGender_ShouldReturnProperValue(){
        EmpPayRollDBService empPayRollDBService=new EmpPayRollDBService();
        EmployeePayrollService employeePayrollService= new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollDataList = empPayRollDBService.readData();
        Map<String,Double> averageSalaryByGender=employeePayrollService.readAvgSalaryByGender();
        Assertions.assertTrue(averageSalaryByGender.get("M").equals(2000000.0)&&
                                averageSalaryByGender.get("F").equals(3500000.0));
    }
}
