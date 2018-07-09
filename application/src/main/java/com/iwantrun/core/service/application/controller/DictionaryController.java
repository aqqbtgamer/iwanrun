package com.iwantrun.core.service.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.core.service.application.annotation.NeedTokenVerify;
import com.iwantrun.core.service.application.domain.Dictionary;
import com.iwantrun.core.service.application.service.DictionaryService;
import com.iwantrun.core.service.application.transfer.Message;
import com.iwantrun.core.service.application.transfer.SimpleMessageBody;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@RestController
public class DictionaryController {
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@RequestMapping("/application/dictionary/getPages")
	public Message getPages(@RequestBody Message message) {
		message.setMessageBody(dictionaryService.getPages());
		return message;
	}
	
	
	@RequestMapping("/application/dictionary/getTabs")
	public Message getTabs(@RequestBody Message message) {
		String json = message.getMessageBody();
		JSONObject obj = (JSONObject) JSONValue.parse(json);
		message.setMessageBody(dictionaryService.getTabs(obj.getAsString("name")));
		return message;
	}
	
	@RequestMapping("/application/dictionary/findByCode")
	public Message findByCode(@RequestBody Message message) {
		String json = message.getMessageBody();
		JSONObject obj = (JSONObject) JSONValue.parse(json);
		String filedId = obj.getAsString("used_field");
		String name = obj.getAsString("name");
		List<Dictionary> resultList = dictionaryService.findByCondition(filedId, name);
		message.setMessageBody(JSONValue.toJSONString(resultList));
		return message;
	}
	
	@RequestMapping("/application/dictionary/findByAssign")
	public Message findByAssign(@RequestBody Message message) {
		String json = message.getMessageBody();
		JSONObject obj = (JSONObject) JSONValue.parse(json);
		String assignTo = obj.getAsString("assignTo");
		List<Dictionary> resultList = dictionaryService.findByAssign(assignTo);
		message.setMessageBody(JSONValue.toJSONString(resultList));
		return message;
	}
	
	@RequestMapping("/application/dictionary/add")
	@NeedTokenVerify
	public Message add(@RequestBody Message message) {
		String json = message.getMessageBody();
		Dictionary obj = JSONValue.parse(json, Dictionary.class);		
		Dictionary dbResult =  dictionaryService.add(obj);
		message.setMessageBody(JSONValue.toJSONString(dbResult));
		return message;
	}
	
	
	@RequestMapping("/application/dictionary/delete")
	@NeedTokenVerify
	public Message delete(@RequestBody Message message) {
		String id = message.getMessageBody();	
		dictionaryService.delete(Integer.parseInt(id));
		SimpleMessageBody body = new SimpleMessageBody();
		body.setSuccessful(true);
		message.setMessageBody(JSONValue.toJSONString(body));
		return message;
	}
	
	@RequestMapping("/application/dictionary/findByName")
	public Message findByName(@RequestBody Message message) {
		String param = message.getMessageBody();
		if(!StringUtils.isEmpty(param)) {
			List<Dictionary> resultList = dictionaryService.findDictionaryByName(param);
			message.setMessageBody(JSONValue.toJSONString(resultList));
			return message;
		}
		return null;
	}
}
