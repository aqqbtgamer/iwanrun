package com.iwantrun.core.service.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class DictionaryConfigParamsTest {
	
	private DictionaryConfigParams dictionaryConfigParams = new DictionaryConfigParams() {};

	@Test
	public void testGetLocationIdByDesc() {
		int result = dictionaryConfigParams.getLocationIdByDesc("特色关键字");
		assertEquals(1,result);
	}
	
	@Test
	public void testGetLocationDictinaryJson() {
		String result = dictionaryConfigParams.getLocationDictinaryJson();
		System.out.println(result);
	}

}
