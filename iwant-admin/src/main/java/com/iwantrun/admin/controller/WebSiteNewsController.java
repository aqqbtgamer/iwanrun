package com.iwantrun.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.admin.service.WebSiteNewsService;

@RestController
@RequestMapping("/websiteNews")
public class WebSiteNewsController {
	
	@Autowired
	private WebSiteNewsService newsService ;
	
	@RequestMapping("add")
	public String addAll(HttpServletRequest request) {
		return newsService.add(request);
	}
	
	@RequestMapping("findAll")
	public String findAll(HttpServletRequest request) {
		return newsService.findAll(request);
	}
	
	@RequestMapping("delete")
	public String delete(HttpServletRequest request) {
		return newsService.delete(request);
	}
	
	@RequestMapping("query")
	public String queryByCondition(HttpServletRequest request) {
		return newsService.queryByCondition(request);
	}
}
