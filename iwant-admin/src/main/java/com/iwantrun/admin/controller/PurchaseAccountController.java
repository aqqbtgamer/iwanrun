package com.iwantrun.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.admin.service.PurChaseAccountService;

@RestController
@RequestMapping("purchaseAccount")
public class PurchaseAccountController {
	
	@Autowired
	private PurChaseAccountService accountService ;
	
	@RequestMapping("findPurchaseUser")
	@ResponseBody
	public String findPurchaseUser(HttpServletRequest request) {
		return accountService.findPurchaseUser(request);
	}
	
	@RequestMapping("queryPurchaseUser")
	@ResponseBody
	public String queryPurchaseUser(HttpServletRequest request) {
		return accountService.queryPurchaseUser(request);
	}
	
	@RequestMapping("add")
	@ResponseBody
	public String addPurchaseUser(HttpServletRequest request) {
		return accountService.addPurchaseUser(request);
	}
	
	@RequestMapping("get")
	@ResponseBody
	public String getById(HttpServletRequest request) {
		return accountService.getById(request);
	}
	
	@RequestMapping("modify")
	@ResponseBody
	public String modify(HttpServletRequest request) {
		return accountService.modify(request);
	}
	
	@RequestMapping("apply")
	@ResponseBody
	public String apply(HttpServletRequest request) {
		return accountService.apply(request);
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public String delete(HttpServletRequest request) {
		return accountService.delete(request);
	}
	

}
