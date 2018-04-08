package com.iwantrun.core.service.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.iwantrun.core.service.application.transfer.Token;
import com.iwantrun.core.service.utils.AESUtils;
import com.iwantrun.core.service.utils.StringFilterUtils;

import net.minidev.json.JSONValue;

@Service
public class LoginTokenService {
	
	@Autowired  
    private Environment env;
	
	public String tokenGenerate(String username,String sessionId) {
		Long tokenvalidateTime = Long.parseLong(env.getProperty("token.validate.period"));
		Token token = new Token();
		token.setTokenHolder(username);
		token.setTokenSession(sessionId);
		long currentTime = System.currentTimeMillis();
		token.setTokenMaxTime(currentTime+tokenvalidateTime);
		String json = JSONValue.toJSONString(token);
		return StringFilterUtils.replaceBlank(AESUtils.encode(json));
	}

}
