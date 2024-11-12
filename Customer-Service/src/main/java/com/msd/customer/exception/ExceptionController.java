package com.msd.customer.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ExceptionResponse handleResourceAlreadyExistsException(HttpServletRequest request,
			ResourceAlreadyExistsException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setUrl(request.getRequestURI());
		exceptionResponse.setErrorMessage(exception.getMessage());
		return exceptionResponse;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	@ExceptionHandler(ResourceNotFoundException.class)
	public ExceptionResponse handleResourceNotFoundException(HttpServletRequest request,
			ResourceNotFoundException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setUrl(request.getRequestURI());
		exceptionResponse.setErrorMessage(exception.getMessage());
		return exceptionResponse;
	}

}