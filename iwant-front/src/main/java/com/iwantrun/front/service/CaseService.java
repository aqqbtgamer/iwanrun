package com.iwantrun.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.front.domain.SearchDictionary;
import com.iwantrun.front.domain.Dictionary;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.utils.JSONUtils;

import net.minidev.json.JSONObject;
@Service
public class CaseService {
	@Autowired
	private Environment environment;
	@Autowired
	private RestTemplate template;
	@Autowired
	private DictionaryService dicService;
	
	/**
	 * @param messageBodyJson
	 * @return
	 */
	public SearchDictionary caseDictionaryDataDo(String messageBodyJson) {
		List<Dictionary> dictoryList = JSONUtils.toList(messageBodyJson, Dictionary.class);
		SearchDictionary vo = new SearchDictionary();
		List<Dictionary> activitytypeList = dicService.filterByUsedField(dictoryList,"9");
		vo.setActivitytype(activitytypeList);//设置活动类型
		vo.setCompanytype(dicService.filterByUsedField(dictoryList,"24"));
		vo.setPersonNum(dicService.filterByUsedField(dictoryList,"22"));
		vo.setDuration(dicService.filterByUsedField(dictoryList,"23"));
		vo.setActivityProvinceCode(dicService.filterByUsedField(dictoryList,"6"));
		return vo;
		
	}
	public Message queryCaseList(String param) {
		String findByName = environment.getProperty("application.cases.findAll");
		String baseUrl = environment.getProperty("app.server");
		
		String url = baseUrl + findByName;

		Message message = new Message();
		message.setMessageBody(param);
		message.setRequestMethod(url);
		message = template.postForEntity(url, message, Message.class).getBody();
		
		return message;
	}
	public Message queryCaseByCondition(String param) {
		String findByName = environment.getProperty("application.cases.queryCaseByCondition");
		String baseUrl = environment.getProperty("app.server");
		
		String url = baseUrl + findByName;

		Message message = new Message();
		message.setMessageBody(param);
		message.setRequestMethod(url);
		message = template.postForEntity(url, message, Message.class).getBody();
		
		return message;
	}
	
	public Message queryDetailById(String id,String token) {
		String findByName = environment.getProperty("application.cases.queryDetailById");
		String baseUrl = environment.getProperty("app.server");		
		String url = baseUrl + findByName;
		JSONObject requestObj = new JSONObject();
		requestObj.put("id", id);
		Message message = new Message();
		message.setMessageBody(requestObj.toJSONString());
		message.setRequestMethod(url);
		message.setAccessToken("token");
		message = template.postForEntity(url, message, Message.class).getBody();
		
		return message;
	}
}
