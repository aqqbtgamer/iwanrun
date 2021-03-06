package com.iwantrun.front.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.front.domain.Orders;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.utils.JSONUtils;

@Service
public class OrdersService {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private RestTemplate template;
	
	public Map<String,Object> submitOrder(String orderJson,String token){
		Map<String,Object> result = new HashMap<String,Object>();
		String orderSubmitUrl = environment.getProperty("application.orders.submit");
		String baseUrl = environment.getProperty("app.server");
		Message message = new Message();
		message.setMessageBody(orderJson);
		message.setAccessToken(token);
		message.setRequestMethod(baseUrl+orderSubmitUrl);
		Message response = template.postForEntity(baseUrl+orderSubmitUrl, message, Message.class).getBody();
		String resultOrder = response == null ? null : response.getMessageBody();
		if(resultOrder != null) {
			Orders order = JSONUtils.jsonToObj(resultOrder, Orders.class);
			result.put("orders", order);
			if(order.getId() > 0) {
				result.put("submitResult", true);
			}else {
				result.put("submitResult", false);
			}
		}else {
			result.put("submitResult", false);
		}
		return result;
	}
	public Message getOrderListByLoginId(String param) {
		String findByName = environment.getProperty("application.orders.getOrderListByLoginId");
		String baseUrl = environment.getProperty("app.server");
		
		String url = baseUrl + findByName;

		Message message = new Message();
		message.setMessageBody(param);
		message.setRequestMethod(url);
		message = template.postForEntity(url, message, Message.class).getBody();
		
		return message;
	}
	public Message get(String param) {
		String findByName = environment.getProperty("application.orders.get");
		String baseUrl = environment.getProperty("app.server");
		
		String url = baseUrl + findByName;

		Message message = new Message();
		message.setMessageBody(param);
		message.setRequestMethod(url);
		message = template.postForEntity(url, message, Message.class).getBody();
		
		return message;
	}
	public Message saveFileOrderAttach(String param) {
		String findByName = environment.getProperty("application.orders.saveFileOrderAttach");
		String baseUrl = environment.getProperty("app.server");
		
		String url = baseUrl + findByName;

		Message message = new Message();
		message.setMessageBody(param);
		message.setRequestMethod(url);
		message = template.postForEntity(url, message, Message.class).getBody();
		
		return message;
	}
	public Message orderResultClick(String param) {
		String findByName = environment.getProperty("application.orders.orderResultClick");
		String baseUrl = environment.getProperty("app.server");
		
		String url = baseUrl + findByName;

		Message message = new Message();
		message.setMessageBody(param);
		message.setRequestMethod(url);
		message = template.postForEntity(url, message, Message.class).getBody();
		
		return message;
	}
}
