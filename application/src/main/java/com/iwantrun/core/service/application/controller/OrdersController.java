package com.iwantrun.core.service.application.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.core.service.application.annotation.NeedTokenVerify;
import com.iwantrun.core.service.application.domain.Dictionary;
import com.iwantrun.core.service.application.domain.Orders;
import com.iwantrun.core.service.application.service.DictionaryService;
import com.iwantrun.core.service.application.service.OrdersService;
import com.iwantrun.core.service.application.transfer.Message;
import com.iwantrun.core.service.utils.EntityDictionaryConfigUtils;
import com.iwantrun.core.service.utils.JSONUtils;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@RestController
@RequestMapping("application/orders")
public class OrdersController {
	
	@Autowired
	private OrdersService orderService ;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@RequestMapping("findAll")
	public Message findAll(@RequestBody Message message) {
		String requestJson = message.getMessageBody();
		JSONObject requestObj = (JSONObject) JSONValue.parse(requestJson);
		String result = orderService.findAll(requestObj);
		message.setMessageBody(result);
		return message;
	}
	
	@RequestMapping("findByExample")
	public Message findByExample(@RequestBody Message message) {
		String requestJson = message.getMessageBody();
		JSONObject requestObj = (JSONObject) JSONValue.parse(requestJson);
		String result = orderService.findByExample(requestObj);
		message.setMessageBody(result);
		return message;
	}
	
	@RequestMapping("get")
	public Message get(@RequestBody Message message) {
		String requestJson = message.getMessageBody();
		JSONObject requestObj = (JSONObject) JSONValue.parse(requestJson);
		Map<String,Object> resultMap = orderService.get(requestObj);
		Orders order = (Orders) resultMap.get("orders");
		Map<String,Dictionary> dictionnaryMap = EntityDictionaryConfigUtils.getDictionaryMaping(new Orders());
		if(order != null) {
			resultMap.put("orders", dictionaryService.dictionaryFilter(order, dictionnaryMap));
		}
		message.setMessageBody(JSONUtils.objToJSON(resultMap));
		return message;
	}
	
	@RequestMapping("simpleGet")
	public Message simpleGet(@RequestBody Message message) {
		String requestJson = message.getMessageBody();
		JSONObject requestObj = (JSONObject) JSONValue.parse(requestJson);
		Orders order = orderService.simpleGet(requestObj);
		message.setMessageBody(JSONUtils.objToJSON(order));
		return message;
	}
	
	@RequestMapping("getOrderMessage")
	public Message getOrderMessage(@RequestBody Message message) {
		String requestJson = message.getMessageBody();
		JSONObject requestObj = (JSONObject) JSONValue.parse(requestJson);
		String result = orderService.getOrderMessage(requestObj);
		message.setMessageBody(result);
		return message;
	}
	
	@RequestMapping("assign")
	@NeedTokenVerify
	public Message update(@RequestBody Message message) {
		String requestJson = message.getMessageBody();
		JSONObject requestObj = (JSONObject) JSONValue.parse(requestJson);
		String result = orderService.assignOrder(requestObj);
		message.setMessageBody(result);
		return message;
	}
	
	@RequestMapping("close")
	@NeedTokenVerify
	public Message close(@RequestBody Message message) {
		String requestJson = message.getMessageBody();
		JSONObject requestObj = (JSONObject) JSONValue.parse(requestJson);
		String result = orderService.close(requestObj);
		message.setMessageBody(result);
		return message;
	}
	
	@RequestMapping("submit")
	@NeedTokenVerify
	public Message submit(@RequestBody Message message) {
		String requestJson = message.getMessageBody();
		JSONObject requestObj = (JSONObject) JSONValue.parse(requestJson);
		String result = orderService.add(requestObj);
		message.setMessageBody(result);
		return message;
	}

}
