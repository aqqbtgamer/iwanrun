package com.iwantrun.admin.utils;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.junit.Test;

public class AESUtilsTest {

	@Test
	public void test() {
		try {
			System.out.println(AESUtils.encode("admin1876"));;
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
