package com.iwantrun.admin.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.iwantrun.admin.domain.ProductionInfo;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class JSONTest {
	@Test
	public void test() {
		ProductionInfo info=new ProductionInfo();
		info.setName("123");
		
		List<String> list=new ArrayList<>();
		list.add("list");
		
//		info.setList(list);
		
		JSONObject obj=new JSONObject();
		obj.put("name2", "name2");
		
		
		String json = JSONValue.toJSONString(info);
		System.out.println(json);
		
		
		info=JSONValue.parse(obj.toJSONString(), ProductionInfo.class);
		Assert.assertNotNull(info);
	}
}
