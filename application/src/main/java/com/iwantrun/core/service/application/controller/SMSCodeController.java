package com.iwantrun.core.service.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwantrun.core.service.application.transfer.SMSCodeRequest;
import com.iwantrun.core.service.application.transfer.SMSCodeResponse;
import com.iwantrun.core.service.utils.SMSCodeUtils;

/**
 * @author user 短信验证码获取和校验
 */
@Controller
@RequestMapping("application/smsCode")
public class SMSCodeController {

	private Logger logger = LoggerFactory.getLogger(SMSCodeController.class);

	/**
	 * 配置文件里面的属性完成设值后的SMSCodeRequest对象
	 */
	@Autowired
	SMSCodeRequest request;

	@RequestMapping("/getSMSCode")
	@ResponseBody
	public SMSCodeResponse getSMSCode(@RequestBody SMSCodeRequest param) {

		logger.info("开始获取短信验证码，参数：{}", param);

		// 校验参数
		String validate = SMSCodeUtils.validate(param);

		logger.info("获取短信验证码，参数校验结果：{}", validate);

		// 这个接口相应对象
		SMSCodeResponse response = new SMSCodeResponse();
		if (validate != null) {
			response.setMessage(validate);
		} else {

			String mobile = param.getMobile();
			request.setMobile(mobile);

			logger.info("开始发送短信验证码，参数：{}", request);

			// 发送短信后的响应对象
			response = SMSCodeUtils.getSMSCode(request);
		}

		logger.info("获取短信验证码结束，结果：{}", response);

		return response;
	}
}
