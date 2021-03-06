package com.iwantrun.core.service.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.core.service.application.domain.MobileOpenIdRelation;
import com.iwantrun.core.service.application.service.MobileOpenIdRelationService;
import com.iwantrun.core.service.application.transfer.Message;
import com.iwantrun.core.service.utils.JSONUtils;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@RestController
@RequestMapping("application/mobileOpenIdRelation")
public class MobileOpenIdRelationController {
	
	@Autowired
	public MobileOpenIdRelationService mOpenIdService ;
	
	@RequestMapping("checkMobileOpenIdExists")
	public Message checkMobileOpenIdExists(@RequestBody Message request) {
		String requestJSON = request.getMessageBody();
		JSONObject requestObj = (JSONObject) JSONValue.parse(requestJSON);
		String openId = requestObj.getAsString("openId");
		List<MobileOpenIdRelation> resultList =mOpenIdService.findByMobileOpenId(openId);
		if(resultList != null && resultList.size() == 1) {
			MobileOpenIdRelation relaton = resultList.get(0);
			String responseJson = JSONValue.toJSONString(relaton);
			request.setMessageBody(responseJson); 
		}else {
			request.setMessageBody(null);
		}
		return request ;
	}
	
	@RequestMapping("bindMobileNumber")
	public Message bindMobileNumber(@RequestBody Message request) {
		String requestJSON = request.getMessageBody();
		JSONObject requestObj = (JSONObject) JSONValue.parse(requestJSON);
		String openId = requestObj.getAsString("openId");
		String password = requestObj.getAsString("password");
		String mobileNumber = requestObj.getAsString("mobileNumber");
		request.setMessageBody(mOpenIdService.bindMobileNumber(openId, mobileNumber,password)+""); 
		return request ;
	}

}
