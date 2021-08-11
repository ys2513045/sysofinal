package com.itwillbs.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DateUtil {

	   public String getDate() {
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	        Calendar calendar = Calendar.getInstance();
	        Date date = (Date) calendar.getTime();
	        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
	        String dateResult = sdf.format(date);
	        return dateResult;
	    }
	 
	
}
