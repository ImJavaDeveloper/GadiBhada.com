package com.spring.boot.security.helper;

public class DataValidator {
	
	public static boolean  isInteger(String s)
	{
		boolean isInteger=false;
		
		try {
			int val=Integer.parseInt(s);
			isInteger=true;
		}
		catch(NumberFormatException e)
		{
			return isInteger;
		}
		
		return isInteger;
		
	}
	
	
	public static int  checkStringLenght(String s)
	{
		
		return s.toCharArray().length;
		
	}
	public static boolean  isValidMobileNumer(String s)
	{
		boolean isValidMobile=false;
		
		try {
			int val=Integer.parseInt(s);
			isValidMobile=true;
			
		}
		catch(NumberFormatException e)
		{
			return isValidMobile;
		}
		
		return isValidMobile;
		
	}

}
