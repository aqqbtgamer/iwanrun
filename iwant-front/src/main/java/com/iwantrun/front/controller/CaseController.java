package com.iwantrun.front.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwantrun.front.domain.SearchDictionary;
import com.iwantrun.front.service.CaseService;
import com.iwantrun.front.service.DictionaryService;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.utils.CookieUtils;
import com.iwantrun.front.utils.JSONUtils;

@Controller
@RequestMapping("case")
public class CaseController {
	
	Logger logger = LoggerFactory.getLogger(CaseController.class);
	
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private CaseService caseService;
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/caseSearchList")
	@ResponseBody
	public SearchDictionary caseSearchList(@RequestBody String param) {

		try {
			Map map = JSONUtils.jsonToMap(param);
			String name=(String) map.get("name");
			Message result = dictionaryService.queryDictionaryList(name);//查询出公共字典所有数据
			SearchDictionary caseVo =caseService.caseDictionaryDataDo(result.getMessageBody()); //筛选出 案例需要数据
			return caseVo;
		} catch (Exception e) {
			
		}
		return null;
		
	} 
	
	@RequestMapping("/queryCaseList")
	@ResponseBody
	public String queryCaseList(@RequestBody String param) {

		try {
			Message result =caseService.queryCaseList(param); //筛选出 案例需要数据
			if( result != null) {
				return result.getMessageBody();
			}
		} catch (Exception e) {
			
		}
		return null;
		
	} 
	@RequestMapping("/queryCaseByCondition")
	@ResponseBody
	public String queryCaseByCondition(@RequestBody String param) {

		try {
			Message result =caseService.queryCaseByCondition(param); //筛选出 案例需要数据
			if( result != null) {
				return result.getMessageBody();
			}
		} catch (Exception e) {
			
		}
		return null;
		
	}
	
	
	@RequestMapping("/getDetailsById")
	@ResponseBody
	public String getDetailsById(HttpServletRequest request) {
		String id = request.getParameter("id");
		String token = CookieUtils.getLoginToken(request);
		try {
			Message result =caseService.queryDetailById(id,token); //筛选出 案例需要数据
			if( result != null) {
				return result.getMessageBody();
			}
		} catch (Exception e) {
			logger.error("error getting details by id ",e);
		}
		return null;
		
	}
}
