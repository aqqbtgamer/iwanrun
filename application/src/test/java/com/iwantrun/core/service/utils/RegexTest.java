package com.iwantrun.core.service.utils;

import java.util.regex.Matcher;
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
		String regex2 = "^([a-zA-Z]+[0-9]+[!@#$%^&*]+)|([a-zA-Z]+[!@#$%^&*]+[0-9]+)|([0-9]+[!@#$%^&*]+[a-zA-Z]+)|([0-9]+[a-zA-Z]+[!@#$%^&*]+)|([!@#$%^&*]+[a-zA-Z]+[0-9]+)|([!@#$%^&*]+[0-9]+[a-zA-Z]+)$";
		String regex="^([a-zA-Z]+.*[0-9]+.*[!@#$%^&*]+)|([a-zA-Z]+.*[!@#$%^&*]+.*[0-9]+)|([0-9]+.*[!@#$%^&*]+.*[a-zA-Z]+)|([0-9]+.*[a-zA-Z]+.*[!@#$%^&*]+)|([!@#$%^&*]+.*[a-zA-Z]+.*[0-9]+)|([!@#$%^&*]+.*[0-9]+.*[a-zA-Z]+)$";
		regex = "^(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z]).{2,10}$";
		regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{2,10}$";
		regex = "^(?=.+[a-zA-Z])(?=.+[0-9])(?=.+[!@#$%^&*]).{8,10}$";
		Pattern pattern = Pattern.compile(regex);
		String input = "Wlm1236!";
		boolean matches = pattern.matcher(input).matches();
		System.out.println("密码：" + input + "----是否匹配：" + matches);
	}
	
	@Test
	public void testAssert() throws Exception {
		String input = "i want you , i love you";
		String regex = "\\b\\w+(?=ou\\b)";
		regex="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{2,20}$";
		input="Wlm12361!";
		Matcher matcher =Pattern.compile(regex).matcher(input);
		while (matcher.find()) {
			System.out.println("查找到了："+matcher.group());
		}
	}
	
	@Test
	public void testPreAssert() throws Exception {
		String input="10234567890";
		String regex="(?<=(\\d{2}|\\d{2}))(\\d{3})+";
		Matcher matcher=Pattern.compile(regex).matcher(input);
		while (matcher.find()) {
			System.out.println("找到了："+matcher.group());
		}
	}
}
