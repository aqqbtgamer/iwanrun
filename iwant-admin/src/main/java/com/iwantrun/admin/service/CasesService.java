package com.iwantrun.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.admin.transfer.Message;
import com.iwantrun.admin.utils.CookieUtils;
import com.iwantrun.admin.utils.FormDataUtils;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@Service
public class CasesService {
	
	@Autowired  
    private Environment env;  
	
	@Autowired
    private RestTemplate restTemplate;
	
	
	public String addCase(HttpServletRequest request) {		
		String token = CookieUtils.getLoginToken(request);
		List<String> paramList = FormDataUtils.stringArray2List(new String[] {
				"id=>id",
				"name",
				"activityProvinceCode",
				"activityCityCode",
				"activityDistCode",
				"location",
				"activityTypeCode",
				"companyTypeCode",
				"groupNumber",
				"during",
				"specialKeyWord[]",
				"designDuringCode",
			    "executeDuringCode",
			    "trafficInfo",
			    "foodInfo",
			    "hotelInfo",
			    "priority",
			    "simulatePriceCode",
			    "orderId",
		        "mainImage",
		        "descirbeText3",
		        "imgManage[]",
		        "_ue"
				
		});
		String json = FormDataUtils.formData2Json(request,paramList);
		String postUrl = env.getProperty("application.cases.add");
		String baseUrl = env.getProperty("application.serverbase");
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(json);
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);		
		return response == null ? null : response.getBody().getMessageBody();
	}


	public String findAll(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		List<String> paramList = FormDataUtils.stringArray2List(new String[] {
				"pageIndex"
		});
		String json = FormDataUtils.formData2Json(request,paramList);
		String postUrl = env.getProperty("application.cases.findAll");
		String baseUrl = env.getProperty("application.serverbase");
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(json);
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);		
		return response == null ? null : response.getBody().getMessageBody();
	}


	public String queryAll(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		List<String> paramList = FormDataUtils.stringArray2List(new String[] {
				"pageIndex",
				"obj"
		});
		JSONObject json = FormDataUtils.formData2JsonObj(request,paramList);
		JSONObject obj  = (JSONObject) JSONValue
				.parse(json.getAsString("obj"));
		json.put("obj", obj);
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(json.toJSONString());
		String postUrl = env.getProperty("application.cases.queryAll");
		String baseUrl = env.getProperty("application.serverbase");
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);	
		return response == null ? null : response.getBody().getMessageBody();
	}


	public String delete(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		List<String> paramList = FormDataUtils.stringArray2List(new String[] {
				"id",
				"id[]"
		});
		JSONObject json = FormDataUtils.formData2JsonObj(request,paramList);
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(json.toJSONString());
		String postUrl = env.getProperty("application.cases.delete");
		String baseUrl = env.getProperty("application.serverbase");
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);	
		return response == null ? null : response.getBody().getMessageBody();
	}


	public String getCase(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		List<String> paramList = FormDataUtils.stringArray2List(new String[] {
				"id"
		});
		JSONObject json = FormDataUtils.formData2JsonObj(request,paramList);
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(json.toJSONString());
		String postUrl = env.getProperty("application.cases.get");
		String baseUrl = env.getProperty("application.serverbase");
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);	
		return response == null ? null : response.getBody().getMessageBody();
	}


	public String modifyCase(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		List<String> paramList = FormDataUtils.stringArray2List(new String[] {
				"id",
				"name",
				"activityProvinceCode",
				"activityCityCode",
				"activityDistCode",
				"location",
				"activityTypeCode",
				"companyTypeCode",
				"groupNumber",
				"during",
				"specialKeyWord[]",
				"designDuringCode",
			    "executeDuringCode",
			    "trafficInfo",
			    "foodInfo",
			    "hotelInfo",
			    "priority",
			    "simulatePriceCode",
			    "orderId",
		        "mainImage",
		        "descirbeText3",
		        "imgManage[]",
		        "_ue"
		});
		String json = FormDataUtils.formData2Json(request,paramList);
		String postUrl = env.getProperty("application.cases.modify");
		String baseUrl = env.getProperty("application.serverbase");
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(json);
		message.setRequestMethod(baseUrl+postUrl);
		ResponseEntity<Message> response = restTemplate.postForEntity(baseUrl+postUrl, message, Message.class);		
		return response == null ? null : response.getBody().getMessageBody();
	}

}
