package com.spring.boot.security.exception;

public class DataBaseException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String page;
	public DataBaseException()
	{
		
	}
	public DataBaseException (String errorMsg,String page)
	{
		super(errorMsg);
		this.page=page;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}

}
