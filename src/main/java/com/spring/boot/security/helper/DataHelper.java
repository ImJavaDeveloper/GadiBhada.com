package com.spring.boot.security.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataHelper {
	 
	public static String  formatDate(String date,String sourceFormat,String requiredFormat) throws Exception
	{
		SimpleDateFormat format=new SimpleDateFormat(sourceFormat);
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(requiredFormat);
		if(isValidDateFormat(date,sourceFormat))
		return simpleDateFormat.format(format.parse(date));
		else {
			throw new Exception("Date Format Is Not Correct");
			
		}
	}
	
	public static boolean isValidDateFormat(String date,String dateFormat ) 
	{
Date dt=null;
		
		SimpleDateFormat format=new SimpleDateFormat(dateFormat);
		try {
		       dt=format.parse(date);
			if(!date.equals(format.format(dt)))
			{
				dt=null;
			}
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return dt!=null;
		
	}
}
