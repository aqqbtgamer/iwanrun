package com.iwantrun.core.service.utils;

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
}
