package com.iwantrun.core.service.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.core.service.application.service.DictionaryService;
import com.iwantrun.core.service.application.transfer.Message;

@RestController
public class DictionaryController {
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@RequestMapping("/application/dictionary/getPages")
	public Message getPages(@RequestBody Message message) {
		Message response = new Message();
		response.setAccessToken(message.getAccessToken());
		response.setRequestMethod(message.getRequestMethod());
		response.setMessageBody(dictionaryService.getPages());
		return response;
	}

}
