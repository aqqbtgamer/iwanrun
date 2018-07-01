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
	
	
	
	@RequestMapping(value="server")
	public String getConfig(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		if(UEditorService.METHOD_NAME_CONFIG.equals(action)) {
			return ueService.getConfig();
		}else if(UEditorService.UPLOAD_PATF.equals(action)) {
			return ueService.upload(request,response);
		}else if(UEditorService.UPLOAD_SCRAW_PATF.equals(action)) {
			return ueService.uploadScraw(request, response);
		}else if(UEditorService.UPLOAD_VIDEO_PATF.equals(action)) {
			return ueService.upload(request, response);
		}
		else {
			return null ;
		}
	}

}
