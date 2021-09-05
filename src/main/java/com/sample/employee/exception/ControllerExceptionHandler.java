package com.sample.employee.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value = {EmployeeRecordNotFoundException.class})
	  @ResponseStatus(value = HttpStatus.NOT_FOUND)
	  public ErrorMessage employeeRecordNotFoundException(EmployeeRecordNotFoundException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(
		        HttpStatus.NOT_FOUND.value(),
		        new Date(),
		        ex.getMessage(),
		        request.getDescription(false));
	    
	    return message;
	  }
		
	@ExceptionHandler(MethodArgumentNotValidException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  ErrorMessage handleConstraintViolationException(MethodArgumentNotValidException e) {
		String msg = "Arguments passed in API request are in inavlid format. Kindly review.";
		ErrorMessage message = new ErrorMessage(
		        HttpStatus.BAD_REQUEST.value(),
		        new Date(),
		        msg,
		        e.getMessage());
	    
	    return message;
	  }
	
	@ExceptionHandler(Exception.class)
	  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	  public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(
	        HttpStatus.INTERNAL_SERVER_ERROR.value(),
	        new Date(),
	        ex.getMessage(),
	        request.getDescription(false));
	    
	    return message;
	  }
	
}
