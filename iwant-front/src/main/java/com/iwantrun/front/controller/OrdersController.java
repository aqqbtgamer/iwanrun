package com.iwantrun.front.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.front.domain.SearchDictionary;
import com.iwantrun.front.service.OrdersService;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.utils.CookieUtils;
import com.iwantrun.front.utils.JSONUtils;

@RestController
@RequestMapping("orders")
public class OrdersController {
	
	@Autowired
	private OrdersService orderService ;
	
	@RequestMapping("submit")
	public Map<String,Object> submitOrders(HttpServletRequest request){		
		String token = CookieUtils.getLoginToken(request);
		String requestJson = request.getParameter("requestJson");
		return orderService.submitOrder(requestJson, token);
	}
	
	@RequestMapping("/getOrderListByLoginId")
	@ResponseBody
	public String getOrderListByLoginId(@RequestBody String param) {

		try {
			Message result = orderService.getOrderListByLoginId(param);//查询出公共字典所有数据
			return result.getMessageBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	} 
	@RequestMapping("/get")
	@ResponseBody
	public String getOrderById(@RequestBody String param) {

		try {
			Message result = orderService.get(param);//查询出公共字典所有数据
			return result.getMessageBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	} 
	@RequestMapping("/saveFileOrderAttach")
	@ResponseBody
	public String saveFileOrderAttach(@RequestBody String param) {

		try {
			Message result = orderService.saveFileOrderAttach(param);//查询出公共字典所有数据
			return result.getMessageBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	} 
	@RequestMapping("/orderResultClick")
	@ResponseBody
	public String orderResultClick(@RequestBody String param) {

		try {
			Message result = orderService.orderResultClick(param);//查询出公共字典所有数据
			return result.getMessageBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	} 
}
