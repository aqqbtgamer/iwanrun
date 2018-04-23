package com.iwantrun.admin.intercepter;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.iwantrun.admin.constant.AdminApplicationConstants;
import com.iwantrun.admin.utils.LoginTokenVerifyUtils;

public class LoginInterceptor implements HandlerInterceptor {
	
	Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		logger.info("into pre handles");
		String loginToken = null ;
		String currentUser = null ;
		String sessionId = request.getSession().getId();
		Cookie[] cookies = request.getCookies();
		//search for login_token
		if(cookies == null) {
			logger.info("login_token not found");			
			response.sendRedirect(request.getContextPath()+"/"+AdminApplicationConstants.LOGIN_PAGE);
			return false ;
		}
		for(Cookie cookie : cookies) {
			if(AdminApplicationConstants.LOGIN_TOKEN.equals(cookie.getName())) {
				loginToken = cookie.getValue();
			}
			if(AdminApplicationConstants.USER_TOKEN.equals(cookie.getName())) {
				currentUser = cookie.getValue();
			}
		}
		if(loginToken == null) {
			logger.info("login_token not found");			
			response.sendRedirect(request.getContextPath()+"/"+AdminApplicationConstants.LOGIN_PAGE);
			return false ;
		}else {
			if(LoginTokenVerifyUtils.verifyLoginToken(sessionId, currentUser, loginToken)){
				logger.info("login_token valid");
				return true ;
			}else {
				logger.info("login_token invalid");
				response.sendRedirect(request.getContextPath()+"/"+AdminApplicationConstants.LOGIN_PAGE);
				return false;
			}
		}	
		//return true;
	}

}
