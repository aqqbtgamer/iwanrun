package com.iwantrun.front.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.front.service.OrdersService;
import com.iwantrun.front.utils.CookieUtils;

@RestController
@RequestMapping("orders")
public class OrdersController {
	
	private OrdersService orderService ;
	
	@RequestMapping("submit")
	public Map<String,Object> submitOrders(HttpServletRequest request){		
		String token = CookieUtils.getLoginToken(request);
		String requestJson = request.getParameter("requestJson");
		return orderService.submitOrder(requestJson, token);
	}
}
