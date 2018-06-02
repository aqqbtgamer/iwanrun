package com.iwantrun.front.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.front.domain.PurchaserAccount;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.transfer.PurchaserAccountRequest;
import com.iwantrun.front.utils.JSONUtils;

@Service
public class PurchaserAccountService {
	@Autowired
	private Environment environment;
	@Autowired
	private RestTemplate template;

	/**
	 * 采购用户注册
	 * 
	 * @param purchaser
	 * @return PurchaserAccount
	 */
	public Message register(PurchaserAccountRequest purchaser) {
		String register = environment.getProperty("application.purchaserAccount.register");
		String baseUrl = environment.getProperty("app.server");
		
		String url = baseUrl + register;

		Message message = new Message();
		message.setMessageBody(JSONUtils.objToJSON(purchaser));
		message.setRequestMethod(url);
		return template.postForEntity(url, message, Message.class).getBody();
	}
	/**
	 * 采购用户登录
	 * 
	 * @param account
	 * @return
	 */
	public Message login(PurchaserAccount account) {
		String login = environment.getProperty("application.purchaserAccount.login");
		String baseUrl = environment.getProperty("app.server");
		String url = baseUrl + login;

		Message message = new Message();
		message.setMessageBody(JSONUtils.objToJSON(account));
		message.setRequestMethod(url);
		return template.postForEntity(url, message, Message.class).getBody();
	}

}
