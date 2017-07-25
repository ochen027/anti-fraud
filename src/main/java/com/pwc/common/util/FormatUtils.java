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

	//Date format YYYY-MM-DD
	public static LocalDate StringToLocalDate(String date){
		return LocalDate.of(Integer.parseInt(date.substring(0,4)),
				Integer.parseInt(date.substring(5,7)), Integer.parseInt(date.substring(8,10)));
	}

	public static String LocalDateToString(LocalDate localDate){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		return dtf.format(localDate);
	}

	public static void main(String agrs[]){

		System.out.println(LocalDateToString(LocalDate.now()));
	}
}
