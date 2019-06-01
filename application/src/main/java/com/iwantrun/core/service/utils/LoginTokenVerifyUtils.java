package com.iwantrun.core.service.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class LoginTokenVerifyUtils {
	
	static Logger logger = LoggerFactory.getLogger(LoginTokenVerifyUtils.class);
	
	public static boolean verifyLoginToken(String sessionId,String currentUser,String loginToken) {
		try {
			logger.info("sessionId :[{}]",sessionId);
			logger.info("sessionId :[{}]",currentUser);
			//1.verify not null
			if(StringUtils.isEmpty(loginToken)) {
				return false;
			}
			//2.verify good format
			String decodeJson = AESUtils.decode(loginToken);
			if(decodeJson == null) {
				return false ;
			}
			//3.verify good format
			JSONObject token = (JSONObject) JSONValue.parse(decodeJson);			
			String decodeSessionId = token.getAsString("tokenSession");
			logger.info("decodeSessionId :[{}]",decodeSessionId);
			String decodeTokenHolder = token.getAsString("tokenHolder");
			logger.info("decodeTokenHolder :[{}]",decodeTokenHolder);
			//3.verify specific field correct
			if(!decodeSessionId.equals(sessionId) || !decodeTokenHolder.equals(currentUser)) {
				return false;
			}
			//4 verify time validate			
			long time = System.currentTimeMillis();
			logger.info("time :[{}]",time);
			long decodeValidTime = Long.parseLong(token.getAsString("tokenMaxTime"));
			logger.info("decodeValidTime :[{}]",decodeValidTime);
			if(time > decodeValidTime ) {
				return false;
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
