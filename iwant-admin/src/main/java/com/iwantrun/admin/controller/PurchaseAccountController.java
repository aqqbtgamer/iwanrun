package com.iwantrun.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.admin.service.PurChaseAccountService;

@RestController
public class PurchaseAccountController {
	
	@Autowired
	private PurChaseAccountService accountService ;
	
	@RequestMapping("findPurchaseUser")
	@ResponseBody
	public String findPurchaseUser(HttpServletRequest request) {
		return accountService.findPurchaseUser(request);
	}
	

}
