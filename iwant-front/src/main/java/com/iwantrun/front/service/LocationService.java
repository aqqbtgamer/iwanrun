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
public class LocationService {
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
	public SearchDictionary locationDictionaryDataDo(String jsonCommon,String jsonLocation) {
		List<Dictionary> dictoryListCommon = JSONUtils.toList(jsonCommon, Dictionary.class);
		List<Dictionary> dictoryListLocation = JSONUtils.toList(jsonLocation, Dictionary.class);
		SearchDictionary vo = new SearchDictionary();
		List<Dictionary> activitytypeList = dicService.filterByUsedField(dictoryListCommon,"9");
		vo.setActivitytype(activitytypeList);//设置活动类型
		vo.setCompanytype(dicService.filterByUsedField(dictoryListCommon,"24"));
		vo.setPersonNum(dicService.filterByUsedField(dictoryListCommon,"22"));
		vo.setDuration(dicService.filterByUsedField(dictoryListCommon,"23"));
		vo.setActivityProvinceCode(dicService.filterByUsedField(dictoryListCommon,"6"));
		vo.setLocationTypeCode(dicService.filterByUsedField(dictoryListLocation,"2"));
		vo.setSpecialTagsCode(dicService.filterByUsedField(dictoryListLocation,"1"));
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
	public Message queryLocationByCondition(String param) {
		String findByName = environment.getProperty("application.location.queryLocationByCondition");
		String baseUrl = environment.getProperty("app.server");
		
		String url = baseUrl + findByName;

		Message message = new Message();
		message.setMessageBody(param);
		message.setRequestMethod(url);
		message = template.postForEntity(url, message, Message.class).getBody();
		
		return message;
	}
	
	public Message queryDetailById(String id, String token) {
		JSONObject requestObj = new JSONObject();
		requestObj.put("id", id);
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(requestObj.toJSONString());
		String queryDetailById = environment.getProperty("application.location.queryDetailById");
		String baseUrl = environment.getProperty("app.server");	
		message.setRequestMethod(baseUrl+queryDetailById);
		message = template.postForEntity(baseUrl+queryDetailById, message, Message.class).getBody();
		return message;
	}
	
	public Message mobileQuery(String param) {
		String findByName = "/application/location/mobileQuery";
		String baseUrl = environment.getProperty("app.server");		
		String url = baseUrl + findByName;
		Message message = new Message();
		message.setMessageBody(param);
		message.setRequestMethod(url);
		message = template.postForEntity(url, message, Message.class).getBody();		
		return message;
	}
	
}
