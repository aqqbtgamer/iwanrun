package com.iwantrun.core.service.application.service;

import java.util.Iterator;
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
import com.iwantrun.core.service.application.transfer.SimpleMessageBody;
import com.iwantrun.core.service.utils.JPADBUtils;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

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
				"=,status,and",
				"=,simulatePriceCode,and"
		}),page);
	}
	
	
	public Page<Locations> queryLocationBySpecificationPageable(int pageIndex,Specification<Locations> example){		
		Integer pageSize = Integer.parseInt(env.getProperty("common.pageSize"));
		Pageable page =  PageRequest.of(pageIndex, pageSize, Sort.Direction.ASC, "id") ;
		return locationDao.findAll(example,page);
	}

	@Transactional
	public String delete(String id) {
		SimpleMessageBody simple = new SimpleMessageBody();
		try {
			JSONObject jsonId = (JSONObject) JSONValue.parse(id);
			if(jsonId != null) {
				if(jsonId.getAsString("id")!= null) {
					Integer deleteId = Integer.parseInt(jsonId.getAsString("id"));
					this.locationDao.deleteById(deleteId);
				}
				if(jsonId.get("id[]") != null) {
					JSONArray jsonIdArray = (JSONArray) jsonId.get("id[]");
					Iterator<Object> it =  jsonIdArray.iterator();
					while(it.hasNext()) {
						Object idDelete = it.next();
						Integer deleteId = Integer.parseInt(idDelete.toString());
						this.locationDao.deleteById(deleteId);
					}
				}
			}		
			simple.setSuccessful(true);
		}catch(NumberFormatException e) {
			simple.setSuccessful(false);
			simple.setDescription(e.getMessage());
			logger.error("Id want to be deleted is not in good format",e);
		}		
		return JSONValue.toJSONString(simple);
	}
	
	
}
