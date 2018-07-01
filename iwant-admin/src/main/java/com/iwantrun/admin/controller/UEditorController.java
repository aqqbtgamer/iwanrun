package com.iwantrun.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.admin.service.UEditorService;

@RestController
@RequestMapping("ueEditor")
public class UEditorController {
	
	@Autowired
	private UEditorService ueService ;
	
	private static final String METHOD_NAME_CONFIG = "config" ;
	
	@RequestMapping(value="server")
	public String getConfig(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		if(METHOD_NAME_CONFIG.equals(action)) {
			return ueService.getConfig();
		}else if(UEditorService.UPLOAD_PATF.equals(action)) {
			return ueService.upload(request,response);
		}else {
			return null ;
		}
	}
	
	@RequestMapping("upload")
	public String upload(HttpServletRequest request, HttpServletResponse response) {
		 String config = "{\"state\": \"SUCCESS\"," +
	                "\"url\": \"" + "http://localhost:8088/" + "abc.png" + "\"," +
	                "\"title\": \"" + "abc.png" + "\"," +
	                "\"original\": \"" + "fileName" + "\"}"; 
		 return config;
	}
	
	
	

}
