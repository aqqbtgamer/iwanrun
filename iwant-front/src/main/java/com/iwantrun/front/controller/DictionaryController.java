package com.iwantrun.front.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iwantrun.front.service.DictionaryService;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.utils.JSONUtils;



@Controller
@RequestMapping("dictionary")
public class DictionaryController {
	@Autowired
	private DictionaryService dictionaryService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/findByName")
	@ResponseBody
	public String findByName(@RequestBody String param) {

		try {
			Map map = JSONUtils.jsonToMap(param);
			String name=(String) map.get("name");
			Message result = dictionaryService.queryDictionaryList(name);
			return JSONUtils.objToJSON(result);
		} catch (Exception e) {
			
		}
		return null;
		
	} 

}
