package com.iwantrun.front.utils;

import static com.iwantrun.front.constants.CookieConstants.COOKIE_ACCESS_TOKEN_KEY;
import static com.iwantrun.front.constants.CookieConstants.COOKIE_LOGIN_ID_KEY;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * LoginToken's OPT
 * 
 * @author sanny
 */
public class LoginTokenUtils {
	private static Logger logger = LoggerFactory.getLogger(LoginTokenUtils.class);
	/**
	 * 获取Token
	 * 
	 * @param request
	 * @return
	 */
	public static String getToken(HttpServletRequest request) {
		return CookieUtils.getCookieValue(COOKIE_ACCESS_TOKEN_KEY, request);
	}

	/**
	 * 获取LoginId
	 * 
	 * @param request
	 * @return
	 */
	public static String getLoginId(HttpServletRequest request) {
		return CookieUtils.getCookieValue(COOKIE_LOGIN_ID_KEY, request);
	}

	/**
	 * Token检验
	 * 
	 * @param request
	 * @return
	 */
	public static boolean verifyLoginToken(HttpServletRequest request) {
		String accessToken = getToken(request);
		String loginId = getLoginId(request);
		String sessionId = request.getSession().getId();

		logger.info("token 检查--获取到的token：{}--loginId", accessToken, loginId);

		try {
			if (!StringUtils.isEmpty(accessToken) && !StringUtils.isEmpty(loginId)) {
				return LoginTokenVerifyUtils.verifyLoginToken(sessionId, loginId, accessToken);
			}
		} catch (Exception e) {
			logger.error("getting input params error：{}", e);
		}
		return false;
	}

}
