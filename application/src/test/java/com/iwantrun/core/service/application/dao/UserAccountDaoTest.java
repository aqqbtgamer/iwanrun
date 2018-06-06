package com.iwantrun.core.service.application.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iwantrun.core.service.application.domain.UserAccount;
import com.iwantrun.core.service.utils.JSONUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.properties"})
@SpringBootTest
public class UserAccountDaoTest {
	
	@Autowired
	private UserAccountDao dao ;

	@Test
	public void testFindByRole() {
		Pageable page = PageRequest.of(1, 10, Sort.Direction.ASC,"id");
		Page<UserAccount> result = dao.findByRole(null, page);
		System.out.println(JSONUtils.objToJSON(result));
	}

	@Test
	public void testFindByUsernameLikeAndMobileNumberLike() {
		Pageable page = PageRequest.of(1, 10, Sort.Direction.ASC,"id");
		Page<UserAccount> result = dao.findByUsernameLikeAndMobileNumberLike("admin","",page);
		System.out.println(JSONUtils.objToJSON(result));
	}

}
