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
public class PurChaseAccountService {
	
	@Autowired  
    private Environment env;  
	
	@Autowired
    private RestTemplate restTemplate;
	
	public String findPurchaseUser(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		List<String> paramList = FormDataUtils.stringArray2List(new String[] {
				"role",
				"mobileNumber",
				"name",
				"loginId",
				"pageIndex"
		});
		String json = FormDataUtils.formData2Json(request,paramList);
		String postUrl = env.getProperty("application.purchaseUser.find");
		String baseUrl = env.getProperty("application.serverbase");
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(json);
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);	
		return response == null ? null : response.getBody().getMessageBody();
	}

	public String queryPurchaseUser(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		List<String> paramList = FormDataUtils.stringArray2List(new String[] {
				"pageIndex",
				"obj"
		});
		JSONObject json = FormDataUtils.formData2JsonObj(request,paramList);
		JSONObject obj  = (JSONObject) JSONValue
				.parse(json.getAsString("obj"));
		json.put("obj", obj);
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(json.toJSONString());
		String postUrl = env.getProperty("application.purchaseUser.findByExample");
		String baseUrl = env.getProperty("application.serverbase");
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);	
		return response == null ? null : response.getBody().getMessageBody();
	}

	public String addPurchaseUser(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		List<String> paramList = FormDataUtils.stringArray2List(new String[] {
				"name",
				"role",
				"loginId",
				"mobileNumber",
				"password",
				"wec",
				"aliPayId",
				"email",
				"contractMobile",
				"thirdPartyId1",
				"thirdPartyId2",
				"thirdPartyId3",
				"companyTypeId",
				"companySizeId",
				"companyName",
				"imgManage[]",
				"gender[]"
		});
		String json = FormDataUtils.formData2Json(request,paramList);
		String postUrl = env.getProperty("application.purchaseUser.add");
		String baseUrl = env.getProperty("application.serverbase");
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(json);
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);		
		return response == null ? null : response.getBody().getMessageBody();
	}

	public String getById(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		String id = request.getParameter("id");
		JSONObject requestObj = new JSONObject();
		requestObj.put("id", id);
		String json = requestObj.toJSONString();
		String postUrl = env.getProperty("application.purchaseUser.get");
		String baseUrl = env.getProperty("application.serverbase");
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(json);
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);		
		return response == null ? null : response.getBody().getMessageBody();
	}

	public String modify(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		String id = request.getParameter("id");
		List<String> paramList = FormDataUtils.stringArray2List(new String[] {
				"name",
				"role",
				"loginId",
				"mobileNumber",
				"password",
				"wec",
				"aliPayId",
				"email",
				"contractMobile",
				"thirdPartyId1",
				"thirdPartyId2",
				"thirdPartyId3",
				"companyTypeId",
				"companySizeId",
				"companyName",
				"imgManage[]",
				"gender[]"
		});
		String json = FormDataUtils.formData2Json(request,paramList);
		JSONObject requestObj = new JSONObject();
		requestObj.put("id", id);
		requestObj.put("json", json);
		String postUrl = env.getProperty("application.purchaseUser.modify");
		String baseUrl = env.getProperty("application.serverbase");
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(requestObj.toJSONString());
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);		
		return response == null ? null : response.getBody().getMessageBody();
	}

	public String apply(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		String id = request.getParameter("id");
		Integer verified = 1 ;
		JSONObject requestObj = new JSONObject();
		requestObj.put("id", id);
		requestObj.put("verified", verified);
		String postUrl = env.getProperty("application.purchaseUser.apply");
		String baseUrl = env.getProperty("application.serverbase");
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(requestObj.toJSONString());
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);		
		return response == null ? null : response.getBody().getMessageBody();
	}

	public String delete(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		List<String> paramList = FormDataUtils.stringArray2List(new String[] {
			"id",	
			"id[]"	
		});
		String json = FormDataUtils.formData2Json(request,paramList);
		String postUrl = env.getProperty("application.purchaseUser.delete");
		String baseUrl = env.getProperty("application.serverbase");
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(json);
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);		
		return response == null ? null : response.getBody().getMessageBody();
	}

}
