package com.bridgelabz;
import java.time.LocalDate;
/*
Declaring Employee PayRoll Data
Declaring variables
 */
public class EmployeePayrollData {
    public int id;
    public String name;
    public double salary;
    public LocalDate start;
    /*
    Declaring Parametrised Constructor
     */
    public EmployeePayrollData(int id, String name, double salary, LocalDate startDate){
        this.id=id;
        this.name=name;
        this.salary=salary;
        this.start=startDate;
    }
}
