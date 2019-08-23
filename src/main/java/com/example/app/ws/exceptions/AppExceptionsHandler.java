package com.example.app.ws.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.app.ws.ui.model.response.ErrorMessage;

/*
 * Handles general exception & returns full stackTrace of the exception
@ControllerAdvice // makes the class listens for exceptions across all the RequestMapping
public class AppExceptionsHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = {Exception.class}) //handles the exception
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
		return new ResponseEntity<> (ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
*/


@ControllerAdvice 
public class AppExceptionsHandler extends ResponseEntityExceptionHandler{
	
	//Handles general exception & returns custom exception body with contents of the ErrorMessage class
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
		
		String errorMessageDescription = ex.getLocalizedMessage();
		
		if (errorMessageDescription == null) {
			errorMessageDescription = ex.toString();
		}
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
		
		return new ResponseEntity<> (errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	//Handles specific exceptions in 1 method & returns custom exception body with contents of the ErrorMessage class
		@ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})
		public ResponseEntity<Object> handleSpecificExceptions(Exception ex, WebRequest request) {
			
			String errorMessageDescription = ex.getLocalizedMessage();
			
			if (errorMessageDescription == null) {
				errorMessageDescription = ex.toString();
			}
			
			ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
			
			return new ResponseEntity<> (errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
