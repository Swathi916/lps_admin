package com.javacloud.lps.exception;

public class ApplicationNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public  ApplicationNotFoundException(String message) {
		super(message);
	}

}

