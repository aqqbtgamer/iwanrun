package com.iwantrun.core.service.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class Md5UtilsTest {

	@Test
	public void testGenerate() {
		String password = "admin1876" ;
		String md5Salt = Md5Utils.generate(password);
		assertNotNull(md5Salt);
		System.out.println(md5Salt);
		assertTrue(Md5Utils.verify(password, md5Salt));
	}

}
