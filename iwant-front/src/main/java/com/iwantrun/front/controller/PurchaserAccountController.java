package com.iwantrun.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iwantrun.front.domain.PurchaserAccount;
import com.iwantrun.front.service.PurchaserAccountService;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.transfer.PurchaserAccountRequest;
import com.iwantrun.front.utils.JSONUtils;

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
	 * 采购用户注册
	 * 
	 * @param purchaser
	 * @return String
	 */
	@RequestMapping("/register")
	public String register(@RequestBody PurchaserAccountRequest purchaser) {
		Message result = service.register(purchaser);
		return JSONUtils.objToJSON(result);
	}

	/**
	 * 采购用户登录
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping("/login")
	public String login(@RequestBody PurchaserAccount account) {
		Message result = service.login(account);
		return JSONUtils.objToJSON(result);
	}
}
