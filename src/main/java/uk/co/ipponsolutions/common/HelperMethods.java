package uk.co.ipponsolutions.common;

import java.util.Calendar;
import java.util.Date;

public class HelperMethods {

	public static Date getNextDatePeriodAfterNow()
	{
		Date date = new Date();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		int currentHourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
		int hourOfDay = 21;
		
		if (currentHourOfDay < 3) {
			hourOfDay = 3;
		} else if (currentHourOfDay < 6) {
			hourOfDay = 6;
		} else if (currentHourOfDay < 12) {
			hourOfDay = 12;
		} else if (currentHourOfDay < 15) {
			hourOfDay = 15;
		} else if (currentHourOfDay < 18) {
			hourOfDay = 18;
		} else if (currentHourOfDay < 21) {
			hourOfDay = 21;
		}
		
		calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		date = calendar.getTime();
		
		return date;
	}
}
