package com.iwantrun.core.service.application.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.core.service.application.annotation.NeedTokenVerify;
import com.iwantrun.core.service.application.domain.UserAccount;
import com.iwantrun.core.service.application.service.LoginTokenService;
import com.iwantrun.core.service.application.service.UserAccountService;
import com.iwantrun.core.service.application.transfer.Message;
import com.iwantrun.core.service.utils.AESUtils;
import com.iwantrun.core.service.utils.Md5Utils;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@RestController
public class LoginTokenGenerationController {
	
	Logger logger = LoggerFactory.getLogger(LoginTokenGenerationController.class);
	
	@Autowired
	private UserAccountService userService;
	
	@Autowired
	private LoginTokenService logintokenService;
	
	
	@RequestMapping(value="/application/loginToken")
	public Message generateLoginToken(@RequestBody Message message ) {
		Message response = new Message();
		response.setAccessToken(message.getAccessToken());
		response.setRequestMethod(message.getRequestMethod());
		//解析请求
		JSONObject body = (JSONObject) JSONValue.parse(message.getMessageBody());
		//校验用户存在
		List<UserAccount> accountList =userService
				.findUserByName(body.getAsString("username")
						,body.getAsNumber("role").intValue());
		if(accountList == null || accountList.size() > 1) {
			logger.warn("no suitable account can be loaded");
		}else {
			UserAccount account = accountList.get(0);
			String password = account.getPassword();
			String inputPassword = AESUtils.decode(body.getAsString("certification"));
			if(Md5Utils.verify(inputPassword, password)) {
				//生成token
				String token = logintokenService.tokenGenerate(body.getAsString("username"), body.getAsString("sessionId"));
				response.setMessageBody(token);
			}else {
				logger.warn("password not correct with input");
			}
		}
		return response ;
	}
	
	@RequestMapping(value="/application/verifyToken")
	@NeedTokenVerify
	public String testTokenVerify() {
		return "only test dummy";
	}

}
