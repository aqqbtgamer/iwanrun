package com.iwantrun.core.service.application.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwantrun.core.service.application.annotation.NeedTokenVerify;
import com.iwantrun.core.service.application.domain.PurchaserAccount;
import com.iwantrun.core.service.application.service.LoginTokenService;
import com.iwantrun.core.service.application.service.PurchaserAccountService;
import com.iwantrun.core.service.application.transfer.Message;
import com.iwantrun.core.service.application.transfer.PageDomianRequest;
import com.iwantrun.core.service.application.transfer.PurchaserAccountRequest;
import com.iwantrun.core.service.utils.JSONUtils;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@Controller
@RequestMapping("application/purchaserAccount")
public class PurchaserAccountController {
	@Autowired
	PurchaserAccountService service;
	@Autowired
	LoginTokenService tokenService;
	
	/**
	 * 采购用户注册
	 * 
	 * @param message
	 * @return
	 */
	@RequestMapping("register")
	@ResponseBody
	public Message register(@RequestBody Message message) {
		Message response=new Message();
		JSONObject responseJSON=new JSONObject();
		
		String errorMsg;
		String param = message.getMessageBody();
		if (!StringUtils.isEmpty(param)) {
			PurchaserAccountRequest accountRequest = JSONUtils.jsonToObj(param, PurchaserAccountRequest.class);
			errorMsg = service.validateRegisterParam(accountRequest);
			if(errorMsg==null) {
				errorMsg= service.register(accountRequest.getAccount());
				if(errorMsg==null) {
					String token=tokenService.tokenGenerate(accountRequest.getAccount().getMobileNumber(), accountRequest.getSessionId());
					response.setAccessToken(token);
				}
			}
		}else {
			errorMsg ="请输入相关数据";
		}
		responseJSON.put("errorMsg", errorMsg);
		response.setMessageBody(responseJSON.toJSONString());
		return response;
	}
	
	/**
	 * 采购用户登录
	 * 
	 * @param message
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public Message login(@RequestBody Message message) {
		Message response=new Message();
		JSONObject responseJSON=new JSONObject();
		
		String errorMsg;
		String param = message.getMessageBody();
		if (!StringUtils.isEmpty(param)) {
			PurchaserAccount account=JSONUtils.jsonToObj(param, PurchaserAccount.class);
			errorMsg = service.validateLoginParam(account);
		}else {
			errorMsg ="请输入相关数据";
		}
		responseJSON.put("errorMsg", errorMsg);
		response.setMessageBody(responseJSON.toJSONString());
		return response;
	}
	
	@RequestMapping("find")
	@ResponseBody
	public Message findByParams(@RequestBody Message message) {
		String requestJSON = message.getMessageBody();
		JSONObject requestObj = (JSONObject) JSONValue.parse(requestJSON);
		message.setMessageBody(service.findPurchaseUserPaged(requestObj));
		return message;
	}
	
	@RequestMapping("findByExample")
	@ResponseBody
	public Message findByExample(@RequestBody Message message){
		String dataJson = message.getMessageBody();
		PageDomianRequest example = JSONUtils.jsonToObj(dataJson, PageDomianRequest.class);
		JSONObject requestObj = new JSONObject();
		requestObj.put("pageIndex", String.valueOf(example.getPageIndex()));
		Map<String,Object> wrap = example.getObj();
		requestObj.put("loginId", wrap.get("loginId"));
		requestObj.put("name", wrap.get("name"));
		requestObj.put("mobileNumber", wrap.get("mobileNumber"));
		requestObj.put("role", wrap.get("role"));
		message.setMessageBody(service.findPurchaseUserPaged(requestObj));
		return message;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("add")
	@ResponseBody
	@NeedTokenVerify
	public Message addPurchaseUserAndRelated(@RequestBody Message message){
		String dataJson = message.getMessageBody();
		Map<String,Object> paramsMap = JSONUtils.jsonToObj(dataJson, Map.class);
		String result = service.addPurchaseUserAndRelated(paramsMap);
		message.setMessageBody(result);
		return message;
	}
	
	
}
