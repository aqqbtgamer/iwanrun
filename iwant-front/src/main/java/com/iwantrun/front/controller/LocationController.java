package com.iwantrun.front.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwantrun.front.domain.SearchDictionary;
import com.iwantrun.front.service.CaseService;
import com.iwantrun.front.service.DictionaryService;
import com.iwantrun.front.service.LocationService;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.utils.CookieUtils;
import com.iwantrun.front.utils.JSONUtils;
@Controller
@RequestMapping("location")
public class LocationController {
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private LocationService locationService;
	@SuppressWarnings("rawtypes")
	@RequestMapping("/locationSearchList")
	@ResponseBody
	public SearchDictionary locationSearchList(@RequestBody String param) {

		try {
			Map map = JSONUtils.jsonToMap(param);
			String name=(String) map.get("name");
			Message resultCommon = dictionaryService.queryDictionaryList(name);//查询出公共字典所有数据
			Message resultLocation = dictionaryService.queryDictionaryList("location");
			SearchDictionary caseVo =locationService.locationDictionaryDataDo(resultCommon.getMessageBody(),resultLocation.getMessageBody()); //筛选出 案例需要数据
			return caseVo;
		} catch (Exception e) {
			
		}
		return null;
		
	} 
	
	@RequestMapping("/querylocationByCondition")
	@ResponseBody
	public String querylocationByCondition(@RequestBody String param) {

		try {
			Message result =locationService.queryLocationByCondition(param); //筛选出 案例需要数据
			if( result != null) {
				return result.getMessageBody();
			}
		} catch (Exception e) {
			
		}
		return null;
		
	}
	
	
	@RequestMapping("getDetailsById")
	@ResponseBody
	public String queryDetailsById(HttpServletRequest request) {
		String id = request.getParameter("id");
		String token = CookieUtils.getLoginToken(request);
		Message result = locationService.queryDetailById(id, token);
		if(result != null) {
			return result.getMessageBody();
		}else {
			return null ;
		}
		
	}
	
	
}
