package com.sample.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.employee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	List<Employee> findById(long id);
	
	List<Employee> findByName(String name);

}
