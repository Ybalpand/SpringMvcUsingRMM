package com.mycompany.constants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormate {
	
	public static Date getCurrentDate(){
		 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		 Date date = new Date();  
		 System.out.println(formatter.format(date));
		return  date; 
	}
	
	public static Date typecastToDate(String inputdate) throws ParseException{
	
		 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		 Date date = formatter.parse(inputdate);
		 System.out.println(formatter.format(date));
		return  date; 
	}
}
