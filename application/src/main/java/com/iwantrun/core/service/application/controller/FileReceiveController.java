package com.iwantrun.core.service.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.core.service.application.annotation.NeedTokenVerify;
import com.iwantrun.core.service.application.service.RemoteFileService;
import com.iwantrun.core.service.application.transfer.Message;

@RestController
public class FileReceiveController {
	
	Logger logger = LoggerFactory.getLogger(FileReceiveController.class);
	
	@Autowired
	private RemoteFileService fileService;
	
	@RequestMapping(value="/application/file")
	@NeedTokenVerify
	public Message receiveRemoteFile(@RequestBody Message request) {
		return 	fileService.uploadRemoteFile(request);	
	}

}
