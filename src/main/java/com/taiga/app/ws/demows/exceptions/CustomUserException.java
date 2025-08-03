package com.taiga.app.ws.demows.exceptions;

public class CustomUserException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3882278667661834921L;

	public CustomUserException(String message){
		super(message);
	}
}
