package com.iwantrun.core.service.utils;

import java.util.Map;

import org.junit.Test;

import com.iwantrun.core.service.application.domain.Dictionary;
import com.iwantrun.core.service.application.domain.Locations;

public class EntityDictionaryConfigUtilsTest {

	@Test
	public void testGetDictionaryMaping() {
		Locations location = new Locations();
		Map<String, Dictionary> mapping =EntityDictionaryConfigUtils.getDictionaryMaping(location);
		System.out.println(mapping);
	}

}
