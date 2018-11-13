package com.iwantrun.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwantrun.admin.service.RelationService;

@Controller
@RequestMapping("relations")
public class RelationController {
	
	@Autowired
	private RelationService relationService;
	
	@RequestMapping("add")
	@ResponseBody
	public String addRelation(HttpServletRequest request) {
		return relationService.addRelation(request);
	}
	
	@RequestMapping("queryBindings")
	@ResponseBody
	public String queryBindings(HttpServletRequest request) {
		return relationService.qureyBindings(request);
	}
	
	@RequestMapping("deleteBindings")
	@ResponseBody
	public String deleteBindings(HttpServletRequest request) {
		return relationService.deleteBindings(request);
	}

}
