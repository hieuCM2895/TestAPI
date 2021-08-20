package com.fsoft.fa.model;

public class Employee {

    private int employee_id;
    private String employeeName;
    private double salary;
    private int spvrld;

    public Employee() {
    }

    public Employee(int employee_id, String employeeName, double salary, int spvrld) {
        this.employee_id = employee_id;
        this.employeeName = employeeName;
        this.salary = salary;
        this.spvrld = spvrld;
    }

    public int getEmployeeId() {
        return employee_id;
    }
}
