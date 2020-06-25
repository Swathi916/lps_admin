package com.javacloud.lps.exception;

public class LoanNotFoundException extends RuntimeException {
	         
	private static final long serialVersionUID = 1L;

	public  LoanNotFoundException(String message) {
		super(message);
	}

}

