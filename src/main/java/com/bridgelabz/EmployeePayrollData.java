package com.bridgelabz;
import java.time.LocalDate;
import java.util.Objects;

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
    /*
    Declaring String Equals Method
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePayrollData that = (EmployeePayrollData) o;
        return id == that.id && Double.compare(that.salary, salary) == 0 && Objects.equals(name, that.name) && Objects.equals(start, that.start);
    }
}
