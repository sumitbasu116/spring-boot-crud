package com.sumit.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.sumit.entity.Employee;

@EnableScan
public interface EmployeeRepository extends CrudRepository<Employee,String> {
	
}
