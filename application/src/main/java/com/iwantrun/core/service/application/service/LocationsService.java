package com.iwantrun.core.service.application.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.iwantrun.core.service.application.dao.LocationAttachmentsDao;
import com.iwantrun.core.service.application.dao.LocationsDao;
import com.iwantrun.core.service.application.domain.LocationAttachments;
import com.iwantrun.core.service.application.domain.Locations;
import com.iwantrun.core.service.application.intercepter.ControllInvokerIntercepter;
import com.iwantrun.core.service.utils.JPADBUtils;

@Service
public class LocationsService {
	
	Logger logger = LoggerFactory.getLogger(ControllInvokerIntercepter.class);
	
	@Autowired
	private LocationsDao locationDao;
	
	@Autowired
	private LocationAttachmentsDao attachmentsDao ;
	
	@Autowired  
    private Environment env;
	
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
	
	public Page<Locations> findAllLocationsPageable(int pageIndex){
		Integer pageSize = Integer.parseInt(env.getProperty("common.pageSize"));
		Pageable page =  PageRequest.of(pageIndex, pageSize, Sort.Direction.ASC, "id") ;
		return locationDao.findAll(page);
	}
	
	public Page<Locations> queryLocationByConditionPageable(int pageIndex,Locations example){		
		Integer pageSize = Integer.parseInt(env.getProperty("common.pageSize"));
		Pageable page =  PageRequest.of(pageIndex, pageSize, Sort.Direction.ASC, "id") ;
		return locationDao.findAll(JPADBUtils.generateSpecificationFromExample(example, new String[]{
				"=,id,and",
				"like,name,and",
				"=,activeTypeCode,and",
				"=,groupNumberLimit,and",
				"=,groupNumberLimitCode,and",
				"=,activityProvinceCode,and",
				"=,activityCityCode,and",
				"=,activityDistCode,and",
				"like,location,and",
				"=,priority,and",
				"=,status,and"				
		}),page);
	}
	
	
}
