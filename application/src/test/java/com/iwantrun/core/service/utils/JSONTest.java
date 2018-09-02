package com.iwantrun.core.service.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.iwantrun.core.service.application.domain.ProductionInfo;
import com.iwantrun.core.service.application.transfer.ProductionInfoRequest;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class JSONTest {
	
	@Test
	public void testName() throws Exception {
		int[] arr = {1, 2};
		String str=JSONUtils.objToJSON(arr);
		System.out.println(str);
		str="[1,2]";
		ProductionInfoRequest infoRequest=new ProductionInfoRequest();
		infoRequest.setArr(arr);
		str=JSONUtils.objToJSON(infoRequest);
		infoRequest=JSONUtils.jsonToObj(str, ProductionInfoRequest.class);
		//JSONObject obj=JSONUtils.jsonToObj(str, JSONObject.class);
		System.out.println(infoRequest);
	}
	@Test
	public void testName2() throws Exception {
		int[] arr = {1, 2};
		JSONObject ob=new JSONObject();
		ob.put("size", arr);
		String str=JSONUtils.objToJSON(ob);
		ob=JSONUtils.jsonToObj(str, JSONObject.class);
		str="{\"width\":200, \"height\":200}";
		ob=JSONUtils.jsonToObj(str, JSONObject.class);
		ProductionInfo info=JSONUtils.jsonToObj(str, ProductionInfo.class);
		ob=JSONUtils.jsonToObj(str, JSONObject.class);
		System.out.println(ob);
	}
	@Test
	public void testName3() throws Exception {
		JSONArray arr=new JSONArray();
		arr.add(1);
		arr.add(2);
		String json=JSONUtils.objToJSON(arr);
		arr=JSONUtils.jsonToObj(json, JSONArray.class);
		List<Integer> list=new ArrayList<>();
		list.add(1);
		list.add(2);
		json=JSONUtils.objToJSON(list);
		list=JSONUtils.toList(json, Integer.class);
		System.out.println(json);
	}
	@Test
	public void testMap() throws Exception {
		Map<String, Integer> map=new HashMap<>();
		map.put("width", 1);
		map.put("height", 2);
		String str=JSONUtils.objToJSON(map);
		map=JSONUtils.toMap(str, Integer.class);
		System.out.println(map);
	}

	@Test
	public void testArray() throws Exception {
		String[] strArr = { "213" };
		List<String> list = new ArrayList<>();
		list.add("list");
		String json = JSONUtils.objToJSON(list);
		System.out.println(json);
		JSONArray arr = JSONUtils.jsonToObj(json, JSONArray.class);
		System.out.println(arr);
	}
}
