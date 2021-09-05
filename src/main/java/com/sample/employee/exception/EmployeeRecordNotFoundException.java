package com.sample.employee.exception;

public class EmployeeRecordNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	  public EmployeeRecordNotFoundException(String msg) {
	    super(msg);
	  }
}
