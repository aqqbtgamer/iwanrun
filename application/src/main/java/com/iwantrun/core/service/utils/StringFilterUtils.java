package com.iwantrun.core.service.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFilterUtils {
	
	public static final String maskSymbol = "*";
	
	public static String replaceBlank(String str) {  
        String dest = "";  
        if (str!=null) {  
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");  
            Matcher m = p.matcher(str);  
            dest = m.replaceAll("");  
        }  
        return dest;  
    }
	
	/**
	 * 将包含敏感信息的string mask话 其中
	 * head参数表示保留头几位  tail表示保留尾部几位
	 * 若head + tail超过String 长度 则本方法失效
	 * @param head
	 * @param tail
	 * @param input
	 * @return
	 */
	public static String maskString(int head, int tail,String input) {
		if(input != null) {
			int length = input.length();
			if(length <= head + tail) {
				return input ;
			}else {
				String headStr = input.substring(0, head);
				String tailStr = input.substring(length-tail);
				for(int i = 0 ;i<length - head -tail ; i++) {
					headStr = headStr.concat(maskSymbol);
				}
				return headStr.concat(tailStr);
			}
		}
		return null;
	}
}
