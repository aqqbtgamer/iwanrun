package com.iwantrun.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.admin.service.WebSiteCooperativeLogoService;

@RestController
@RequestMapping("webSiteCooperativeLogo")
public class WebSiteCooperativeLogoController {
	
	@Autowired
	private WebSiteCooperativeLogoService logoService ;
	
	@RequestMapping("add")
	public String addAll(HttpServletRequest request) {
		return logoService.add(request);
	}
	
	@RequestMapping("findAll")
	public String findAll(HttpServletRequest request) {
		return logoService.findAll(request);
	}
	
	@RequestMapping("delete")
	public String delete(HttpServletRequest request) {
		return logoService.delete(request);
	}
	
	@RequestMapping("query")
	public String queryByCondition(HttpServletRequest request) {
		return logoService.query(request);
	}

}
