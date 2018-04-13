package com.iwantrun.core.service.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwantrun.core.service.application.dao.LocationsDao;
import com.iwantrun.core.service.application.domain.Locations;
import com.iwantrun.core.service.application.intercepter.ControllInvokerIntercepter;

public class LocationsService {
	
	Logger logger = LoggerFactory.getLogger(ControllInvokerIntercepter.class);
	
	@Autowired
	private LocationsDao locationDao;
	
	public boolean createLocations(Locations location) {
		boolean executeResult = false ;
		return executeResult;
	}

}
