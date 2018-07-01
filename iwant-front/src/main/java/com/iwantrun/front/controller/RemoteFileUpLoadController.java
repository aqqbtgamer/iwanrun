package com.iwantrun.front.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.iwantrun.front.constants.AdminApplicationConstants;
import com.iwantrun.front.service.RemoteFileService;
import com.iwantrun.front.utils.CookieUtils;

@Controller
public class RemoteFileUpLoadController {
	
	@Autowired
	private RemoteFileService fileService ;
	
	@RequestMapping(value="/remote/fileupload")
	@ResponseBody
	public String uploadFile(@RequestParam("fileUpload") MultipartFile file,HttpServletRequest request) {
		String uploadResult = AdminApplicationConstants.HTTP_RESULT_FAILED ;
		String token = CookieUtils.getLoginToken(request);
		uploadResult = fileService.sendFileToApplication(file, token);
		return uploadResult;
	}

}
