package com.sumit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumit.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
}
