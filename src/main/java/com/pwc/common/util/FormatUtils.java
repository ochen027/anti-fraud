package com.pwc.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FormatUtils {
	
	public static Date StringToDate(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDD");
	    Date d = sdf.parse(date);  
		return d;
	}

	public static LocalDateTime StringToLocalDateTime(String date){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-DD HH:mm:ss");
		return LocalDateTime.parse(date, dtf);
	}

	
}
