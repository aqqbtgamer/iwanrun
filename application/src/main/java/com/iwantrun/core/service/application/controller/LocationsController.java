package com.iwantrun.core.service.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.core.service.application.annotation.NeedTokenVerify;
import com.iwantrun.core.service.application.domain.LocationAttachments;
import com.iwantrun.core.service.application.domain.Locations;
import com.iwantrun.core.service.application.service.LocationsService;
import com.iwantrun.core.service.application.transfer.Message;
import com.iwantrun.core.service.application.transfer.PageDomianRequest;
import com.iwantrun.core.service.utils.EntityBeanUtils;
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
						"activeTypeCode =>location_type_code",
						"groupNumberLimitCode=>group_number_limit_code",
						"activityProvinceCode=>activity_province_code",
						"activityCityCode=>activity_city_code",
						"activityDistCode=>activity_dist_code",
						"location=>location",
						"descirbeText1=>_ue",
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
		boolean updateResult =locationService.createLocations(location, locationAttachments);
		if(updateResult) {			
			response.setMessageBody(String.valueOf(location.getId()));
		}else {
			response.setMessageBody("failed");
		}
		return response;
	}
	
	@RequestMapping("/application/location/findAll")
	public Message findAll(@RequestBody Message message) {
		String dataJson = message.getMessageBody();
		JSONObject object = (JSONObject) JSONValue.parse(dataJson);
		String pageIndex = object.getAsString("pageIndex");
		Page<Locations> resultPage =locationService.findAllLocationsPageable(Integer.parseInt(pageIndex));
		message.setMessageBody(PageDataWrapUtils.page2Json(resultPage));
		return message;		
	}
	
	@RequestMapping("/application/location/findByExample")
	public Message findByExample(@RequestBody Message message) {
		String dataJson = message.getMessageBody();
		@SuppressWarnings("unchecked")
		PageDomianRequest<Locations> example = JSONValue.parse(dataJson,PageDomianRequest.class);
		Page<Locations> resultPage = locationService.queryLocationByConditionPageable(example.getPageIndex(), example.getObj());
		message.setMessageBody(PageDataWrapUtils.page2Json(resultPage));
		return message;		
	}

}
