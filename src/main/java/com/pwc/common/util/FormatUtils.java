package com.pwc.common.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

public class FormatUtils {
	
	public static Date StringToDateNoDash(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.parse(date);
	}

	public static String DateToString(Date date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static Date StringToDate(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(date);
	}

	//Date format YYYY-MM-DD
	public static LocalDate StringToLocalDate(String date){
		return LocalDate.of(Integer.parseInt(date.substring(0,4)),
				Integer.parseInt(date.substring(5,7)), Integer.parseInt(date.substring(8,10)));
	}

	public static LocalDate StringToLocalDateNoDash(String date){
		return LocalDate.of(Integer.parseInt(date.substring(0,4)),
				Integer.parseInt(date.substring(4,6)), Integer.parseInt(date.substring(6,8)));
	}

	public static String LocalDateToString(LocalDate localDate){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		return dtf.format(localDate);
	}

	public static synchronized String GenerateId(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		return dtf.format(LocalDateTime.now())+System.nanoTime();
	}

	public static LocalDate DateToLocalDate(Date date) throws ParseException{
		String dateString = DateToString(date);
		return StringToLocalDate(dateString);
	}

	public static Date LocalDateToDate(LocalDate ld) throws ParseException{
		String localDateString = LocalDateToString(ld);
		return StringToDateNoDash(localDateString);
	}

}
