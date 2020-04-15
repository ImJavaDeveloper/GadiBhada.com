package com.spring.boot.security.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DataHelper {
	 
	public static String  formatDate(String date,String sourceFormat,String requiredFormat) throws Exception
	{
		if(date==null || date.trim().length()==0)
			return null;
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
	
	public static long noOfDaysBetweenTwoDate(String current,String previous,String format ) 
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
		long diffInMillies = 0;
		
		Date currentDt;
		try {
			currentDt = sdf.parse(current);
			Date previousDt=sdf.parse(previous);
			diffInMillies = currentDt.getTime() - previousDt.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
		
	
}
}
