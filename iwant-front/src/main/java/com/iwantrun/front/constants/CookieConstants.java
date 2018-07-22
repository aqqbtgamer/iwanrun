package com.iwantrun.front.constants;

public interface CookieConstants {
	/**
	 * 配置文件里面的AccessToken有效期的key值
	 */
	String COOKIE_EXPIRY_ACCESS_TOKEN_KEY = "accessToken.cookie.expiry";

	/**
	 * 配置文件里面的临时AccessToken有效期的key值
	 */
	String COOKIE_TEMP_EXPIRY_ACCESS_TOKEN_KEY = "accessToken.temp.cookie.expiry";

	/**
	 * 存入cookie的key值
	 */
	String COOKIE_ACCESS_TOKEN_KEY = "accessToken";
	String COOKIE_LOGIN_ID_KEY = "loginId";
}
