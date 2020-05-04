package com.flightReservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	// 
	public ResponseEntity<?> handleUserDoesNotFound(UserDoesNotFound exception,WebRequest request)
	{
		ErrorDetails errorDetails= new ErrorDetails(exception.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<?> handleUserNotValid(UserNotValid exception, WebRequest request)
	{
		ErrorDetails errorDetails=new ErrorDetails(exception.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<?> handleFlightNotFound(FlightNotFound exception, WebRequest request)
	{
		ErrorDetails errorDetails=new ErrorDetails(exception.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<?> handleTicketNotFound(TicketNotFound exception, WebRequest request)
	{
		ErrorDetails errorDetails=new ErrorDetails(exception.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
	}

}
