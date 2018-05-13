package com.iwantrun.core.service.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class DictionaryConfigParamsTest {	
	

	@Test
	public void testGetLocationIdByDesc() {
		int result = DictionaryConfigParams.getLocationIdByDesc("特色关键字");
		assertEquals(1,result);
	}
	
	@Test
	public void testGetLocationDictinaryJson() {
		String result = DictionaryConfigParams.getLocationDictinaryJson();
		System.out.println(result);
	}

}
