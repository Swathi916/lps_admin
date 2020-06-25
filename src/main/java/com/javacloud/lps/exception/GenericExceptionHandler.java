package com.javacloud.lps.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.javacloud.lps.entity.Applicant;
import com.javacloud.lps.entity.Loan;
import com.javacloud.lps.entity.User;
import com.javacloud.lps.response.Response;

@ControllerAdvice
public class GenericExceptionHandler {
	
		@ExceptionHandler
		public ResponseEntity<Response<Applicant>> handleException(ApplicationNotFoundException ex){
			Response<Applicant> response = new  Response<>(true,ex.getMessage(),null);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		@ExceptionHandler
		public ResponseEntity<Response<Loan>> handleException(LoanNotFoundException ex){
			Response<Loan> response = new  Response<>(true,ex.getMessage(),null);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		@ExceptionHandler
		public ResponseEntity<Response<User>> handleException(UserNotFoundException ex){
			Response<User> response = new  Response<>(true,ex.getMessage(),null);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		@ExceptionHandler
		public ResponseEntity<?> handleException(EmailNotFoundException ex){
			Response<User> response = new  Response<>(true,ex.getMessage(),null);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}

	}



