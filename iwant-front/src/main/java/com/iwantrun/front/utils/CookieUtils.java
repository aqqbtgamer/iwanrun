package com.iwantrun.front.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author user
 */
public class CookieUtils {

	/**
	 * 从request获取指定Cookie的值
	 * 
	 * @param key
	 * @param request
	 * @return
	 */
	public static String getCookieValue(String key, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			String name = cookie.getName();
			String path = cookie.getPath();
			if (name.equals(key)) {
				return cookie.getValue();
			}
		}
		return null;
	}

}
