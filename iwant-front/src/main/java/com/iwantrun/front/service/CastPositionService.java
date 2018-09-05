package com.iwantrun.front.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.front.transfer.Message;

@Service
public class CastPositionService {
	
	@Autowired
	private Environment environment;
	@Autowired
	private RestTemplate template;
	
	public String findAll() {
		String findAll = environment.getProperty("application.castposition.getAll");
		String baseUrl = environment.getProperty("app.server");		
		String url = baseUrl + findAll;		
		Message message = new Message();	
		message.setRequestMethod(url);
		message.setAccessToken("token");
		message = template.postForEntity(url, message, Message.class).getBody();
		return message == null ? null : message.getMessageBody();
	}

}
