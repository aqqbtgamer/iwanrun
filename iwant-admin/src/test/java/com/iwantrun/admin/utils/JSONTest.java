package com.iwantrun.admin.utils;

import java.lang.reflect.Array;
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
		
		
		String jsonstr="{\"name\":\"\",\"activityTypeCode\":\"1\",\"during\":\"\",\"duringCode\":\"1\",\"location\":\"\",\"orderGroupPriceCode\":\"1\",\"orderSimulatePriceCode\":\"1\",\"groupNumber\":\"\",\"groupNumberCode\":\"1\",\"priority\":\"\",\"activityProvinceCode\":\"1\",\"activityCityCode\":\"1\",\"activityDistCode\":\"1\",\"mainImage\":\"http://localhost:8089/iwant_admin/images/1524319241.png\",\"imgManage\":[\"http://127.0.0.1:9999/iwant_app/uploaded/1524620559(1)_1524620582044.jpg\"],\"_ue\":\"<p><img src=\\\"http://img.baidu.com/hi/jx2/j_0001.gif\\\"/>\\n\\t\\t\\t\\t\\t\\t\\t\\t产品详情编辑\\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>\",\"descirbeText1\":\"<p><img src=\\\"http://img.baidu.com/hi/jx2/j_0001.gif\\\"/>\\n\\t\\t\\t\\t\\t\\t\\t\\t产品详情编辑\\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>\"}";
		
		info=JSONValue.parse(jsonstr, ProductionInfo.class);
		Assert.assertNotNull(info);
	}
	
	@Test
	public void testName() throws Exception {
		int[] arr = {1, 2};
		String str=JSONUtils.objToJSON(arr);
		System.out.println(str);
		str="[1,2]";
		System.out.println(arr);
	}
}
