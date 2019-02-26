package com.iwantrun.front.utils;

import com.iwantrun.front.constants.AdminApplicationConstants;
import com.iwantrun.front.utils.WeiXinUtils;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

import org.junit.Test;

public class WeiXinUtilsTest {

	@Test
	public void test1() {
		String result =WeiXinUtils.getOpenAcessToken(AdminApplicationConstants.appOpenId, AdminApplicationConstants.appOpenSecret,"021tB7OR1BxVk31vXHLR1E5hOR1tB7OQ");
		JSONObject obj = (JSONObject) JSONValue.parse(result);
		String openId = obj.getAsString("openid");
		String accessToken = obj.getAsString("access_token");
		System.out.println(openId);
		System.out.println(accessToken);
	}
	
	
	@Test
	public void test2() {
		String acessToken = "19_lap6kLdd38VKpKaCxO0TvP9kCLiULBdepJv0n3QTlf6xA42ePrQdNA_IVwVSo0QD411XQtVpWeuOn8yjZNb3Zg";
		String openId = "oNnBn6GW0JlNOmzqkOmh9ur-0z9s";
		String result =WeiXinUtils.getOpenUserInfo(acessToken, openId);
		JSONObject obj = (JSONObject) JSONValue.parse(result);
		System.out.println(obj.toJSONString());
	}
	

}
