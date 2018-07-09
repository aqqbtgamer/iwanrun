package com.iwantrun.core.service.application.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iwantrun.core.constant.AdminApplicationConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = { "classpath:application.properties" })
@SpringBootTest
public class UserInfoAttachmentsDaoTest {
	@Autowired
	UserInfoAttachmentsDao dao;

	@Test
	public void deleteTest() {
		int deleted = dao.deleteByUserInfoIdAndPagePath(1, AdminApplicationConstants.USER_HEAD_IMG);
		System.out.println(deleted);
	}
}
