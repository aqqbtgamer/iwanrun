package com.iwantrun.admin.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.admin.transfer.Message;
import com.iwantrun.admin.utils.CookieUtils;
import com.iwantrun.admin.utils.JSONUtils;

@Service
public class RelationService {
	
	@Autowired  
    private Environment env;  
	
	@Autowired
    private RestTemplate restTemplate;
	
	public String addRelation(HttpServletRequest request) {
		String oprationId = request.getParameter("oprationId");
		String type = request.getParameter("type");
		String target = request.getParameter("target");
		String[] targetIds = request.getParameterValues("ids[]");
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("oprationId", oprationId);
		paramsMap.put("type", type);
		paramsMap.put("target", target);
		paramsMap.put("targetIds", targetIds);
		Message message = new Message();
		message.setAccessToken(CookieUtils.getLoginToken(request));
		message.setMessageBody(JSONUtils.objToJSON(paramsMap));
		String postUrl = env.getProperty("application.relations.add");
		String baseUrl = env.getProperty("application.serverbase");	
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);	
		return response == null ? null : response.getBody().getMessageBody();
	}
	
	
	public String qureyBindings(HttpServletRequest request) {		
		@SuppressWarnings("unchecked")
		Map<String,String> objMap = JSONUtils.jsonToObj(request.getParameter("obj"), HashMap.class);		
		String type = objMap.get("type");
		String target = objMap.get("target");
		String oprationId = objMap.get("oprationId");
		String pageIndex = request.getParameter("pageIndex");
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("oprationId", oprationId);
		paramsMap.put("type", type);
		paramsMap.put("target", target);
		paramsMap.put("pageIndex", pageIndex);
		Message message = new Message();
		message.setAccessToken(CookieUtils.getLoginToken(request));
		message.setMessageBody(JSONUtils.objToJSON(paramsMap));
		String postUrl = env.getProperty("application.relations.queryBings");
		String baseUrl = env.getProperty("application.serverbase");	
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);	
		return response == null ? null : response.getBody().getMessageBody();
	}
	
	
	public String deleteBindings(HttpServletRequest request) {		
		String oprationId = request.getParameter("oprationId");
		String typeId = request.getParameter("typeId");
		String type = request.getParameter("type");
		String target = request.getParameter("target");
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("oprationId", oprationId);
		paramsMap.put("typeId", typeId);
		paramsMap.put("type", type);
		paramsMap.put("target", target);
		Message message = new Message();
		message.setAccessToken(CookieUtils.getLoginToken(request));
		message.setMessageBody(JSONUtils.objToJSON(paramsMap));
		String postUrl = env.getProperty("application.relations.deleteBindings");
		String baseUrl = env.getProperty("application.serverbase");
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);	
		return response == null ? null : response.getBody().getMessageBody();
	}

}
