package com.msd.product.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	ExceptionResponse handleResourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest request) {
		ExceptionResponse ownResponse = new ExceptionResponse();
		ownResponse.setErrorMessage(exception.getMessage());
		ownResponse.setRequestedURI(request.getRequestURI());
		return ownResponse;
	}
    
	@ExceptionHandler(EmptyFieldException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	ExceptionResponse handleEmptyFieldException(EmptyFieldException exception, HttpServletRequest request) {
		ExceptionResponse ownResponse = new ExceptionResponse();
		ownResponse.setErrorMessage(exception.getMessage());
		ownResponse.setRequestedURI(request.getRequestURI());
		return ownResponse;
	}
	
	@ExceptionHandler(DuplicateNotAllowedException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	@ResponseBody
	ExceptionResponse handleDuplicateNotAllowedException(DuplicateNotAllowedException exception, HttpServletRequest request) {
		ExceptionResponse ownResponse = new ExceptionResponse();
		ownResponse.setErrorMessage(exception.getMessage());
		ownResponse.setRequestedURI(request.getRequestURI());
		return ownResponse;
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		return new ResponseEntity<>("check the method Type", HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	ExceptionResponse handleException(Exception exception, HttpServletRequest request) {
		ExceptionResponse ownResponse = new ExceptionResponse();
		ownResponse.setErrorMessage(exception.getMessage());
		ownResponse.setRequestedURI(request.getRequestURI());
		return ownResponse;
	}

}
