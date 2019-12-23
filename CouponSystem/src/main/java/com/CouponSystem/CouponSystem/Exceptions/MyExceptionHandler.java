package com.CouponSystem.CouponSystem.Exceptions;

import java.sql.SQLException;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {
	

	
	@ExceptionHandler(value = 
			SQLException.class)
	public ResponseEntity<Object> handleSqlException(Exception ex,WebRequest wr){
		System.out.println(ex);
		String bodyOfResponse = "There was an error, please try again";
		return handleExceptionInternal(ex, bodyOfResponse,new HttpHeaders(), HttpStatus.CONFLICT, wr);
	}
	
	@ExceptionHandler(value = MyCouponSystemException.class)
	public ResponseEntity<Object> handleCouponException(Exception ex,WebRequest wr){
		System.out.println(ex);
		String bodyOfResponse = ex.getMessage();
		return handleExceptionInternal(ex, bodyOfResponse,new HttpHeaders(), HttpStatus.CONFLICT, wr);
	}
	
	@ExceptionHandler(value = LoginException.class)
	public ResponseEntity<Object> handleLoginException(Exception ex,WebRequest wr){
		System.out.println(ex);
		String bodyOfResponse = ex.getMessage();
		return handleExceptionInternal(ex, bodyOfResponse,new HttpHeaders(), HttpStatus.CONFLICT, wr);
	}
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Object> handleOtherException(RuntimeException ex,WebRequest wr){
		System.out.println(ex);
		String bodyOfResponse = "Email provided is not a valid email address!";
		return handleExceptionInternal(ex, bodyOfResponse,new HttpHeaders(), HttpStatus.CONFLICT, wr);
	}
}

//IllegalArgumentException
