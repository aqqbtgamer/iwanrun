package com.iwantrun.core.service.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.core.service.application.annotation.NeedTokenVerify;
import com.iwantrun.core.service.application.service.LocationsService;
import com.iwantrun.core.service.application.transfer.Message;

@RestController
public class LocationsController {
	
	Logger logger = LoggerFactory.getLogger(LocationsController.class);
	
	@Autowired
	private LocationsService locationService;
	
	@RequestMapping("/application/location/add")
	@NeedTokenVerify
	public Message addLocation(@RequestBody Message message) {
		Message response = new Message();
		return response;
	}

}
