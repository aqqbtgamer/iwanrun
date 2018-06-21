package com.iwantrun.core.service.application.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iwantrun.core.service.application.domain.PurchaserAccount;

import net.minidev.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.properties"})
@SpringBootTest
public class PurchaserAccountServiceTest {
	
	@Autowired
	private PurchaserAccountService service;

	@Test
	public void test() {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("pageIndex",1);
		jsonObj.put("name","老吴");
		String result = service.findPurchaseUserPaged(jsonObj);
		assertNotNull(result);
		System.out.println(result);
	}
	
	@Test
	public void update() throws Exception {
		PurchaserAccount account = new PurchaserAccount();
		account.setLoginId("13167126686");
		account.setPassword("a123123");
		service.modifyPwd(account);
	}
}
