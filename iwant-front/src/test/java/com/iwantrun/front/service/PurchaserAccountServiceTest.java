package com.iwantrun.front.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.properties"})
@SpringBootTest
public class PurchaserAccountServiceTest {
	
	@Autowired
	private PurchaserAccountService service ;

	@Test
	public void testGetWeixinLoginUrl() {
		System.out.println(service.getWeixinLoginUrl());		
		try {
			String s = URLEncoder.encode("http://139.196.228.29:8088/iwantrun/index.html","utf-8");
			System.out.println(s);
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}
	}

}
