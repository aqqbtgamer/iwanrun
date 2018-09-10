package com.iwantrun.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.admin.service.CastPositionService;

@RestController
@RequestMapping("/castposition")
public class CastPositionController {
	
	@Autowired
	private CastPositionService castService ;
	
	@RequestMapping("findAll")
	public String findAll(HttpServletRequest request) {
		return castService.getCastPostion(request);
	}
	
	@RequestMapping("addAll")
	public String addAll(HttpServletRequest request) {
		return castService.addCastPostion(request);
	}

}
