package com.iwantrun.core.service.utils;

import java.util.regex.Pattern;

import org.junit.Test;

//@RunWith(SpringJUnit4ClassRunner.class)
//@TestPropertySource({ "classpath:application.properties" })
public class RegexTest {
	/*
	 * @Autowired Environment environment;
	 * 
	 * @Test public void testPropRegex() throws Exception { String password =
	 * "12345678"; String regex =
	 * environment.getProperty("purchaser.account.password.regex"); boolean
	 * matchered = password.matches(regex); System.out.println("regex=" + regex);
	 * System.out.println("         testPropRegex: " + matchered); }
	 */

	@Test
	public void testPwdRegex() throws Exception {
		String regex = "^([a-zA-Z]+[0-9]+[!@#$%^&*]+)|" + "([a-zA-Z]+[!@#$%^&*]+[0-9]+)|"
				+ "([0-9]+[!@#$%^&*]+[a-zA-Z]+)|" + "([0-9]+[a-zA-Z]+[!@#$%^&*]+)|" + "([!@#$%^&*]+[a-zA-Z]+[0-9]+)|"
				+ "([!@#$%^&*]+[0-9]+[a-zA-Z]+)$";
		// ^([a-zA-Z]+[0-9]+[!@#$%^&*]+)|([a-zA-Z]+[!@#$%^&*]+[0-9]+)|([0-9]+[!@#$%^&*]+[a-zA-Z]+)|([0-9]+[a-zA-Z]+[!@#$%^&*]+)|([!@#$%^&*]+[a-zA-Z]+[0-9]+)|([!@#$%^&*]+[0-9]+[a-zA-Z]+)${8,
		// 16}
		// regex = "^([a-zA-Z]+[0-9]+[!@#$%^&*]+)$";
		Pattern pattern = Pattern.compile(regex);
		String input = "a1234a!";
		boolean matches = pattern.matcher(input).matches();
		System.out.println("密码：" + input + "----是否匹配：" + matches);
	}
}
