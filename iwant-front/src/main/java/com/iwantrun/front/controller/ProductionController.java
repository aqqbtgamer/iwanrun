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
import com.iwantrun.front.service.ProductionService;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.utils.CookieUtils;
import com.iwantrun.front.utils.JSONUtils;

@Controller
@RequestMapping("production")
public class ProductionController {
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private ProductionService produtionService;
	@SuppressWarnings("rawtypes")
	@RequestMapping("/produtionSearchList")
	@ResponseBody
	public SearchDictionary produtionSearchList(@RequestBody String param) {

		try {
			Map map = JSONUtils.jsonToMap(param);
			String name=(String) map.get("name");
			Message result = dictionaryService.queryDictionaryList(name);//查询出公共字典所有数据
			Message resultProduction = dictionaryService.queryDictionaryList("production");
			SearchDictionary caseVo =produtionService.productionDictionaryDataDo(result.getMessageBody(),resultProduction.getMessageBody()); //筛选出 案例需要数据
			return caseVo;
		} catch (Exception e) {
			
		}
		return null;
		
	} 

	@RequestMapping("/queryProdutionByCondition")
	@ResponseBody
	public String queryProdutionByCondition(@RequestBody String param) {

		try {
			Message result =produtionService.queryProdutionByCondition(param); //筛选出 案例需要数据
			if( result != null) {
				return result.getMessageBody();
			}
		} catch (Exception e) {
			
		}
		return null;
		
	}
	
	@RequestMapping("/getDetailsById")
	@ResponseBody
	public String queryDetailsById(HttpServletRequest request) {
		String id = request.getParameter("id");
		String token = CookieUtils.getLoginToken(request);
		Message result = produtionService.queryDetailById(id, token);
		if(result != null) {
			return result.getMessageBody();
		}else {
			return null ;
		}
		
	}
}
