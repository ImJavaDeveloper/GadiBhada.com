package com.spring.boot.security.exception;

import javax.servlet.ServletException;

public class AppHttpException extends ServletException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AppHttpException()
	{
		
	}
	
	public AppHttpException(String msg)
	{
		super(msg);
	}

}
