package com.iwantrun.front.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.front.transfer.SMSCodeRequest;
import com.iwantrun.front.transfer.SMSCodeResponse;

@Service
public class SMSCodeService {

	private Logger logger = LoggerFactory.getLogger(SMSCodeService.class);

	@Autowired
	private Environment environment;
	@Autowired
	private RestTemplate template;

	public SMSCodeResponse getSMSCode(SMSCodeRequest param) {
		String get = environment.getProperty("application.smsCode.get");
		String baseUrl = environment.getProperty("app.server");

		String url = baseUrl + get;

		logger.info("获取短信验证码-url：{}，参数：{}", url, param);

		try {

			return template.postForEntity(url, param, SMSCodeResponse.class).getBody();

		} catch (Exception e) {
			logger.info("获取短信验证码异常：{}", e);
		}

		return new SMSCodeResponse();
	}

}
