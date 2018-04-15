package com.iwantrun.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iwantrun.admin.constant.AdminApplicationConstants;

@Controller
public class HomePageController {
	
	@RequestMapping(value="/")
	public String indexPage() {
		return AdminApplicationConstants.HOME_PAGE;
	}

}
