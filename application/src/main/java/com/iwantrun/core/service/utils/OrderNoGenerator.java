package com.iwantrun.core.service.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * a unique tools for generator meaningful orderNo tools
 * which can support 10 thousand mutithread request without a single confilict
 * @author WXP22
 *
 */
public class OrderNoGenerator {
	
	private static int idLength = 20 ;
	
	public static String generateOrderNo() {
		//partt1 yyyyMMdd starter 
		Date date = new Date();
		SimpleDateFormat smf = new SimpleDateFormat("yyyyMMdd");
		String partDate = smf.format(date);
		int remainLength = idLength - partDate.length();
		//part2 hashcode of date 
		String partHashCode = String.valueOf(Math.abs(date.hashCode()));
		remainLength = idLength - partHashCode.length();
		//part3 4 digtitals random
		String random4 = "";
		if(remainLength > 8) {
			random4 = String.valueOf((int)(Math.random()*99999999));			
		}
		remainLength = remainLength - random4.length();
		//fill the blank with zero
		char[] zero = null;
		if(remainLength > 0) {
			zero = new char[remainLength];
			for(int i =0 ; i<zero.length ;i++) {
				zero[i] = '0';
			}
		}
		String result = partDate.concat(partHashCode).concat(new String(zero)).concat(random4);
		return result;
	}
}
