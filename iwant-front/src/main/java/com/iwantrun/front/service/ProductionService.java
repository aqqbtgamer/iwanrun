package com.iwantrun.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.front.domain.SearchDictionary;
import com.iwantrun.front.domain.Dictionary;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.utils.CookieUtils;
import com.iwantrun.front.utils.JSONUtils;
@Service
public class ProductionService {
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
	public SearchDictionary productionDictionaryDataDo(String jsonCommon,String jsonLocation) {
		List<Dictionary> dictoryListCommon = JSONUtils.toList(jsonCommon, Dictionary.class);
		List<Dictionary> dictoryListProdution = JSONUtils.toList(jsonLocation, Dictionary.class);
		SearchDictionary vo = new SearchDictionary();
		List<Dictionary> activitytypeList = dicService.filterByUsedField(dictoryListCommon,"9");
		vo.setPersonNum(dicService.filterByUsedField(dictoryListCommon,"22"));
		vo.setDuration(dicService.filterByUsedField(dictoryListCommon,"23"));
		vo.setActivityProvinceCode(dicService.filterByUsedField(dictoryListCommon,"6"));
		vo.setActivitytype(activitytypeList);//设置活动类型
		vo.setSpecialTagsCode(dicService.filterByUsedField(dictoryListProdution,"11"));
		vo.setOrderSimulatePriceCode(dicService.filterByUsedField(dictoryListProdution,"13"));
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
	public Message queryProdutionByCondition(String param) {
		String findByName = environment.getProperty("application.production.queryProdutionByCondition");
		String baseUrl = environment.getProperty("app.server");
		
		String url = baseUrl + findByName;

		Message message = new Message();
		message.setMessageBody(param);
		message.setRequestMethod(url);
		message = template.postForEntity(url, message, Message.class).getBody();
		
		return message;
	}
	
	public Message queryDetailById(String id,String token) {
		String url = environment.getProperty("application.production.queryDetailById");
		String baseUrl = environment.getProperty("app.server");
		Message message = new Message();
		message.setMessageBody(id);
		message.setRequestMethod(url+baseUrl);
		message.setAccessToken(token);
		message = template.postForEntity(url, message, Message.class).getBody();
		return message;
	}
}
