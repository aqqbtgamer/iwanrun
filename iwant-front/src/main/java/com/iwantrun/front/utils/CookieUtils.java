package com.iwantrun.front.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

/**
 * @author user
 */
public class CookieUtils {

	public static final String DEFAULT_COOKIE_PATH = "/";

	/**
	 * 从request获取指定Cookie的值
	 * 
	 * @param key
	 * @param request
	 * @return
	 */
	public static String getCookieValue(String key, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				if (name.equals(key)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * 写cookie
	 * 
	 * @param expirty
	 *            cookie生命周期
	 * @param key
	 * @param value
	 * @param response
	 */
	public static void addCookie(String expirty, String key, String value, HttpServletResponse response) {
		Cookie cookie = new Cookie(key, value);
		if (!StringUtils.isEmpty(expirty)) {
			int maxAge = Integer.valueOf(expirty);
			cookie.setMaxAge(maxAge);
		}
		cookie.setPath(DEFAULT_COOKIE_PATH);
		response.addCookie(cookie);
	}

}
