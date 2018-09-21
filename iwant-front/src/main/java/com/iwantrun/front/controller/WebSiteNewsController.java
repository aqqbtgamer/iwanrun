package com.iwantrun.front.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.front.service.WebSiteNewsService;

@RestController
@RequestMapping("websiteNews")
public class WebSiteNewsController {
	
	@Autowired
	private WebSiteNewsService newsService;
			
	@RequestMapping(value="query",method=RequestMethod.POST)
	public String queryLastestNews(HttpServletRequest request) {
		return newsService.findLatestNews() ;
	}

}
