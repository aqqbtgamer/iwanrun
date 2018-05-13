package com.iwantrun.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwantrun.admin.service.CasesService;

@Controller
public class CaseController {
	@Autowired
	private CasesService casesService;
	
	@RequestMapping("/cases/findAll")
	@ResponseBody
	public String findAll(HttpServletRequest request) {
		return casesService.findAll(request);
	}

}
