package com.iwantrun.admin.intercepter;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
	
	Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		logger.info("into pre handles");
		String login_token = null ;
		Cookie[] cookies = request.getCookies();
		//search for login_token
		for(Cookie cookie : cookies) {
			if("login_token".equals(cookie.getName())) {
				login_token = cookie.getValue();
			}
		}
		if(login_token == null) {
			logger.info("login_token not found");
			try {
				response.sendRedirect(request.getContextPath()+"/login");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false ;
		}else {
			return true;
		}
				
	}

}
