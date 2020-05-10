package com.mycompany.constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormate {
	
	public static Date getCurrentDate(){
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		 Date date = new Date();  
		 System.out.println(formatter.format(date));
		return  date; 
	}
}
