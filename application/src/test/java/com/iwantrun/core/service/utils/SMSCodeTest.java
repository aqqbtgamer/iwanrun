package com.iwantrun.core.service.utils;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Random;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class SMSCodeTest {
	@Test
	public void testName() throws Exception {
		System.out.println(RandomUtils.getSixCode());
	}
	@Test
	public void testName2() throws Exception {
		String url="http://dx1.xitx.cn:8888/sms.aspx?userid=6739&password=a123456&mobile=13*****86&action=send&content=【沐跑】您的验证码是131481";
		RestTemplate template=new RestTemplate();
		Object response =template.getForEntity(url, String.class).getBody();
		System.out.println(response);
	}
}
