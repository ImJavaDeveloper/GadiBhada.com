package com.spring.boot.security.helper;

import java.text.SimpleDateFormat;

public class FormUtils {
	
	public static String nullToEmpty(String s)
	{
		
		if(s==null || s.trim().length()==0)
			return "";
		else return s;
		
	}
	
	public static String getCurrentTimeStamp()
	{
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate=sdf.format(dt);
		return currentDate;
	}
	public static double getDouble(String s)
	{
		if(s!=null && s.trim().length()>0)
	        return Double.valueOf(s);
		else
			return 0;
	}

}
