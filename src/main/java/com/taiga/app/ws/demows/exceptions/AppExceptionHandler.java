package com.taiga.app.ws.demows.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.taiga.app.ws.demows.ui.model.response.ErrorMessage;

@ControllerAdvice //This annotation is used to listen the exception in application
public class AppExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){
		
		String errorDescription = ex.getLocalizedMessage();
		if(errorDescription==null){
			errorDescription = ex.toString();
		}
		ErrorMessage errorMessage = new ErrorMessage(new Date(), errorDescription);
		return new ResponseEntity<>(
				errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	@ExceptionHandler(value = {NullPointerException.class})
//	public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request){
//		
//		String errorDescription = ex.getLocalizedMessage();
//		if(errorDescription==null){
//			errorDescription = ex.toString();
//		}
//		ErrorMessage errorMessage = new ErrorMessage(new Date(), errorDescription);
//		return new ResponseEntity<>(
//				errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);// We can use any business login here 
//	}
//	
//	@ExceptionHandler(value = {CustomUserException.class})
//	public ResponseEntity<Object> handleCustomUserException(CustomUserException ex, WebRequest request){
//		
//		String errorDescription = ex.getLocalizedMessage();
//		if(errorDescription==null){
//			errorDescription = ex.toString();
//		}
//		ErrorMessage errorMessage = new ErrorMessage(new Date(), errorDescription);
//		return new ResponseEntity<>(
//				errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);// We can use any business login here 
//	}
	
	@ExceptionHandler(value = {NullPointerException.class, CustomUserException.class})
	public ResponseEntity<Object> handleSpecificException(Exception ex, WebRequest request){
		
		String errorDescription = ex.getLocalizedMessage();
		if(errorDescription==null){
			errorDescription = ex.toString();
		}
		ErrorMessage errorMessage = new ErrorMessage(new Date(), errorDescription);
		return new ResponseEntity<>(
				errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);// We can use any business login here 
	}
}
