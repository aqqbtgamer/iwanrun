package com.iwantrun.core.service.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginTokenGenerationController {
	
	
	@RequestMapping(value="/application/loginToken")
	public String generateLoginToken(HttpServletRequest request) {
		return null ;
	}

}
