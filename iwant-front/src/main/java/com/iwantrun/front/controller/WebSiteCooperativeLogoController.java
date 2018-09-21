package com.iwantrun.front.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.front.service.WebSiteCooperativeLogoService;

@RestController
@RequestMapping("webSiteCooperativeLogo")
public class WebSiteCooperativeLogoController {
	
	@Autowired
	private WebSiteCooperativeLogoService logoService;
	
	@RequestMapping(value="query",method=RequestMethod.POST)
	public String queryLastestNews(HttpServletRequest request) {
		return logoService.findAll();
	}


}
