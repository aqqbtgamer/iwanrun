package com.iwantrun.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.admin.transfer.Message;
import com.iwantrun.admin.utils.CookieUtils;
import com.iwantrun.admin.utils.FormDataUtils;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@Service
public class WebSiteNewsService {
	
	@Value("${application.serverbase}")
	private String baseUrl ;
	@Value("${application.websiteNews.add}")
	private String addUrl;
	@Value("${application.websiteNews.findAll}")
	private String findAllUrl; 
	@Value("${application.websiteNews.delete}")
	private String deleteUrl; 
	@Value("${application.websiteNews.query}")
	private String queryUrl; 
	
	@Autowired
    private RestTemplate restTemplate;
	
	public String add(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		List<String> paramList = FormDataUtils.stringArray2List(new String[] {
				"newsContent"
		});
		String json = FormDataUtils.formData2Json(request,paramList);
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(json);
		message.setRequestMethod(baseUrl+addUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+addUrl, message, Message.class);		
		return response == null ? null : response.getBody().getMessageBody();
	}

	public String findAll(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		List<String> paramList = FormDataUtils.stringArray2List(new String[] {
				"pageIndex"
		});
		String json = FormDataUtils.formData2Json(request,paramList);
		Message message = new Message();
		message.setAccessToken(token);
		message.setRequestMethod(baseUrl+findAllUrl);
		message.setMessageBody(json);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+findAllUrl, message, Message.class);		
		return response == null ? null : response.getBody().getMessageBody();		
	}

	public String delete(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		List<String> paramList = FormDataUtils.stringArray2List(new String[] {
				"id"
		});
		String json = FormDataUtils.formData2Json(request,paramList);
		Message message = new Message();
		message.setAccessToken(token);
		message.setRequestMethod(baseUrl+deleteUrl);
		message.setMessageBody(json);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+deleteUrl, message, Message.class);		
		return response == null ? null : response.getBody().getMessageBody();	
	}

	public String queryByCondition(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		List<String> paramList = FormDataUtils.stringArray2List(new String[] {
				"obj",
				"pageIndex"
		});
		JSONObject json = FormDataUtils.formData2JsonObj(request,paramList);
		JSONObject obj  = (JSONObject) JSONValue
				.parse(json.getAsString("obj"));
		json.put("obj", obj);		
		Message message = new Message();
		message.setAccessToken(token);
		message.setRequestMethod(baseUrl+queryUrl);
		message.setMessageBody(json.toJSONString());
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+queryUrl, message, Message.class);		
		return response == null ? null : response.getBody().getMessageBody();
	}

}
