package com.iwantrun.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {
	
	@RequestMapping("/")
	public String indexPage() {
		return "index";
	}

}
