package com.sumit.service;

import java.util.List;

import com.sumit.entity.Employee;

public interface EmployeeService {

	Employee saveEmployee(Employee employee);

    List<Employee> fetchAllEmployees();

    Employee getEmployeeById(String id);

    Employee updateEmployeeById(String id, Employee employee);

}
