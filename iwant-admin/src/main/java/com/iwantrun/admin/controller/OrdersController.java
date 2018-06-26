package com.iwantrun.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.admin.service.OrdersService;

@RestController
@RequestMapping("orders")
public class OrdersController {
	
	@Autowired
	private OrdersService orderService ;
	
	@RequestMapping("findAll")
	public String findAll(HttpServletRequest request) {
		return orderService.findAll(request);
	}
	
	@RequestMapping("findByExample")
	public String findByExample(HttpServletRequest request) {
		return orderService.findByExample(request);
	}
	
	
	@RequestMapping("get")
	public String get(HttpServletRequest request) {
		return orderService.get(request);
	}
	
	@RequestMapping("getSingle")
	public String getSingle(HttpServletRequest request) {
		return orderService.getSingle(request);
	}
	
	@RequestMapping("getMessage")
	public String getMessage(HttpServletRequest request) {
		return orderService.getMessage(request);
	}

}
