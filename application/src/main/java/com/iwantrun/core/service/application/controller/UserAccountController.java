package com.iwantrun.core.service.application.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.core.service.application.annotation.NeedTokenVerify;
import com.iwantrun.core.service.application.service.UserAccountService;
import com.iwantrun.core.service.application.transfer.Message;
import com.iwantrun.core.service.application.transfer.PageDomianRequest;
import com.iwantrun.core.service.utils.JSONUtils;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@RestController
@RequestMapping(path="application/userAccount")
public class UserAccountController {
	
	@Autowired
	private UserAccountService userService;
	
	@RequestMapping(path="findAll")
	public Message findAll(@RequestBody Message message) {
		String dataJson = message.getMessageBody();
		PageDomianRequest example = JSONUtils.jsonToObj(dataJson, PageDomianRequest.class);
		String result = userService.findAllPaged(example);
		message.setMessageBody(result);
		return message;
	}
	
	@RequestMapping(path="findByExample")
	public Message findByExample(@RequestBody Message message) {
		String dataJson = message.getMessageBody();
		PageDomianRequest example = JSONUtils.jsonToObj(dataJson, PageDomianRequest.class);
		String result = userService.findByExamplePaged(example);
		message.setMessageBody(result);
		return message;
	}
	
	@RequestMapping(path="add")
	@NeedTokenVerify
	public Message add(@RequestBody Message message) {
		String dataJson = message.getMessageBody();
		JSONObject obj = (JSONObject) JSONValue.parse(dataJson);
		String result = userService.addUserAccount(obj);
		message.setMessageBody(result);
		return message;
	}
	
	@RequestMapping(path="get")
	public Message get(@RequestBody Message message) {
		String dataJson = message.getMessageBody();
		JSONObject obj = (JSONObject) JSONValue.parse(dataJson);
		String result = userService.get(obj);
		message.setMessageBody(result);
		return message;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(path="modify")
	@NeedTokenVerify
	public Message modify(@RequestBody Message message) {
		String dataJson =  message.getMessageBody();
		JSONObject requestObj = (JSONObject) JSONValue.parse(dataJson);
		String id = requestObj.getAsString("id");
		Map<String,Object> paramsMap = JSONUtils.jsonToObj(requestObj.getAsString("json"),Map.class);
		String result = userService.modify(id,paramsMap);
		message.setMessageBody(result);
		return message ;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	@NeedTokenVerify
	public Message delete(@RequestBody Message message) {
		String dataJson =  message.getMessageBody();
		JSONObject requestObj = (JSONObject) JSONValue.parse(dataJson);
		String result = userService.delete(requestObj);
		message.setMessageBody(result);
		return message;
	}
}
