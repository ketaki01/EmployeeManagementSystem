package com.sample.employee.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.employee.exception.EmployeeRecordNotFoundException;
import com.sample.employee.model.Employee;
import com.sample.employee.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class EmployeeController {

@Autowired
EmployeeRepository employeeRepository;
	
@GetMapping("/employeeList")
public ResponseEntity<List<Employee>> getAllEmployees() {
	try {
	      List<Employee> employeeList = new ArrayList<Employee>();
	      employeeRepository.findAll().forEach(employeeList::add);
	     
	      if (employeeList.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(employeeList, HttpStatus.OK);
	      
	    } catch (Exception e) {
	    	throw new EmployeeRecordNotFoundException("Employee table has zero records");
	    }
}

@GetMapping("/employee/{id}")
public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
  List<Employee> employeeData = employeeRepository.findById(id);

  if (null != employeeData) {
    return new ResponseEntity<>(employeeData.get(0), HttpStatus.OK);
  } else {
	  throw new EmployeeRecordNotFoundException("Employee record with id = "+id+" not found");
  }
  
}

@PostMapping("/employee")
public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
  try {
	  Employee emp = employeeRepository.save(new Employee(employee.getName(), employee.getEmailAddress(), employee.getJoiningDate()));
    return new ResponseEntity<>(emp, HttpStatus.CREATED);
  } catch (Exception e) {
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

@PutMapping("/employee/{id}")
public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @Valid @RequestBody Employee employee) throws ParseException {
  List<Employee> employeeData = employeeRepository.findById(id);

  if (null != employeeData) {
	Employee emp = employeeData.get(0);
    emp.setName(employee.getName());;
    emp.setEmailAddress(employee.getEmailAddress());
    emp.setJoiningDate(employee.getJoiningDate());;
    return new ResponseEntity<>(employeeRepository.save(emp), HttpStatus.OK);
  } else {
    throw new EmployeeRecordNotFoundException("Employee record with id = "+id+" not found");
  }
}

@DeleteMapping("/employee/{id}")
public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id) {
  try {
    employeeRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  } catch (Exception e) {
	  throw new EmployeeRecordNotFoundException("Employee record with id = "+id+" not found");
  }
}



}
