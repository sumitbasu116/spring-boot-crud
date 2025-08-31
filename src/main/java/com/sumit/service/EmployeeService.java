package com.sumit.service;

import java.util.List;

import com.sumit.entity.Employee;

public interface EmployeeService {

	Employee saveEmployee(Employee employee);

    List<Employee> fetchAllEmployees();

    Employee getEmployeeById(Long id);

    Employee updateEmployeeById(Long id, Employee employee);

    String deleteDepartmentById(Long id);
}
