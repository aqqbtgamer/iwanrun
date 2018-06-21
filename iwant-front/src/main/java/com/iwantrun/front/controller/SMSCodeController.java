package com.iwantrun.front.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwantrun.front.service.SMSCodeService;
import com.iwantrun.front.transfer.SMSCodeRequest;
import com.iwantrun.front.transfer.SMSCodeResponse;
import com.iwantrun.front.utils.SMSCodeUtils;

/**
 * @author user 短信验证码获取和校验
 */
@Controller
@RequestMapping("smsCode")
public class SMSCodeController {

	private Logger logger = LoggerFactory.getLogger(SMSCodeController.class);

	@Autowired
	SMSCodeService service;
	@Autowired
	Environment environment;
	
	@RequestMapping("/getSMSCode")
	@ResponseBody
	public SMSCodeResponse getSMSCode(HttpServletResponse servletResponse, @RequestBody SMSCodeRequest param) throws Exception {
		logger.info("开始获取短信验证码，参数：{}", param);
		SMSCodeResponse response = service.getSMSCode(param);
//		SMSCodeResponse response =new SMSCodeResponse();
//		response.setEncryptedSMSCode("GeyRL9viM485z2/GIYeE/w==");
		logger.info("获取短信验证码结束，结果：{}", response);
		// 这里不能把验证码发送到前端
		response.setSmsCode(null);
		String encryptedSMSCode = response.getEncryptedSMSCode();
		//把加过密的短信验证码写进cookie
		SMSCodeUtils.addCookie(encryptedSMSCode, param.getMobile(), servletResponse, environment);
		return response;
	}
}
