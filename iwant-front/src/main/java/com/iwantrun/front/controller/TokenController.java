package com.iwantrun.front.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwantrun.front.constants.CookieConstants;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.utils.CookieUtils;
import com.iwantrun.front.utils.LoginTokenVerifyUtils;

import net.minidev.json.JSONObject;

@Controller
@RequestMapping("token")
public class TokenController {
	Logger logger = LoggerFactory.getLogger(TokenController.class);

	/**
	 * token 检查
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/verify")
	@ResponseBody
	public String verify(HttpServletRequest request) {
		Message result = new Message();
		String accessToken = CookieUtils.getCookieValue(CookieConstants.COOKIE_ACCESS_TOKEN_KEY, request);
		String loginId = CookieUtils.getCookieValue(CookieConstants.COOKIE_LOGIN_ID_KEY, request);
		logger.info("token 检查--获取到的token：{}--loginId", accessToken, loginId);
		JSONObject json = new JSONObject();
		try {
			if (!StringUtils.isEmpty(accessToken) && !StringUtils.isEmpty(loginId)) {
				boolean validToken = LoginTokenVerifyUtils.verifyLoginToken(request.getSession().getId(), loginId,
						accessToken);
				if (validToken) {
					json.put("token", "success");
				}
			}
		} catch (Exception e) {
			logger.error("getting input params error：{}", e);
		}
		logger.info("token 检查--结果：{}", json);
		return json.toJSONString();
	}
}
