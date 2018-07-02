package com.iwantrun.front.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwantrun.front.utils.LoginTokenUtils;

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
		JSONObject json = new JSONObject();
		boolean validToken = LoginTokenUtils.verifyLoginToken(request);
		if (validToken) {
			json.put("token", "success");
		}
		logger.info("token 检查--结果：{}", json);
		return json.toJSONString();
	}
}
