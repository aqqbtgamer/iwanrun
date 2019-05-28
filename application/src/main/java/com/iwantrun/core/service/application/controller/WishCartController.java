package com.iwantrun.core.service.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.core.service.application.annotation.NeedTokenVerify;
import com.iwantrun.core.service.application.service.WishCartService;
import com.iwantrun.core.service.application.transfer.Message;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@RestController
@RequestMapping("/application/wishcart")
public class WishCartController {
	
	Logger logger = LoggerFactory.getLogger(WishCartController.class);
	
	@Autowired
	private WishCartService service ;
	
	@RequestMapping("add")
	@NeedTokenVerify
	public Message addWishCart(@RequestBody Message message) {
		String requestBody = message.getMessageBody();
		if(requestBody == null ) {
			return message ;
		}else {
			JSONObject requestJson = (JSONObject) JSONValue.parse(requestBody);
			String loginId = requestJson.getAsString("loginId");
			String type = requestJson.getAsString("type");
			int typeId = requestJson.getAsNumber("id").intValue();
			JSONObject resultJson = service.add(loginId,type,typeId);
			message.setMessageBody(resultJson.toJSONString());
			return message ;
		}		
	}
	
	
	@RequestMapping("delete")
	@NeedTokenVerify
	public Message deleteWishCart(@RequestBody Message message) {
		String requestBody = message.getMessageBody();
		if(requestBody == null ) {
			return message ;
		}else {
			JSONObject requestJson = (JSONObject) JSONValue.parse(requestBody);
			int id = requestJson.getAsNumber("id").intValue();
			JSONObject resultJson = service.delete(id);
			message.setMessageBody(resultJson.toJSONString());
			return message ;
		}
	}
	
	
	@RequestMapping("find")	
	public Message findWishCart(@RequestBody Message message) {
		String requestBody = message.getMessageBody();
		if(requestBody == null ) {
			return message ;
		}else {
			JSONObject requestJson = (JSONObject) JSONValue.parse(requestBody);
			int id = requestJson.getAsNumber("id").intValue();
			String resultJson = service.findById(id);
			message.setMessageBody(resultJson);
			return message ;
		}
	}
	
	
	@RequestMapping("query")	
	public Message queryWishCart(@RequestBody Message message) {
		String requestBody = message.getMessageBody();
		if(requestBody == null ) {
			return message ;
		}else {
			JSONObject requestJson = (JSONObject) JSONValue.parse(requestBody);
			String loginId = requestJson.getAsString("loginId");
			String type = requestJson.getAsString("type");
			int pageIndex = requestJson.getAsNumber("pageIndex").intValue();
			int pageSize = requestJson.getAsNumber("pageSize").intValue();
			String resultJson = service.query(loginId, type, pageIndex, pageSize);
			message.setMessageBody(resultJson);
			return message ;
		}
	}

}
