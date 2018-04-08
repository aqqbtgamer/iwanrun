package com.iwantrun.admin.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iwantrun.admin.intercepter.LoginInterceptor;
import com.iwantrun.admin.service.LoginTokenService;
import com.iwantrun.admin.utils.AESUtils;


@Controller
public class LoginPageController {
	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Autowired
	private LoginTokenService loginService;
	
	@RequestMapping(value="/login")
	public String loginPage(HttpServletRequest request,HttpServletResponse response) {
		return "login";
	}
	
	@RequestMapping(value="/getLoginToken")
	public String getLoginToken(HttpServletRequest request,HttpServletResponse response) throws GeneralSecurityException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		logger.info("login user :"+username );
		String loginToken =loginService.getLoginTokenByCetification(username, AESUtils.encode(password),request.getSession().getId());
		if(!StringUtils.isEmpty(loginToken)) {
			Cookie cookie = new Cookie("login_token", loginToken);
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
		}
		response.sendRedirect("home.html");
		return null;
	}
}
