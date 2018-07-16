package com.iwantrun.admin.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.iwantrun.admin.constant.AdminApplicationConstants;

import net.minidev.json.JSONObject;

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
		String token = getCookieValue(request,AdminApplicationConstants.LOGIN_TOKEN);
		String user =  getCookieValue(request,AdminApplicationConstants.USER_TOKEN);
		String sessionId = request.getSession().getId();
		JSONObject tokenJson = new JSONObject();
		tokenJson.put("loginToken", token);
		tokenJson.put("currentUser", user);
		tokenJson.put("sessionId", sessionId);
		return tokenJson.toJSONString();
	}

}
