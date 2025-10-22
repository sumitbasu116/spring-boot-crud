package com.sumit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long employeeId;

    private String employeeName;

    private float employeeSalary;

    public Employee() {
    }

    public Employee(Long employeeId, String employeeName, float employeeSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
    }

    public Employee( String employeeName, float employeeSalary) {
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
    }
}
