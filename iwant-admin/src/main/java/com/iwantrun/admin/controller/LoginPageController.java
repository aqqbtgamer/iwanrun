package com.iwantrun.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginPageController {
	
	@RequestMapping(value="/login")
	public String loginPage(HttpServletRequest request,HttpServletResponse response) {
		return "login";
	}
}
