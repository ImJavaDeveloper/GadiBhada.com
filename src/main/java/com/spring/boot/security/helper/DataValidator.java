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
	
	public static int  strinCharLen(String s)
	{
		char charArray[]=s.trim().toCharArray();
		return charArray.length;
		
	}
	
	
	public static int  checkStringLenght(String s)
	{
		
		return s.toCharArray().length;
		
	}
	public static boolean  isValidMobileNumer(String s)
	{
		return isNumber(s) && strinCharLen(s)==10;
		
	}
	
	public static boolean  isNumber(String s)
	{
		String regex="[0-9.]*";
		return s.matches(regex);
		
	}

}
