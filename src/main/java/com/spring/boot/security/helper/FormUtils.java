package com.spring.boot.security.helper;

import java.text.SimpleDateFormat;

public class FormUtils {
	
	public static String nullToEmpty(String s)
	{
		
		if(s==null)
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

}
