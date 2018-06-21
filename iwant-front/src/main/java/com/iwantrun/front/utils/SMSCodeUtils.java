package com.iwantrun.front.utils;

import javax.servlet.http.Cookie;
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
	 * 生成的加过密的短信验证码写进cookie
	 * 
	 * @param encryptedSMSCode
	 * @param key
	 * @param servletResponse
	 * @param environment
	 */
	public static void addCookie(String encryptedSMSCode, String loginId, HttpServletResponse servletResponse,
			Environment environment) {
		if (encryptedSMSCode != null) {
			// cookie生命周期
			int expiry = Integer.valueOf(environment.getProperty("smsCode.cookie.expiry"));
			Cookie cookie = new Cookie(COOKIE_ENCRYPTED_SMS_CODE_KEY + loginId, encryptedSMSCode);
			cookie.setMaxAge(expiry);
			cookie.setPath("/");
			servletResponse.addCookie(cookie);
		}
	}
	
}
