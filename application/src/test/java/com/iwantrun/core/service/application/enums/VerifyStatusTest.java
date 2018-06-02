package com.iwantrun.core.service.application.enums;

import org.junit.Test;

import com.iwantrun.core.service.application.domain.UserInfo;

public class VerifyStatusTest {

	@Test
	public void test() {
		UserInfo info = new UserInfo();
		info.setVerified(null);
		System.out.println(info.getVerifiedString());
	}

}
