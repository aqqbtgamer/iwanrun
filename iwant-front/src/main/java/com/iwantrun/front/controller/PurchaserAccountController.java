package com.iwantrun.front.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwantrun.front.service.PurchaserAccountService;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.transfer.PurchaserAccountRequest;
import com.iwantrun.front.utils.JSONUtils;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

/**
 * 
 * @author user 采购方账户相关操作
 */
@Controller
@RequestMapping("purchaserAccount")
public class PurchaserAccountController {

	@Autowired
	private PurchaserAccountService service;

	/**
	 * 登出
	 * 
	 * @param purchaser
	 * @return String
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public void logout(HttpServletResponse response) {
		service.logout(response);
	}

	/**
	 * 采购用户修改密码
	 * 
	 * @param purchaser
	 * @return String
	 */
	@RequestMapping("/modifyPwd")
	@ResponseBody
	public Message modifyPwd(HttpServletRequest request, @RequestBody PurchaserAccountRequest purchaser) {
		Message result = service.getVaidateSMSCodeResult(request, purchaser);
		if (result != null) {
			return result;
		}
		result = service.modifyPwd(purchaser);
		return result;
	}

	/**
	 * 采购用户注册
	 * 
	 * @param purchaser
	 * @return String
	 */
	@RequestMapping("/register")
	@ResponseBody
	public Message register(HttpServletRequest request, @RequestBody PurchaserAccountRequest purchaser) {
		// 是注册
		purchaser.setRegister(true);

		Message result = service.getVaidateSMSCodeResult(request, purchaser);
		if (result != null) {
			return result;
		}
		result = service.register(purchaser);
		return result;
	}
	
	/**
	 * 采购用户绑定手机
	 * 
	 * @param purchaser
	 * @return String
	 */
	@RequestMapping("/bindMobile")
	@ResponseBody
	public Message bindMobile(HttpServletRequest request, @RequestBody PurchaserAccountRequest purchaser) {
		// 是注册
		purchaser.setRegister(true);

		Message result = service.getVaidateSMSCodeResult(request, purchaser);
		if (result != null) {
			return result;
		}
		result = service.bindMobile(purchaser);
		return result;
	}

	/**
	 * 采购用户登录
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Message login(HttpServletRequest request, HttpServletResponse response,
			@RequestBody PurchaserAccountRequest purchaser) {
		boolean isMessageLogin = purchaser.isMessageLogin();
		Message result;
		if (isMessageLogin) {
			result = service.getVaidateSMSCodeResult(request, purchaser);
			if (result != null) {
				return result;
			}
		}
		String sessionId = request.getSession().getId();
		purchaser.setSessionId(sessionId);
		result = service.login(purchaser);
		JSONObject resultJson = (JSONObject) JSONValue.parse(result.getMessageBody());
		service.addCookieForToken(purchaser.isAutoLogin(), result.getAccessToken(), resultJson.getAsString("loginId"),
				response);
		return result;
	}

	/**
	 * 采购用户-用户个人信息-增加和修改
	 * 
	 * @param request
	 * @param param
	 * @return
	 */
	@RequestMapping("/addAndModifyInfo")
	@ResponseBody
	public Message addAndModifyInfo(HttpServletRequest request, @RequestBody String param) {
		Message resonse = new Message();
		String result = service.addAndModifyInfo(param, request);
		resonse.setMessageBody(result);
		return resonse;
	}

	@RequestMapping("findMixedByLoginId")
	@ResponseBody
	public String findMixedByLoginId(HttpServletRequest request) {
		return service.findMixedByLoginId(request);
	}
	@RequestMapping("findByLoginId")
	@ResponseBody
	public String findByLoginId(@RequestBody String param) {
		@SuppressWarnings("rawtypes")
		Map map = JSONUtils.jsonToMap(param);
		String loginId=(String) map.get("loginId");
		return service.findByLoginId(loginId);
	}
	
	@RequestMapping("getWeixinLoginUrl")
	public String getWeixinLoginUrl(HttpServletRequest request) {
		return service.getWeixinLoginUrl();
	}
}
