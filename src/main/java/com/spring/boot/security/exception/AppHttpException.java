package com.spring.boot.security.exception;

import javax.servlet.ServletException;

public class AppHttpException extends ServletException {
	
	public AppHttpException()
	{
		
	}
	
	public AppHttpException(String msg)
	{
		super(msg);
	}

}
