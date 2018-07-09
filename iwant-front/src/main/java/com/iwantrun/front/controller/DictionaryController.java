package com.iwantrun.front.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.front.domain.Dictionary;
import com.iwantrun.front.service.DictionaryService;



@RestController
@RequestMapping("dictionary")
public class DictionaryController {
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@RequestMapping("queryListByField")
	public List<Dictionary> queryListByField(HttpServletRequest request){
		String name = request.getParameter("name");
		String usedField = request.getParameter("used_field");
		return dictionaryService.queryListByField(name, usedField);
	}
	

}
