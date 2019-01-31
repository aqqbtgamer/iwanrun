package com.iwantrun.front.controller;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwantrun.front.utils.Md5Utils;
import com.iwantrun.front.utils.WeiXinUtils;

import net.minidev.json.JSONObject;

@Controller
@RequestMapping("weixing")
public class WeiXingController {
	
	Logger logger = LoggerFactory.getLogger(WeiXingController.class);
	
	@Value("${weixing.publicAccount.appid}")
	private String appId;
	
	@Value("${weixing.publicAccount.appsecret}")
	private String appSecret ;
	
	
	@RequestMapping("getWeixingConfig")
	@ResponseBody
	public String getWeixingConfig(HttpServletRequest request) {
		String urlEncode = request.getParameter("url");
		try {
			String url = java.net.URLDecoder.decode(urlEncode,"UTF-8");
			String accessToken = WeiXinUtils.getAcessToken(appId, appSecret);
			String appTicket = WeiXinUtils.getAcessTicket(accessToken);
			String nonceStr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串  
		    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳  
		    String str = "jsapi_ticket="+appTicket+"&noncestr="+nonceStr+"&timestamp="+timestamp+"&url="+url; 
		    String signature =Md5Utils.SHA1(str);  
		    JSONObject jsonObject = new JSONObject();
		    jsonObject.put("appId",appId);
		    jsonObject.put("timestamp",timestamp);
		    jsonObject.put("accessToken",accessToken);
		    jsonObject.put("ticket",appTicket);
		    jsonObject.put("nonceStr",nonceStr);
		    jsonObject.put("signature",signature);
		    return jsonObject.toJSONString();
		} catch (UnsupportedEncodingException e) {
			logger.error("url 解码发生问题",e);
			return "";
		}		
	}

}
