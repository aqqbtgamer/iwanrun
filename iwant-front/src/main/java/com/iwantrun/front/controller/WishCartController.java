package com.iwantrun.front.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.utils.CookieUtils;

import net.minidev.json.JSONObject;

@Controller
@RequestMapping("wishcart")
public class WishCartController {
	
	Logger logger = LoggerFactory.getLogger(WishCartController.class);
	
	@Value("${app.server}")
	private String baseUrl ;
	
	@Autowired
	private RestTemplate template;
	
	@RequestMapping("add")
	@ResponseBody
	public String add(@RequestBody JSONObject requestObj,HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(requestObj.toJSONString());
		String postUrl = baseUrl+"/application/wishcart/add";
		message.setRequestMethod(postUrl); 
		ResponseEntity<Message> response = template.postForEntity(postUrl, message, Message.class);
		return response.getBody().getMessageBody();
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public String delete(@RequestBody JSONObject requestObj,HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(requestObj.toJSONString());
		String postUrl = baseUrl+"/application/wishcart/delete";
		message.setRequestMethod(postUrl); 
		ResponseEntity<Message> response = template.postForEntity(postUrl, message, Message.class);
		return response.getBody().getMessageBody();
	}
	
	
	@RequestMapping("find")
	@ResponseBody
	public String find(@RequestBody JSONObject requestObj,HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(requestObj.toJSONString());
		String postUrl = baseUrl+"/application/wishcart/find";
		message.setRequestMethod(postUrl); 
		ResponseEntity<Message> response = template.postForEntity(postUrl, message, Message.class);
		return response.getBody().getMessageBody();
	}
	
	@RequestMapping("query")
	@ResponseBody
	public String query(@RequestBody JSONObject requestObj,HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(requestObj.toJSONString());
		String postUrl = baseUrl+"/application/wishcart/query";
		message.setRequestMethod(postUrl); 
		ResponseEntity<Message> response = template.postForEntity(postUrl, message, Message.class);
		return response.getBody().getMessageBody();
	}
	
	
	@RequestMapping("findOne")
	@ResponseBody
	public String findOne(@RequestBody JSONObject requestObj,HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(requestObj.toJSONString());
		String postUrl = baseUrl+"/application/wishcart/findOne";
		message.setRequestMethod(postUrl); 
		ResponseEntity<Message> response = template.postForEntity(postUrl, message, Message.class);
		return response.getBody().getMessageBody();
	}

}
