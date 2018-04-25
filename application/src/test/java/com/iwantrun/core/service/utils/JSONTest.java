package com.iwantrun.core.service.utils;

import org.junit.Test;

import com.iwantrun.core.service.application.domain.ProductionInfo;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import net.minidev.json.parser.ParseException;

public class JSONTest {
	
	@Test
	public  void test() throws ParseException {
		JSONObject object=new JSONObject();
		object.put("name2", "name2");
		object.put("name", "name");
		ProductionInfo info = JSONValue.parse(object.toJSONString(), ProductionInfo.class);
		System.out.println(info);
	}
}
