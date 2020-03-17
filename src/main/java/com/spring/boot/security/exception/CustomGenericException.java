package com.spring.boot.security.exception;

public class CustomGenericException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomGenericException()
	{
		
	}
	
	public CustomGenericException(String msg)
	{
		super(msg);
	}
}
