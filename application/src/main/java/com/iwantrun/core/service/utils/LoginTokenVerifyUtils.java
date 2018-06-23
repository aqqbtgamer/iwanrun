package com.iwantrun.core.service.utils;

import org.springframework.util.StringUtils;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class LoginTokenVerifyUtils {
	
	public static boolean verifyLoginToken(String sessionId,String currentUser,String loginToken) {
		try {
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
			String decodeTokenHolder = token.getAsString("tokenHolder");
			//3.verify specific field correct
			if(!decodeSessionId.equals(sessionId) || !decodeTokenHolder.equals(currentUser)) {
				return false;
			}
			//4 verify time validate
			long time = System.currentTimeMillis();
			long decodeValidTime = Long.parseLong(token.getAsString("tokenMaxTime"));
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
