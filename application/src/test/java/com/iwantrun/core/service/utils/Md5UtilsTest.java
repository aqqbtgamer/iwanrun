package com.iwantrun.core.service.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class Md5UtilsTest {

	@Test
	public void testGenerate() {
		String password = "testPassword" ;
		String md5Salt = Md5Utils.generate(password);
		assertNotNull(md5Salt);
		System.out.println(md5Salt);
		System.out.println(md5Salt.length());
		assertTrue(Md5Utils.verify(password, md5Salt));
		System.out.println(Md5Utils.verify("Aqq37217$","11732a568164e34167b7f617206845899711c71b2884010b"));;
	}

}
