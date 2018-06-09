package com.iwantrun.front.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwantrun.front.service.SMSCodeService;
import com.iwantrun.front.transfer.SMSCodeRequest;
import com.iwantrun.front.transfer.SMSCodeResponse;

/**
 * @author user 短信验证码获取和校验
 */
@Controller
@RequestMapping("smsCode")
public class SMSCodeController {

	private Logger logger = LoggerFactory.getLogger(SMSCodeController.class);

	@Autowired
	SMSCodeService service;

	@RequestMapping("/getSMSCode")
	@ResponseBody
	public SMSCodeResponse getSMSCode(@RequestBody SMSCodeRequest param) {

		logger.info("开始获取短信验证码，参数：{}", param);

		SMSCodeResponse response = service.getSMSCode(param);

		logger.info("获取短信验证码结束，结果：{}", response);

		// 这里不能把验证码发送到前端
		response.setSmsCode(null);

		return response;
	}
}
