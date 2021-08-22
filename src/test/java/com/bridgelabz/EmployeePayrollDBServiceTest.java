package com.bridgelabz;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
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
}
