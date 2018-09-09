package com.iwantrun.front.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwantrun.front.service.CastPositionService;

@Controller
@RequestMapping("castposition")
public class CastPositionController {
	
	Logger logger = LoggerFactory.getLogger(CastPositionController.class);
	
	@Autowired
	private CastPositionService castService;
	
	@RequestMapping("findAll")
	@ResponseBody	
	public String findAll(HttpServletRequest request) {
		return castService.findAll();
	}
	

}
