package com.iwantrun.core.service.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.core.service.application.transfer.SMSCodeResponse;
import com.iwantrun.core.service.utils.SMSCodeUtils;

import net.minidev.json.JSONObject;

/**
 * @author user 短信验证码获取和校验
 */
@Controller
@RequestMapping("application/smsCode")
public class SMSCodeController {
	@Autowired
	Environment environment;
	@Autowired
	RestTemplate template;

	@RequestMapping("/getSMSCode")
	public SMSCodeResponse getSMSCode(@RequestBody JSONObject paramObj) {
		SMSCodeResponse response = new SMSCodeResponse();
		String validate = SMSCodeUtils.validate(paramObj);
		if (validate != null) {
			response.setMsg(validate);
		} else {
			String mobile = paramObj.getAsString("mobile");
			response = SMSCodeUtils.getSMSCode(mobile, environment, template);
		}
		return response;
	}
}
