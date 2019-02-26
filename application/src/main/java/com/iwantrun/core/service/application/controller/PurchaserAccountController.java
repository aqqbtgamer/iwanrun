package com.iwantrun.core.service.application.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private final Logger logger = LoggerFactory.getLogger(PurchaserAccountController.class);
	
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
	@RequestMapping("modifyPwd")
	@ResponseBody
	public Message modifyPwd(@RequestBody Message message) {
		Message response=new Message();
		JSONObject responseJSON=new JSONObject();
		
		String errorMsg="";
		String param = message.getMessageBody();
		
		logger.error("用户修改密码，请求参数：{}", param);
		
		try {
			if (!StringUtils.isEmpty(param)) {
				PurchaserAccountRequest accountRequest = JSONUtils.jsonToObj(param, PurchaserAccountRequest.class);
				errorMsg = service.validateRegisterParam(accountRequest);
				if(errorMsg==null) {
					errorMsg= service.modifyPwd(accountRequest.getAccount());
					if(errorMsg==null) {
						String token=tokenService.tokenGenerate(accountRequest.getAccount().getLoginId(), accountRequest.getSessionId());
						response.setAccessToken(token);
					}
				}
			}else {
				errorMsg ="请输入相关数据";
			}
		} catch (Exception e) {
			errorMsg = "未知异常";
			logger.error("用户注册异常：{}", e);
		}
		
		responseJSON.put("errorMsg", errorMsg);
		
		logger.error("用户注册，返回结果：{}", responseJSON.toJSONString());
		
		response.setMessageBody(responseJSON.toJSONString());
		return response;
	}
	
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
		
		String errorMsg="";
		String param = message.getMessageBody();
		
		logger.error("用户注册，请求参数：{}", param);
		
		try {
			if (!StringUtils.isEmpty(param)) {
				PurchaserAccountRequest accountRequest = JSONUtils.jsonToObj(param, PurchaserAccountRequest.class);
				errorMsg = service.validateRegisterParam(accountRequest);
				if(errorMsg==null) {
					errorMsg= service.register(accountRequest.getAccount());
					if(errorMsg==null) {
						String token=tokenService.tokenGenerate(accountRequest.getAccount().getLoginId(), accountRequest.getSessionId());
						response.setAccessToken(token);
					}
				}
			}else {
				errorMsg ="请输入相关数据";
			}
		} catch (Exception e) {
			errorMsg = "未知异常";
			logger.error("用户注册异常：{}", e);
		}
		
		responseJSON.put("errorMsg", errorMsg);
		
		logger.error("用户注册，返回结果：{}", responseJSON.toJSONString());
		
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
		
		String errorMsg = null;
		String param = message.getMessageBody();
		if (!StringUtils.isEmpty(param)) {
			PurchaserAccountRequest accountRequest = JSONUtils.jsonToObj(param, PurchaserAccountRequest.class);
			PurchaserAccount account=accountRequest.getAccount();
			
			boolean isMessageLogin = accountRequest.isMessageLogin();
			if(isMessageLogin) {
				String token=tokenService.tokenGenerate(account.getLoginId(), accountRequest.getSessionId());
				response.setAccessToken(token);
			}else {
				errorMsg = service.validateLoginParam(accountRequest);
				if(StringUtils.isEmpty(errorMsg)) {
					String token=tokenService.tokenGenerate(account.getLoginId(), accountRequest.getSessionId());
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
	
	@RequestMapping("find")
	@ResponseBody
	public Message findByParams(@RequestBody Message message) {
		String requestJSON = message.getMessageBody();
		JSONObject requestObj = (JSONObject) JSONValue.parse(requestJSON);
		message.setMessageBody(service.findPurchaseUserPaged(requestObj));
		return message;
	}
	
	@RequestMapping("findByLoginId")
	@ResponseBody
	public Message findByLoginId(@RequestBody Message message) {
		String requestJSON = message.getMessageBody();
		JSONObject requestObj = (JSONObject) JSONValue.parse(requestJSON);
		message.setMessageBody(service.findByLoginId(requestObj));
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
	
	@RequestMapping("get")
	@ResponseBody
	public Message get(@RequestBody Message message) {
		String dataJson = message.getMessageBody();
		JSONObject requestObj = (JSONObject) JSONValue.parse(dataJson);
		String idStr = requestObj.getAsString("id");
		if(idStr != null) {
			Integer id = Integer.parseInt(idStr);
			String result = service.get(id);
			message.setMessageBody(result);
		}
		return message;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("modify")
	@ResponseBody
	@NeedTokenVerify
	public Message modify(@RequestBody Message message) {
		String dataJson =  message.getMessageBody();
		JSONObject requestObj = (JSONObject) JSONValue.parse(dataJson);
		String id = requestObj.getAsString("id");
		Map<String,Object> paramsMap = JSONUtils.jsonToObj(requestObj.getAsString("json"),Map.class);
		String result = service.modify(id,paramsMap);
		message.setMessageBody(result);
		return message ;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	@NeedTokenVerify
	public Message delete(@RequestBody Message message) {
		String dataJson =  message.getMessageBody();
		JSONObject requestObj = (JSONObject) JSONValue.parse(dataJson);
		String result = service.delete(requestObj);
		message.setMessageBody(result);
		return message;
	}
	
	@RequestMapping("apply")
	@ResponseBody
	@NeedTokenVerify
	public Message apply(@RequestBody Message message) {
		String dataJson =  message.getMessageBody();
		JSONObject requestObj = (JSONObject) JSONValue.parse(dataJson);
		String id = requestObj.getAsString("id");
		String result = service.apply(id);
		message.setMessageBody(result);
		return message;
	}
	
	@RequestMapping("addAndModifyInfo")
	@ResponseBody
	@NeedTokenVerify
	public Message addAndModifyInfo(@RequestBody Message message) {
		String dataJson = message.getMessageBody();
		String result = service.addAndModifyInfo(dataJson);
		message.setMessageBody(result);
		return message;
	}

	@RequestMapping("findMixedByLoginId")
	@ResponseBody
	@NeedTokenVerify
	public Message findMixedByLoginId(@RequestBody Message message) {
		String dataJson = message.getMessageBody();
		String result = service.findMixedByLoginId(dataJson);
		message.setMessageBody(result);
		return message;
	}
	
	@RequestMapping("weixinGreenPass")
	@ResponseBody	
	public Message weixinGreenPass(@RequestBody Message message) {
		String dataJson = message.getMessageBody();
		JSONObject paramJSON = (JSONObject) JSONValue.parse(dataJson);
		boolean success = service.weixinGreenPass(paramJSON);
		if(success) {
			message.setMessageBody(tokenService.tokenGenerate(paramJSON.getAsString("openid"),paramJSON.getAsString("sessionId")));
		}		
		return message;
	}
	
	
	
}
