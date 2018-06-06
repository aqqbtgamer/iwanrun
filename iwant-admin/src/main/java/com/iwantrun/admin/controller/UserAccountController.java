package com.iwantrun.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.admin.service.UserAccountService;

@RestController
@RequestMapping(path="userAccount")
public class UserAccountController {
	
	@Autowired
	private UserAccountService service ;
	
	@RequestMapping(path="findAll")
	public String findAll(HttpServletRequest request) {
		return service.findAll(request);
	}
	
	@RequestMapping(path="findByExample")
	public String findByExample(HttpServletRequest request) {
		return service.findByExample(request);
	}
	
	@RequestMapping(path="add")
	public String addUserAccount(HttpServletRequest request) {
		return service.addUserAccount(request);
	}
	
	@RequestMapping(path="delete")
	public String delete(HttpServletRequest request) {
		return service.delete(request);
	}
	
	@RequestMapping(path="get")
	public String get(HttpServletRequest request) {
		return service.get(request);
	}
	
	@RequestMapping(path="modify")
	public String modify(HttpServletRequest request) {
		return service.modify(request);
	}

}
