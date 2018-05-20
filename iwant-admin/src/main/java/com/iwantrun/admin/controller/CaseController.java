package com.iwantrun.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwantrun.admin.service.CasesService;

@Controller
public class CaseController {
	@Autowired
	private CasesService casesService;
	/**
	 * list列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/cases/findAll")
	@ResponseBody
	public String findAll(HttpServletRequest request) {
		return casesService.findAll(request);
	}
	
	@RequestMapping("/cases/add")
	@ResponseBody
	public String addLocation(HttpServletRequest request) {
		return casesService.addCase(request);
	}

	@RequestMapping("/cases/get")
	@ResponseBody
	public String getLocation(HttpServletRequest request) {
		return casesService.getCase(request);
	}
	
	@RequestMapping("/cases/queryAll")
	@ResponseBody
	public String queryAll(HttpServletRequest request) {
		return casesService.queryAll(request);
	}
	
	@RequestMapping("/cases/delete")
	@ResponseBody
	public String delete(HttpServletRequest request) {
		return casesService.delete(request);
	}
	
	@RequestMapping("/cases/modify")
	@ResponseBody
	public String modifyCase(HttpServletRequest request) {
		return casesService.modifyCase(request);
	}

}
