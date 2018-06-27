package com.iwantrun.core.service.application.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.iwantrun.core.service.application.dao.DictionaryDao;
import com.iwantrun.core.service.application.dao.JPQLEnableRepository;
import com.iwantrun.core.service.application.dao.LocationAttachmentsDao;
import com.iwantrun.core.service.application.dao.LocationTagsDao;
import com.iwantrun.core.service.application.dao.LocationsDao;
import com.iwantrun.core.service.application.domain.CaseTags;
import com.iwantrun.core.service.application.domain.Cases;
import com.iwantrun.core.service.application.domain.Dictionary;
import com.iwantrun.core.service.application.domain.LocationAttachments;
import com.iwantrun.core.service.application.domain.LocationTags;
import com.iwantrun.core.service.application.domain.Locations;
import com.iwantrun.core.service.application.domain.SearchDictionaryList;
import com.iwantrun.core.service.application.intercepter.ControllInvokerIntercepter;
import com.iwantrun.core.service.application.transfer.SimpleMessageBody;
import com.iwantrun.core.service.utils.JPADBUtils;
import com.iwantrun.core.service.utils.PageDataWrapUtils;

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
	private LocationTagsDao tagsDao;
	
	@Autowired
	private Environment environment;
	@Autowired
	private JPQLEnableRepository jpqlExecute;
	@Autowired  
    private Environment env;
	@Autowired
	private DictionaryDao dictionaryDao;
	
	@Transactional
	public boolean createLocations(Locations location,List<LocationAttachments> attachments,List<LocationTags> tags) {
		boolean executeResult = false ;
		locationDao.saveAndFlush(location);
		if(attachments != null) {
			for(LocationAttachments attach: attachments) {
				attach.setLocation_id(location.getId());
			}
			attachmentsDao.saveAll(attachments);
		}		
		if(tags != null) {
			for(LocationTags tag : tags) {
				tag.setLocationId(location.getId());
			}
			tagsDao.saveAll(tags);
		}	
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

	public String get(Integer id) {
		String response = "";
		Optional<Locations> locationOP= locationDao.findById(id);
		if(locationOP.isPresent()) {
			Locations location = locationOP.get();
			List<LocationAttachments> listAttch  = attachmentsDao.findByLocationId(location.getId());
			List<LocationTags> listTag = tagsDao.findByLocationId(location.getId());
			JSONObject json = new JSONObject();
			json.put("location", JSONValue.toJSONString(location));
			json.put("listAttch", JSONValue.toJSONString(listAttch));
			json.put("listTag", JSONValue.toJSONString(listTag));
			response = json.toJSONString();
		}
		return response;
	}

	/**
	 * 1.delete all related componaunts  2.add new  attached componaunts
	 * @param location
	 * @param locationAttachments
	 * @param tagsList
	 * @return
	 */
	@Transactional
	public SimpleMessageBody modifyLocations(Locations location, List<LocationAttachments> locationAttachments,
			List<LocationTags> tagsList) {
		SimpleMessageBody body = new SimpleMessageBody();
		body.setSuccessful(false);
		int locationId = location.getId();
		locationDao.save(location);	
		List<LocationAttachments> dbLocationAttachments = attachmentsDao.findByLocationId(locationId);
		List<LocationTags> dbLocationTags = tagsDao.findByLocationId(locationId);
		attachmentsDao.deleteAll(dbLocationAttachments);
		tagsDao.deleteAll(dbLocationTags);
		for(LocationAttachments attach : locationAttachments) {
			attach.setLocation_id(locationId);
		}
		attachmentsDao.saveAll(locationAttachments);
		for(LocationTags tags : tagsList) {
			tags.setLocationId(locationId);
		}
		tagsDao.saveAll(tagsList);
		body.setSuccessful(true);
		return body;
	}
	
	public PageImpl<Locations> queryLocationByDictListConditionPageable
	(SearchDictionaryList vo,String pageIndex){	
		Integer pageSize = Integer.parseInt(environment.getProperty("common.pageSize"));
		int pageIndexInt =  pageIndex == null ? 1:Integer.parseInt(pageIndex)+1 ;
		Pageable page =  PageRequest.of(pageIndexInt-1, pageSize, Sort.Direction.ASC, "id");
		Long totalNum = locationDao.countByMutipleParams(vo,jpqlExecute);
		List<Locations> content = locationDao.findByMutipleParams(vo,jpqlExecute,pageSize,pageIndexInt);
		for( Locations loVo :content) {
			List<LocationTags> listTag = tagsDao.findByLocationId(loVo.getId());
			if(listTag!= null && listTag.size() >0 ) {
				String[] tips =new String[listTag.size()];
				for( int i=0;i< listTag.size();i++ ) {
					Dictionary dic = dictionaryDao.findByFiledNameCode(String.valueOf(listTag.get(i).getTagsType()),"location",listTag.get(i).getTagsCode());
					tips[i]=dic.getValue();
				}
				loVo.setTips(tips);
			}
		}
		PageImpl<Locations> result = new PageImpl<Locations>(content, page, totalNum);
		return result;
	}
	
}
