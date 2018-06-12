package com.iwantrun.front.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.transfer.PurchaserAccountRequest;
import com.iwantrun.front.utils.JSONUtils;

@Service
public class DictionaryService {
	@Autowired
	private Environment environment;
	@Autowired
	private RestTemplate template;
	
	public Message queryDictionaryList(String name) {
		String findByName = environment.getProperty("application.dictionary.findByName");
		String baseUrl = environment.getProperty("app.server");
		
		String url = baseUrl + findByName;

		Message message = new Message();
		message.setMessageBody(name);
		message.setRequestMethod(url);
		return template.postForEntity(url, message, Message.class).getBody();
	}
}
