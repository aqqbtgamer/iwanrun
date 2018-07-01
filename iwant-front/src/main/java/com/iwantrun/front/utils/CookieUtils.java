package com.iwantrun.front.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.iwantrun.front.constants.CookieConstants;

/**
 * @author user
 */
public class CookieUtils {
	private static Logger logger = LoggerFactory.getLogger(CookieUtils.class);
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
		
		logger.info("开始写入cookie--key为{}--value为{}", key, value);
		
		Cookie cookie = new Cookie(key, value);
		if (!StringUtils.isEmpty(expirty)) {
			int maxAge = Integer.valueOf(expirty);
			cookie.setMaxAge(maxAge);
		}
		cookie.setPath(DEFAULT_COOKIE_PATH);
		response.addCookie(cookie);
		
		logger.info("写入cookie结束--key为{}--value为{}", key, value);
	}

	public static String getLoginToken(HttpServletRequest request) {
		return getCookieValue(CookieConstants.COOKIE_ACCESS_TOKEN_KEY, request);
	}
}
