package com.iwantrun.core.service.application.dao;

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
public class JPQLEnableRepositoryTest {
	
	@Autowired
	private JPQLEnableRepository<UserAccount> repository;
	
	@Test
	public void testA() {
		List<UserAccount> result = repository.findByJPQLPage("select account from UserAccount account order by account.id desc", UserAccount.class, 1, 10);
		for(UserAccount account : result) {
			System.out.println("----------------test result --------------"+account.getId());
		}
	}

}
