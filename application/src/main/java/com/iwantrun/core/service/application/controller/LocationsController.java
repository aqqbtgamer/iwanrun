package com.iwantrun.core.service.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.core.service.application.annotation.NeedTokenVerify;
import com.iwantrun.core.service.application.dao.JPQLEnableRepository;
import com.iwantrun.core.service.application.dao.LocationTagsDao;
import com.iwantrun.core.service.application.domain.Cases;
import com.iwantrun.core.service.application.domain.Dictionary;
import com.iwantrun.core.service.application.domain.LocationAttachments;
import com.iwantrun.core.service.application.domain.LocationTags;
import com.iwantrun.core.service.application.domain.Locations;
import com.iwantrun.core.service.application.domain.SearchDictionaryArray;
import com.iwantrun.core.service.application.domain.SearchDictionaryList;
import com.iwantrun.core.service.application.service.DictionaryService;
import com.iwantrun.core.service.application.service.LocationsService;
import com.iwantrun.core.service.application.transfer.Message;
import com.iwantrun.core.service.application.transfer.PageDomianRequest;
import com.iwantrun.core.service.application.transfer.SimpleMessageBody;
import com.iwantrun.core.service.utils.DictionaryConfigParams;
import com.iwantrun.core.service.utils.EntityBeanUtils;
import com.iwantrun.core.service.utils.EntityDictionaryConfigUtils;
import com.iwantrun.core.service.utils.JPADBUtils;
import com.iwantrun.core.service.utils.JSONUtils;
import com.iwantrun.core.service.utils.ListUpdateUtils;
import com.iwantrun.core.service.utils.MappingGenerateUtils;
import com.iwantrun.core.service.utils.PageDataWrapUtils;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@RestController
public class LocationsController {
	
	Logger logger = LoggerFactory.getLogger(LocationsController.class);
	
	@Autowired
	private LocationsService locationService;
	
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private LocationTagsDao locationTagsDao;
	@Autowired
	private JPQLEnableRepository jpqlExecute;
	

