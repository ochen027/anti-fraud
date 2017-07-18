package com.pwc.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtils {
	
	public static Date StringToDate(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDD");
	    Date d = sdf.parse(date);  
		return d;
	}
	
	
}
