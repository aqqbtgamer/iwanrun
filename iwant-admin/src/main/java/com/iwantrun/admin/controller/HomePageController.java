package com.iwantrun.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {
	
	@RequestMapping("/")
	public String indexPage() {
		return "home";
	}

}
