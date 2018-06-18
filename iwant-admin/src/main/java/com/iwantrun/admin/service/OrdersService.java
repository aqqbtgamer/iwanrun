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
import net.minidev.json.JSONValue;

@Service
public class OrdersService {
	
	@Autowired  
    private Environment env;  
	
	@Autowired
    private RestTemplate restTemplate;
	
	public String findAll(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		String pageIndex = request.getParameter("pageIndex");
		JSONObject requestObj = new JSONObject();
		requestObj.put("pageIndex", pageIndex);
		String postUrl = env.getProperty("application.orders.findAll");
		String baseUrl = env.getProperty("application.serverbase");
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(requestObj.toJSONString());
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);	
		return response == null ? null : response.getBody().getMessageBody();
	}

	public String findByExample(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		List<String> paramList = FormDataUtils.stringArray2List(new String[] {
				"obj",
				"pageIndex"
		});
		JSONObject json = FormDataUtils.formData2JsonObj(request,paramList);
		JSONObject obj  = (JSONObject) JSONValue
				.parse(json.getAsString("obj"));
		json.put("obj", obj);
		String postUrl = env.getProperty("application.orders.findByExample");
		String baseUrl = env.getProperty("application.serverbase");
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(json.toJSONString());
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);	
		return response == null ? null : response.getBody().getMessageBody();
	}

	public String get(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		String id = request.getParameter("id");
		JSONObject requestObj = new JSONObject();
		requestObj.put("id", id);
		String json = requestObj.toJSONString();
		String postUrl = env.getProperty("application.orders.get");
		String baseUrl = env.getProperty("application.serverbase");
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(json);
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);		
		return response == null ? null : response.getBody().getMessageBody();
	}

}
