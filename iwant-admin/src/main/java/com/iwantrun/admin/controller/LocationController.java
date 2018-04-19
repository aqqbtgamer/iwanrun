package com.iwantrun.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwantrun.admin.service.LocationService;

@Controller
public class LocationController {
	
	@Autowired
	private LocationService locationService ;
	
	@RequestMapping("/location/add")
	@ResponseBody
	public String addLocation(HttpServletRequest request) {
		return locationService.addLocation(request);
	}

}