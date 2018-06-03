package com.iwantrun.core.service.utils;

/**
 * @author user 随机数生成
 */
public class RandomUtils {

	/**
	 * @return 六位伪随机数
	 */
	public static String getSixCode() {
		int intFlag = (int) (Math.random() * 1000000);

		String flag = String.valueOf(intFlag);
		if (flag.length() == 6 && flag.substring(0, 1).equals("9")) {
			return flag;
		} else {
			intFlag = intFlag + 100000;
			return String.valueOf(intFlag);
		}
	}

}
