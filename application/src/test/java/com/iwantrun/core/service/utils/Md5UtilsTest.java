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
	}

}
