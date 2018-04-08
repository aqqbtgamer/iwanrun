package com.iwantrun.core.service.application.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.core.service.application.transfer.Message;
import com.iwantrun.core.service.utils.AESUtils;

@RestController
public class LoginTokenGenerationController {
	
	
	@RequestMapping(value="/application/loginToken")
	public Message generateLoginToken(@RequestBody Message message ) {
		Message response = new Message();
		response.setAccessToken(message.getAccessToken());
		response.setRequestMethod(message.getRequestMethod());
		try {
			response.setMessageBody(AESUtils.encode("not yet completed"));
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response ;
	}

}
