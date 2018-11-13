package com.iwantrun.core.service.utils;

import org.junit.Test;

public class StringFilterUtilsTest {

	@Test
	public void test() {
		String s = StringFilterUtils.replaceBlank("DBpGYjGCS1moV9uToJf0QjgGC7iTSwtzMrQ2/QvQdIX7fqSYaM+LXFeBY/7WgS9TjApvFdv2RBtS\r\n" + 
				"VuwngRnMmxjjLY8qDoVdhd1oleJUItNafUqikA1AagGMxOUdqD1uKCtHLtEcDGM/6ulCAFh0ZQ==");
		System.out.println(AESUtils.decode(s));
	}
	
	@Test
	public void testMask() {
		String testS = "18018336171" ;
		System.out.println(StringFilterUtils.maskString(3, 4, testS));
	}
	
	@Test
	public void testFirstCap() {
		String testS = "locationId";
		System.out.println(StringFilterUtils.firstCapString(testS));
	}

}
