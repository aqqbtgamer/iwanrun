package com.iwantrun.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.admin.transfer.Message;
import com.iwantrun.admin.utils.CookieUtils;
import com.iwantrun.admin.utils.FormDataUtils;

import net.minidev.json.JSONObject;

@Service
public class DictionaryService {
	
	@Autowired  
    private Environment env;  
	
	@Autowired
    private RestTemplate restTemplate;

	public String getPages(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		String postUrl = env.getProperty("application.dictionary.getPages");
		String baseUrl = env.getProperty("application.serverbase");
		Message message = new Message();
		message.setAccessToken(token);
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);		
		return response == null ? null : response.getBody().getMessageBody();
	}

	public String getTabs(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		String postUrl = env.getProperty("application.dictionary.getTabs");
		String baseUrl = env.getProperty("application.serverbase");
		Message message = new Message();
		message.setAccessToken(token);
		message.setRequestMethod(baseUrl+postUrl);
		String name = request.getParameter("name");
		JSONObject json = new JSONObject();
		json.put("name", name);
		message.setMessageBody(json.toJSONString());
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);		
		return response == null ? null : response.getBody().getMessageBody();
	}

	public String findByCode(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		String postUrl = env.getProperty("application.dictionary.findByCode");
		String baseUrl = env.getProperty("application.serverbase");
		Message message = new Message();
		message.setAccessToken(token);
		message.setRequestMethod(baseUrl+postUrl);
		String name = request.getParameter("name");
		String used_field = request.getParameter("used_field");
		JSONObject json = new JSONObject();
		json.put("name", name);
		json.put("used_field", used_field);
		message.setMessageBody(json.toJSONString());
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);		
		return response == null ? null : response.getBody().getMessageBody();
	}

	public String add(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		List<String> paramList = FormDataUtils.stringArray2List(new String[] {
				"name",
				"used_field",
				"code",
				"value"
		});
		String json = FormDataUtils.formData2Json(request,paramList);
		String postUrl = env.getProperty("application.dictionary.add");
		String baseUrl = env.getProperty("application.serverbase");
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(json);
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);		
		return response == null ? null : response.getBody().getMessageBody();
	}

	public String delete(HttpServletRequest request){
		String token = CookieUtils.getLoginToken(request);
		String id = request.getParameter("id");
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(id);
		String postUrl = env.getProperty("application.dictionary.delete");
		String baseUrl = env.getProperty("application.serverbase");
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);		
		return response == null ? null : response.getBody().getMessageBody();
	}

}
