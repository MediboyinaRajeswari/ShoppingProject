package com.msd.customer.exception;

public class ResourceAlreadyExistsException extends Exception{
	private static final long serialVersionUID = 1L;

	public ResourceAlreadyExistsException() {
		super();
	}
	
	public ResourceAlreadyExistsException(String message) {
		super(message);
	}
}
