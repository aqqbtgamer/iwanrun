package com.iwantrun.admin.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.iwantrun.admin.constant.AdminApplicationConstants;

public class CookieUtils {
	
	public static String getCookieValue(HttpServletRequest request,String key) {
		String result = null ;
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals(key)) {
				result = cookie.getValue();
			}
		}
		return result;
	}
	
	public static String getLoginToken(HttpServletRequest request) {
		return getCookieValue(request,AdminApplicationConstants.LOGIN_TOKEN);
	}

}
