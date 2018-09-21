package com.iwantrun.core.service.application.controller;

import java.util.List;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.core.constant.AdminApplicationConstants;
import com.iwantrun.core.service.application.annotation.NeedTokenVerify;
import com.iwantrun.core.service.application.domain.WebSiteCooperativeLogo;
import com.iwantrun.core.service.application.service.WebSiteCooperativeLogoService;
import com.iwantrun.core.service.application.transfer.Message;
import com.iwantrun.core.service.utils.JSONUtils;
import com.iwantrun.core.service.utils.PageDataWrapUtils;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@RestController
@RequestMapping("/application/webSiteCooperativeLogo")
public class WebSiteCooperativeLogoController {
	
	Logger logger = LoggerFactory.getLogger(WebSiteCooperativeLogoController.class);
	
	@Autowired
	private WebSiteCooperativeLogoService logoService;
	
	@RequestMapping("findAll")
	public Message findAll(@RequestBody Message message) {
		List<WebSiteCooperativeLogo> resultList = logoService.findAll();
		message.setMessageBody(JSONUtils.objToJSON(resultList));
		return message ;
	}
	
	@RequestMapping("findAllPaged")
	public Message findAllPaged(@RequestBody Message message) {
		String dataJson = message.getMessageBody();
		JSONObject object = (JSONObject) JSONValue.parse(dataJson);		
		Page<WebSiteCooperativeLogo> resultList = logoService.findAllPaged(object);
		resultList.stream().forEach(logo -> logo.setLogoPath("<img width='120' src='"+logo.getLogoPath() + "'/>"));
		message.setMessageBody(PageDataWrapUtils.page2Json(resultList));
		return message ;
	}
	
	
	@RequestMapping("query")
	public Message query(@RequestBody Message message) {
		try {
			String dataJson = message.getMessageBody();		
		    JSONObject jsonObj =  (JSONObject) JSONValue.parse(dataJson);
		    JSONObject obj = (JSONObject) jsonObj.get("obj");
		    String pageIndexStr = jsonObj.getAsString("pageIndex");
		    obj.put("pageIndex", pageIndexStr);
		    Page<WebSiteCooperativeLogo> pageResult = logoService.findByName(obj);		  
			pageResult.stream().forEach(logo -> logo.setLogoPath("<img width='120' src='"+logo.getLogoPath() + "'/>"));
		    message.setMessageBody(PageDataWrapUtils.page2Json(pageResult));
		}catch(Exception e) {
			logger.error("查询",e);
			 message.setMessageBody(AdminApplicationConstants.Add_FAIL_RESULT);
		}		
		return message;
	}
	
	
	@RequestMapping("add")
	@NeedTokenVerify
	public Message add(@RequestBody Message message) {		
		String dataJson = message.getMessageBody();		
		WebSiteCooperativeLogo webSiteCooperativeLogo = JSONUtils.jsonToObj(dataJson, WebSiteCooperativeLogo.class);		
		String result = logoService.add(webSiteCooperativeLogo);
		message.setMessageBody(result);
		return message;
	}
	
	@RequestMapping("delete")
	@NeedTokenVerify
	public Message delete(@RequestBody Message message) {
		String dataJson = message.getMessageBody();		
	    JSONObject jsonObj =  (JSONObject) JSONValue.parse(dataJson);		    
	    String result = logoService.delete(jsonObj);
	    message.setMessageBody(result);
		return message;
	}
	
	

}
