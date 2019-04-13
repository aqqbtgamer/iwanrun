package com.iwantrun.front.controller;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.front.constants.AdminApplicationConstants;
import com.iwantrun.front.service.PurchaserAccountService;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.utils.Md5Utils;
import com.iwantrun.front.utils.WeiXinUtils;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@Controller
@RequestMapping("weixing")
public class WeiXingController {
	
	Logger logger = LoggerFactory.getLogger(WeiXingController.class);
	
	@Value("${weixing.publicAccount.appid}")
	private String appId;
	
	@Value("${weixing.publicAccount.appsecret}")
	private String appSecret ;
	
	@Value("${app.server}")
	private String baseUrl ;
	
	@Autowired
	private RestTemplate template;
	
	@Autowired
	private PurchaserAccountService service;
	
	
	@RequestMapping("getWeixingConfig")
	@ResponseBody
	public String getWeixingConfig(HttpServletRequest request) {
		String urlEncode = request.getParameter("url");
		try {
			String url = java.net.URLDecoder.decode(urlEncode,"UTF-8");
			logger.info("url:"+url);
			logger.info("appid:"+appId);
			logger.info("appSecret:"+appSecret);
			String accessToken = WeiXinUtils.getAcessToken(appId, appSecret);
			logger.info("accessToken:"+accessToken);
			String appTicket = WeiXinUtils.getAcessTicket(accessToken);
			logger.info("appTicket:"+appTicket);
			String nonceStr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串  
		    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳  
		    String str = "jsapi_ticket="+appTicket+"&noncestr="+nonceStr+"&timestamp="+timestamp+"&url="+url;
		    logger.info("str:"+str);
		    String signature =Md5Utils.SHA1(str);  
		    logger.info("signature:"+str);
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
	
	@RequestMapping("getWeixingOpenLoginUrl")
	@ResponseBody
	public String getWeixingOpenLoginUrl(HttpServletRequest request,@RequestBody JSONObject json) {
		String state = json.getAsString("counselor");
		int steteInt = 1 ;
		if("true".equals(state)) {
			steteInt = 2 ;
		}
		String url = "https://open.weixin.qq.com/connect/qrconnect?" +
				"appid=" + AdminApplicationConstants.appOpenId+
				"&redirect_uri="+
				AdminApplicationConstants.redirectUrl + 
				"&response_type=code" + 
				"&scope=snsapi_login" + 
				"&state="+
				steteInt+
				"#wechat_redirect";
		return url;
	}
	
	@RequestMapping("weixinCallback")
	public String weixinCallback(HttpServletRequest request,HttpServletResponse response) {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String acessTokenItem = WeiXinUtils.getOpenAcessToken(AdminApplicationConstants.appOpenId, AdminApplicationConstants.appOpenSecret,code);
		JSONObject acess = (JSONObject) JSONValue.parse(acessTokenItem);
		String openId = acess.getAsString("openid");
		String accessToken = acess.getAsString("access_token");
		String result =WeiXinUtils.getOpenUserInfo(accessToken, openId);
		JSONObject resultObj = (JSONObject) JSONValue.parse(result);
		resultObj.put("state", state);
		resultObj.put("sessionId", request.getSession().getId());
		String postUrl = baseUrl+"/application/purchaserAccount/weixinGreenPass";
		Message message = new Message();
		message.setMessageBody(resultObj.toJSONString());
		message.setRequestMethod(postUrl);
		message = template.postForEntity(postUrl, message, Message.class).getBody();
		String token = message.getMessageBody();
		service.addCookieForToken(false, token, openId,
				response);
		return "index";
	}
	
	
	@RequestMapping("mobileWeixinLoginUrl")
	@ResponseBody
	public String mobileWeixinLoginUrl(HttpServletRequest request,HttpServletResponse response) {
		String baseUrl = request.getParameter("baseUrl");
		return WeiXinUtils.getMobileLoginUrl(appId, baseUrl);
	}
	
	
	@RequestMapping("getMobileWeixinOpenid")
	@ResponseBody
	public String getMobileWeixinOpenid(HttpServletRequest request,HttpServletResponse response) {
		String code = request.getParameter("code");
		String acessTokenItem = WeiXinUtils.getOpenAcessToken(appId, appSecret,code);
		JSONObject acess = (JSONObject) JSONValue.parse(acessTokenItem);
		String openId = acess.getAsString("openid");
		String accessToken = acess.getAsString("access_token");
		String result =WeiXinUtils.getOpenUserInfo(accessToken, openId);
		return result ;
	}
	
	@RequestMapping("checkMobileOpenIdExists")
	@ResponseBody
	public String checkMobileOpenIdExists(HttpServletRequest request,HttpServletResponse response) {
		String openId = request.getParameter("openId");
		String postUrl = baseUrl+"/application/mobileOpenIdRelation/checkMobileOpenIdExists";
		JSONObject requestObj = new JSONObject();
		requestObj.put("openId", openId);
		Message message = new Message();
		message.setRequestMethod(postUrl);
		message.setMessageBody(requestObj.toJSONString());
		message = template.postForEntity(postUrl, message, Message.class).getBody();
		return message.getMessageBody();
	}
	
	@RequestMapping("mobileWeixinCallBack")
	@ResponseBody
	public String mobileWeixinCallBack(@RequestBody JSONObject userInfo,HttpServletRequest request,HttpServletResponse response) {	
		
		userInfo.put("sessionId", request.getSession().getId());
		String postUrl = baseUrl+"/application/purchaserAccount/weixinGreenPass";
		Message message = new Message();
		message.setMessageBody(userInfo.toJSONString());
		message.setRequestMethod(postUrl);
		message = template.postForEntity(postUrl, message, Message.class).getBody();
		String token = message.getMessageBody();
		service.addCookieForToken(false, token, userInfo.getAsString("openid"),
				response);
		return "success";
	}
	

}
