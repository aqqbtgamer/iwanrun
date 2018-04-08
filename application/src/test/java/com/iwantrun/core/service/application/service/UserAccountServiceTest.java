package com.iwantrun.core.service.application.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iwantrun.core.service.application.domain.UserAccount;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.properties"})
@SpringBootTest
public class UserAccountServiceTest {
	
	@Autowired
	private UserAccountService userService;

	@Test
	public void testFindAll() {
		if(userService == null) {
			fail("test setting not valid");
		}else {
			List<UserAccount> resultList = userService.findAll();
			assertNotNull(resultList);
			for(UserAccount account : resultList ) {
				System.out.println("find record :"+account.getUsername());
			}
		}
		
	}
	
	@Test
	public void testFindByName() {
		if(userService == null) {
			fail("test setting not valid");
		}else {
			List<UserAccount> resultList = userService.findUserByName("admin",1);
			assertNotNull(resultList);
			assertEquals(1, resultList.size());
			UserAccount item = resultList.get(0);
			assertEquals("admin",item.getUsername());
			List<UserAccount> resultList1 = userService.findUserByName("admin1",1);
			assertNotNull(resultList1);
			assertEquals(0, resultList1.size());
		}
	}

}
