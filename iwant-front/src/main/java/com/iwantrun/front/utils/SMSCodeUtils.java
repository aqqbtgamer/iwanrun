package com.iwantrun.front.utils;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;

/**
 * @author user
 */
public class SMSCodeUtils {
	/**
	 * 存到cookie的加过密的短信验证码的key值前缀
	 */
	public static final String COOKIE_ENCRYPTED_SMS_CODE_KEY = "EncryptedSMSCode_";
	/**
	 * 配置文件的短信验证码Cookie有效期的Key值
	 */
	public static final String COOKIE_EXPIRY_SMS_CODE_KEY = "smsCode.cookie.expiry";
	
	/**
	 * 生成的加过密的短信验证码写进cookie
	 * 
	 * @param encryptedSMSCode
	 * @param key
	 * @param servletResponse
	 * @param environment
	 */
	public static void addCookie(String encryptedSMSCode, String loginId, HttpServletResponse response,
			Environment environment) {
		if (encryptedSMSCode != null) {
			String expirty = environment.getProperty(COOKIE_EXPIRY_SMS_CODE_KEY);
			CookieUtils.addCookie(expirty, COOKIE_ENCRYPTED_SMS_CODE_KEY + loginId, encryptedSMSCode, response);
		}
	}

}
