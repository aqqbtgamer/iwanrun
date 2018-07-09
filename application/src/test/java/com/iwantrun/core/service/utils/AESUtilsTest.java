package com.iwantrun.core.service.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class AESUtilsTest {

	@Test
	public void test() {
		System.out.println(AESUtils.encode("admin1876"));	
		System.out.println(AESUtils.decode("DBpGYjGCS1moV9uToJf0QjgGC7iTSwtzMrQ2/QvQdIUqLbz2WNh3CM9J6UHVAUy7TuX+yGsNnjEqMj8E8OPTynoEwq77iDykZWIC889lMo1Bo/SsQ3Umfz0WyST/OeYsUtwymvwHspKeY/fdlH9d5A=="));
	}
	
	@Test
	public void test2() throws Exception {
		System.out.println(AESUtils.encode("123123"));	
	}
	
	@Test
	public void testReg() {
		String regRex = "^([a-zA-Z]+.*[0-9]+.*[!@#$%^&*]+)|([a-zA-Z]+.*[!@#$%^&*]+.*[0-9]+)|([0-9]+.*[!@#$%^&*]+.*[a-zA-Z]+)|([0-9]+.*[a-zA-Z]+.*[!@#$%^&*]+)|([!@#$%^&*]+.*[a-zA-Z]+.*[0-9]+)|([!@#$%^&*]+.*[0-9]+.*[a-zA-Z]+)$";
		Pattern pattern = Pattern.compile(regRex);
		Matcher match = pattern.matcher("a1234a!");
		System.out.println(match.matches());
		
	}
}
