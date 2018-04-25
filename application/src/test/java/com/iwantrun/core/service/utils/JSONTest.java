package com.iwantrun.core.service.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.iwantrun.core.service.application.domain.ProductionInfo;
import com.iwantrun.core.service.application.transfer.ProductionInfoRequest;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import net.minidev.json.parser.ParseException;

public class JSONTest {
	public static void main(String[] args) throws ParseException {
		JSONObject object=new JSONObject();
		object.put("name2", "name2");
		object.put("name", "name");
		ProductionInfo info = JSONValue.parse(object.toJSONString(), ProductionInfo.class);
		System.out.println(info);
	}
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
		str="{\"size\":[1,2,3],\"size2\":[1,2,3], \"name\":\"123\"}";
		ProductionInfo info=JSONUtils.jsonToObj(str, ProductionInfo.class);
		ob=JSONUtils.jsonToObj(str, JSONObject.class);
		System.out.println(ob);
	}
}
