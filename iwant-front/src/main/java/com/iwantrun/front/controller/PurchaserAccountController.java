package com.iwantrun.front.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwantrun.front.service.PurchaserAccountService;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.transfer.PurchaserAccountRequest;

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
		Message result = service.getVaidateSMSCodeResult(request, purchaser);
		if (result != null) {
			return result;
		}
		result = service.register(purchaser);
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
	public Message login(HttpServletRequest request, @RequestBody PurchaserAccountRequest purchaser) {
		boolean isMessageLogin = purchaser.isMessageLogin();
		Message result;
		if (isMessageLogin) {
			result = service.getVaidateSMSCodeResult(request, purchaser);
			if (result != null) {
				return result;
			}
		}
		result = service.login(purchaser);
		return result;
	}
}
