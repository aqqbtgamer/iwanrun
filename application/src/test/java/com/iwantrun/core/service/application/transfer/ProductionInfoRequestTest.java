package com.iwantrun.core.service.application.transfer;

import org.junit.Test;

import com.iwantrun.core.service.utils.JSONUtils;

public class ProductionInfoRequestTest {

	@Test
	public void test() {
		String testString = 
				"{\"info\":{\"name\":\"测试产品\",\"specialTagsCode\":\"1\",\"activityTypeCode\":\"2\",\"during\":\"2\",\"location\":\"测试地址\",\"orderGroupPriceCode\":\"1\",\"orderSimulatePriceCode\":\"1\",\"groupNumber\":\"2\",\"priority\":\"12\",\"activityProvinceCode\":\"1\",\"activityCityCode\":\"1\",\"activityDistCode\":\"2\",\"mainImageLarge\":\"http://127.0.0.1:9999/iwant_app/uploaded/test4_1538722304855.jpg\",\"imgManage\":[\"http://127.0.0.1:9999/iwant_app/uploaded/test4_1538722309620.jpg\",\"http://127.0.0.1:9999/iwant_app/uploaded/test5_1538722314910.jpg\",\"http://127.0.0.1:9999/iwant_app/uploaded/test7_1538722319639.jpg\"],\"_ue\":\"<p>测试产品</p><p>测试产品描述</p>\",\"descirbeText1\":\"<p>测试产品</p><p>测试产品描述</p>\",\"id\":\"2\"}}";
		try {
			ProductionInfoRequest info = JSONUtils.jsonToObj(testString, ProductionInfoRequest.class);
			System.out.println(info);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
