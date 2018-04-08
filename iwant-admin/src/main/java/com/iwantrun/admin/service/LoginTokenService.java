package com.iwantrun.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.admin.transfer.Message;

import net.minidev.json.JSONObject;

@Service
public class LoginTokenService {
	
	@Autowired  
    private Environment env;  
	
	@Autowired
    private RestTemplate restTemplate;

	
	public String getLoginTokenByCetification(String username, String certification,String sessionId) {
		String serviceUrl = env.getProperty("application.serverbase")+env.getProperty("application.loginToken.service");
		JSONObject object = new JSONObject();
		object.put("username",username);
		object.put("certification", certification);
		object.put("role", 1);
		object.put("sessionId", sessionId);
		Message message = new Message();
		message.setRequestMethod(env.getProperty("application.loginToken.service"));
		message.setMessageBody(object.toJSONString());
		ResponseEntity<Message> response = null;
		try {
			response = restTemplate.postForEntity(serviceUrl, message, Message.class);
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return response != null ?response.getBody().getMessageBody() : null;
	}
}