	@SuppressWarnings("unchecked")
	@RequestMapping("/application/location/add")
	@NeedTokenVerify
	public Message addLocation(@RequestBody Message message) {
		Message response = new Message();
		response.setAccessToken(message.getAccessToken());
		response.setRequestMethod(message.getRequestMethod());
		Locations location = new Locations();
		String dataJson = message.getMessageBody();
		JSONObject object = (JSONObject) JSONValue.parse(dataJson);
		Map<String,String> mappingRelation = 
				MappingGenerateUtils.generateMappingRelation(new String[] {
						"name =>name",
						"activeTypeCode=>activity_type_code",
						"locationTypeCode=>location_type_code",
						"groupNumberLimitCode=>group_number_limit_code",
						"activityProvinceCode=>activity_province_code",
						"activityCityCode=>activity_city_code",
						"activityDistCode=>activity_dist_code",
						"location=>location",
						"priority=>priority",
						"descirbeText1=>_ue",
						"descirbeText2=>mainImage",
						"descirbeText3=>descirbeText3",
						"simulatePriceCode=>simulatePriceCode"
				});
		EntityBeanUtils.beanCreateFromJson(location, mappingRelation, object);
		List<LocationAttachments> locationAttachments = new ArrayList<LocationAttachments>();
		Map<String,String> mappingRelation0 = 
				MappingGenerateUtils.generateMappingRelation(new String[] {
						"filePath=>bean"
				});
		JSONArray array = (JSONArray) object.get("imgManage[]");		
		EntityBeanUtils.listBeanCreateFromJson(locationAttachments, mappingRelation0, array, LocationAttachments.class);
		Function<String,String> fun = s -> {
			int index = s.lastIndexOf("/");
			return s.substring(index+1);
		} ;
		
		BiFunction<String,Integer,String> biFun = (value,index) -> value+"-"+index ;
		ListUpdateUtils.updateListProperty(locationAttachments, 
				new String[] {
						"filePath=>fileName"
				}, 
				new Function[] {
						fun
				}
				, new String[] {
						"pagePath==sideImage"
				}
		 		, (BiFunction<String,Integer,String>[])new BiFunction[]{
						biFun
				} 
		);
		JSONArray tags = (JSONArray) object.get("special_tags[]");
		Map<String,String> mappingRelation1 = 
				MappingGenerateUtils.generateMappingRelation(new String[] {
						"tagsCode=>bean"
				});
		List<LocationTags> tagsList = new ArrayList<LocationTags>();
		EntityBeanUtils.listBeanCreateFromJson(tagsList, mappingRelation1, tags, LocationTags.class);
		Supplier<Integer> tagsTypeSupplier = () ->{
			return DictionaryConfigParams.LOCATION_TAGS_TYPE;
		};
		ListUpdateUtils.updateListPropertyWithSupplier(tagsList, new String[]{
				"tagsType"
		}, new Supplier[] {
			tagsTypeSupplier
		});
		boolean updateResult =locationService.createLocations(location, locationAttachments,tagsList);
		if(updateResult) {			
			response.setMessageBody(String.valueOf(location.getId()));
		}else {
			response.setMessageBody("failed");
		}
		return response;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/application/location/modify")
	@NeedTokenVerify
	public Message modifyLocation(@RequestBody Message message) {
		String dataJson = message.getMessageBody();
		logger.info("received json:"+dataJson);
		Locations location = new Locations();
		JSONObject object = (JSONObject) JSONValue.parse(dataJson);
		Map<String,String> mappingRelation = 
				MappingGenerateUtils.generateMappingRelation(new String[] {
						"id=>id",
						"name =>name",
						"activeTypeCode=>activity_type_code",
						"locationTypeCode=>location_type_code",
						"groupNumberLimitCode=>group_number_limit_code",
						"activityProvinceCode=>activity_province_code",
						"activityCityCode=>activity_city_code",
						"activityDistCode=>activity_dist_code",
						"location=>location",
						"priority=>priority",
						"descirbeText1=>_ue",
						"descirbeText2=>mainImage",
						"descirbeText3=>descirbeText3",
						"simulatePriceCode=>simulatePriceCode"
				});
		EntityBeanUtils.beanCreateFromJson(location, mappingRelation, object);
		List<LocationAttachments> locationAttachments = new ArrayList<LocationAttachments>();
		Map<String,String> mappingRelation0 = 
				MappingGenerateUtils.generateMappingRelation(new String[] {
						"filePath=>bean"
				});
		JSONArray array = (JSONArray) object.get("imgManage[]");		
		EntityBeanUtils.listBeanCreateFromJson(locationAttachments, mappingRelation0, array, LocationAttachments.class);
		Function<String,String> fun = s -> {
			int index = s.lastIndexOf("/");
			return s.substring(index+1);
		} ;
		
		BiFunction<String,Integer,String> biFun = (value,index) -> value+"-"+index ;
		ListUpdateUtils.updateListProperty(locationAttachments, 
				new String[] {
						"filePath=>fileName"
				}, 
				new Function[] {
						fun
				}
				, new String[] {
						"pagePath==sideImage"
				}
		 		, (BiFunction<String,Integer,String>[])new BiFunction[]{
						biFun
				} 
		);
		JSONArray tags = (JSONArray) object.get("special_tags[]");
		Map<String,String> mappingRelation1 = 
				MappingGenerateUtils.generateMappingRelation(new String[] {
						"tagsCode=>bean"
				});
		List<LocationTags> tagsList = new ArrayList<LocationTags>();
		EntityBeanUtils.listBeanCreateFromJson(tagsList, mappingRelation1, tags, LocationTags.class);
		Supplier<Integer> tagsTypeSupplier = () ->{
			return DictionaryConfigParams.LOCATION_TAGS_TYPE;
		};
		ListUpdateUtils.updateListPropertyWithSupplier(tagsList, new String[]{
				"tagsType"
		}, new Supplier[] {
			tagsTypeSupplier
		});
		SimpleMessageBody updateResult =locationService.modifyLocations(location, locationAttachments,tagsList);
		message.setMessageBody(JSONValue.toJSONString(updateResult));
		return message;
	}
	
	@RequestMapping("/application/location/findAll")
	public Message findAll(@RequestBody Message message) {
		String dataJson = message.getMessageBody();
		JSONObject object = (JSONObject) JSONValue.parse(dataJson);
		String pageIndex = object.getAsString("pageIndex");
		Page<Locations> resultPage =locationService.findAllLocationsPageable(Integer.parseInt(pageIndex));
		Map<String,Dictionary> dictionnaryMap = EntityDictionaryConfigUtils.getDictionaryMaping(new Locations());
		dictionaryService.dictionaryFilter(resultPage.getContent(), dictionnaryMap);
		message.setMessageBody(PageDataWrapUtils.page2Json(resultPage));
		return message;		
	}
	
	@RequestMapping("/application/location/findByExample")
	public Message findByExample(@RequestBody Message message) {
		String dataJson = message.getMessageBody();
		PageDomianRequest example = JSONUtils.jsonToObj(dataJson, PageDomianRequest.class);
		Page<Locations> resultPage = null ;
		if(!example.getObj().containsKey("*")) {
			resultPage =locationService.queryLocationByConditionPageable(example.getPageIndex(), example.getObjAsType(Locations.class));
		}else {
			String value = (String) example.getObj().get("*");
			Locations defaultLocation = new Locations();
			defaultLocation.setName(value);
			defaultLocation.setLocation(value);
			String[] defaultSpecification = new String[] {
					"like,name,or",
					"like,location,or",
			};
			Specification<Locations> specification = JPADBUtils.generateSpecificationFromExample(defaultLocation, defaultSpecification);
			resultPage = locationService.queryLocationBySpecificationPageable(example.getPageIndex(), specification);
		}
		Map<String,Dictionary> dictionnaryMap = EntityDictionaryConfigUtils.getDictionaryMaping(new Locations());
		dictionaryService.dictionaryFilter(resultPage.getContent(), dictionnaryMap);
		message.setMessageBody(PageDataWrapUtils.page2Json(resultPage));
		return message;		
	}
	
	@RequestMapping("/application/location/mobileQuery")
	public Message mobileQuery(@RequestBody Message message) {
		String dataJson = message.getMessageBody();
		PageDomianRequest example = JSONUtils.jsonToObj(dataJson, PageDomianRequest.class);
		Page<Locations> resultPage = null ;
		Locations defaultLocation = new Locations();
		Map<String,Object> queryObj = example.getObj();
		String queryName = (String) queryObj.get("name");
		defaultLocation.setName(queryName);
		String[] defaultSpecification = new String[] {
				"like,name,and"
		};
		Specification<Locations> specification = JPADBUtils.generateSpecificationFromExample(defaultLocation, defaultSpecification);
		resultPage = locationService.queryLocationBySpecificationPageable(example.getPageIndex(), specification);
		Map<String,Dictionary> dictionnaryMap = EntityDictionaryConfigUtils.getDictionaryMaping(new Locations());
		dictionaryService.dictionaryFilter(resultPage.getContent(), dictionnaryMap);
		message.setMessageBody(PageDataWrapUtils.page2Json(resultPage));
		return message;		
	}
	
	@RequestMapping("/application/location/delete")
	@NeedTokenVerify
	public Message delete(@RequestBody Message message) {
		String id = message.getMessageBody();
		String result = locationService.delete(id);
		message.setMessageBody(String.valueOf(result));
		return message ;
	}
	
	@RequestMapping("/application/location/get")
	public Message get(@RequestBody Message message) {
		String jsonId = message.getMessageBody();
		JSONObject objectId = (JSONObject) JSONValue.parse(jsonId);
		Integer id = Integer.parseInt(objectId.getAsString("id"));
		String result = locationService.get(id);
		message.setMessageBody(result);
		return message;
	}
	@RequestMapping("/application/location/queryLocationByCondition")
	public Message queryCaseByCondition(@RequestBody Message message) {
		String dataJson = message.getMessageBody();
		SearchDictionaryList queryVo =JSONUtils.jsonToObj(dataJson, SearchDictionaryList.class);
		String json = locationListQuery(queryVo);
		message.setMessageBody(json);
		return message;		
	}
	
	public String locationListQuery(SearchDictionaryList queryVo) {
		if( queryVo != null ) {
			SearchDictionaryList vo = new SearchDictionaryList();
			List<String> activityProvinceCode = new ArrayList<>();
			List<String> activitytype = new ArrayList<>();
			List<Integer> duration = new ArrayList<>();
			List<String> personNum = new ArrayList<>();
			List<String> locationTypeCode = new ArrayList<>();
			List<Integer> specialTagsCode = new ArrayList<>();
			List<String> activityProvinceCodeArray = queryVo.getActivityProvinceCode();
			if(activityProvinceCodeArray != null && activityProvinceCodeArray.size() > 0) {
				activityProvinceCode = dictionaryService.dictionaryParamSwitchString(activityProvinceCodeArray);
				vo.setActivityProvinceCode(activityProvinceCode);
			}
			List<String> activitytypeArray = queryVo.getActivitytype();
			if(activitytypeArray != null && activitytypeArray.size() > 0) {
				activitytype = dictionaryService.dictionaryParamSwitchString(activitytypeArray);
				vo.setActivitytype(activitytype);
			}
			
			List<Integer> durationArray = queryVo.getDuration();
			if(durationArray != null && durationArray.size() > 0) {
				duration = dictionaryService.dictionaryParamSwitch(durationArray);
				vo.setDuration(duration);
			}
			List<String> personNumArray = queryVo.getPersonNum();
			if(personNumArray != null && personNumArray.size() > 0) {
				personNum = dictionaryService.dictionaryParamSwitchString(personNumArray);
				vo.setPersonNum(personNum);
			}
			List<Integer> specialTagsCodArray = queryVo.getSpecialTagsCode();
			if(specialTagsCodArray != null && specialTagsCodArray.size() > 0) {
				specialTagsCode = dictionaryService.dictionaryParamSwitch(specialTagsCodArray);
				List<LocationTags> locationTagList = locationTagsDao.findByTagsCodes(specialTagsCode,jpqlExecute);
				for(LocationTags tag : locationTagList) {
					specialTagsCode.add(tag.getLocationId());
				}
				vo.setSpecialTagsCode(specialTagsCode);
			}
			List<String> locationTypeCodeArray = queryVo.getLocationTypeCode();
			if(locationTypeCodeArray != null && locationTypeCodeArray.size() > 0) {
				locationTypeCode = dictionaryService.dictionaryParamSwitchString(locationTypeCodeArray);
				vo.setLocationTypeCode(locationTypeCode);
			}
			vo.setPageSize(queryVo.getPageSize());
			PageImpl<Locations> result = locationService.queryLocationByDictListConditionPageable( vo, queryVo.getPageIndex());
			Map<String,Dictionary> dictionnaryMap = EntityDictionaryConfigUtils.getDictionaryMaping(new Locations());
			dictionaryService.dictionaryFilter(result.getContent(), dictionnaryMap);
			return PageDataWrapUtils.page2JsonNoCopy(result);
		}
		return "";
		
	} 

}
