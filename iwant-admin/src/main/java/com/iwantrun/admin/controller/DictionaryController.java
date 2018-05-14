package com.iwantrun.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.iwantrun.admin.service.DictionaryService;

@Controller
public class DictionaryController {
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@RequestMapping(value="/dictionary/getPages")
	@ResponseBody
	public String getPages(HttpServletRequest request) {
		return dictionaryService.getPages(request);
	}
	
	
	@RequestMapping(value="/dictionary/getTabs")
	@ResponseBody
	public String getTabs(HttpServletRequest request) {
		return dictionaryService.getTabs(request);
	}
	
	@RequestMapping(value="/dictionary/findByCode")
	@ResponseBody
	public String findByCode(HttpServletRequest request) {
		return dictionaryService.findByCode(request);
	}
	
	
	@RequestMapping(value="/dictionary/add")
	@ResponseBody
	public String add(HttpServletRequest request) {
		return dictionaryService.add(request);
	}
	
	@RequestMapping(value="/dictionary/delete")
	@ResponseBody
	public String delete(HttpServletRequest request) {
		return dictionaryService.delete(request);
	}

}
