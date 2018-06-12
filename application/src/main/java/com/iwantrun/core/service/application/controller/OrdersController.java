package com.iwantrun.core.service.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.core.service.application.service.OrdersService;
import com.iwantrun.core.service.application.transfer.Message;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@RestController
@RequestMapping("application/orders")
public class OrdersController {
	
	@Autowired
	private OrdersService orderService ;
	
	@RequestMapping("findAll")
	public Message findAll(@RequestBody Message message) {
		String requestJson = message.getMessageBody();
		JSONObject requestObj = (JSONObject) JSONValue.parse(requestJson);
		String result = orderService.findAll(requestObj);
		message.setMessageBody(result);
		return message;
	}

}
