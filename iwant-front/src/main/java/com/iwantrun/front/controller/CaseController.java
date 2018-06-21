package com.iwantrun.front.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iwantrun.front.domain.Case;
import com.iwantrun.front.service.CaseService;
import com.iwantrun.front.service.DictionaryService;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.utils.JSONUtils;

@Controller
@RequestMapping("case")
public class CaseController {
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private CaseService caseService;
	@SuppressWarnings("rawtypes")
	@RequestMapping("/caseSearchList")
	@ResponseBody
	public Case caseSearchList(@RequestBody String param) {

		try {
			Map map = JSONUtils.jsonToMap(param);
			String name=(String) map.get("name");
			Message result = dictionaryService.queryDictionaryList(name);//查询出公共字典所有数据
			Case caseVo =caseService.caseDictionaryDataDo(result.getMessageBody()); //筛选出 案例需要数据
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
}
