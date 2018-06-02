package com.iwantrun.core.service.application.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iwantrun.core.service.application.domain.PurchaserAccount;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.properties"})
@SpringBootTest
public class PurchaserAccountDaoTest {
	
	@Autowired
	private PurchaserAccountDao testDao;

	@Test
	public void test() {
		String testMobile = "18018336171";
		List<PurchaserAccount> resultList = testDao.findByMobileNumber(testMobile);
		assertEquals(1,resultList.size());
	}

}
