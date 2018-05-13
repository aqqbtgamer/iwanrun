package com.iwantrun.core.service.application.config;

import org.junit.Test;

public class DictionaryPageConfigTest {

	@Test
	public void testGetPageConfigJson() {
		System.out.println(DictionaryPageConfig.getPageConfigJson());
	}
	
	@Test
	public void testGetPageTages() {
		System.out.println(DictionaryPageConfig.getPageTages("common"));
	}
}
