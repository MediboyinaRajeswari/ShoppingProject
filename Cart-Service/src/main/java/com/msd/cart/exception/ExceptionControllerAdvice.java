package com.msd.cart.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//The class to handle global exceptions

@ControllerAdvice
public class ExceptionControllerAdvice {

	// Handle Resource Not Found Exception
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException e) {

		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), e.getMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	// Handle Something Went Wrong Exception
	@ExceptionHandler(value = SomethingWentWrongException.class)
	public ResponseEntity<Object> handleSomethingWentWrong(SomethingWentWrongException e) {

		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), e.getMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

}