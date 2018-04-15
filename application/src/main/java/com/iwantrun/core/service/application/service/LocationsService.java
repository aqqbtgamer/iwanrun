package com.iwantrun.core.service.application.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iwantrun.core.service.application.dao.LocationAttachmentsDao;
import com.iwantrun.core.service.application.dao.LocationsDao;
import com.iwantrun.core.service.application.domain.LocationAttachments;
import com.iwantrun.core.service.application.domain.Locations;
import com.iwantrun.core.service.application.intercepter.ControllInvokerIntercepter;

@Service
public class LocationsService {
	
	Logger logger = LoggerFactory.getLogger(ControllInvokerIntercepter.class);
	
	@Autowired
	private LocationsDao locationDao;
	
	@Autowired
	private LocationAttachmentsDao attachmentsDao ;
	
	@Transactional
	public boolean createLocations(Locations location,List<LocationAttachments> attachments) {
		boolean executeResult = false ;
		locationDao.saveAndFlush(location);
		for(LocationAttachments attach: attachments) {
			attach.setLocation_id(location.getId());
		}
		attachmentsDao.saveAll(attachments);
		executeResult = true ;
		return executeResult;
	}

}
