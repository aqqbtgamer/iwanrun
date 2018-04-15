package com.iwantrun.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UEditorController {
	
	@RequestMapping(value="/ueEditor/server")
	public String ueEditorController(HttpServletRequest request, HttpServletResponse response) {
		return "not implement yet";
	}

}
