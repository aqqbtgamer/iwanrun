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
	
	@RequestMapping("/location/get")
	@ResponseBody
	public String getLocation(HttpServletRequest request) {
		return locationService.getLocation(request);
	}
	
	@RequestMapping("/location/findAll")
	@ResponseBody
	public String findAll(HttpServletRequest request) {
		return locationService.findAll(request);
	}
	
	@RequestMapping("/location/queryAll")
	@ResponseBody
	public String queryAll(HttpServletRequest request) {
		return locationService.queryAll(request);
	}
	
	@RequestMapping("/location/delete")
	@ResponseBody
	public String delete(HttpServletRequest request) {
		return locationService.delete(request);
	}

}
