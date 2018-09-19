package com.iwantrun.core.service.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {
	
	private static final String defaultFormat = "yyyy-MM-dd HH:mm:ss" ;
	
	public static String formatDate(Date date ,String format) {
		SimpleDateFormat fmt ;
		if(format != null) {
			fmt = new SimpleDateFormat(format);
		}else {
			fmt = new SimpleDateFormat(defaultFormat);
		}
		return fmt.format(date);
	}

}
