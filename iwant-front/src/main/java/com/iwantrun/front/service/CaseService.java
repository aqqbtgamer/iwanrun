package com.iwantrun.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.front.domain.Case;
import com.iwantrun.front.domain.Dictionary;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.utils.JSONUtils;
@Service
public class CaseService {
	@Autowired
	private Environment environment;
	@Autowired
	private RestTemplate template;
	@Autowired
	private DictionaryService dicService;
	
	public Case caseDictionaryDataDo(String messageBodyJson) {
		List<Dictionary> dictoryList = JSONUtils.toList(messageBodyJson, Dictionary.class);
		Case caseVo = new Case();
		List<Dictionary> activitytypeList = dicService.filterByUsedField(dictoryList,"9");
		caseVo.setActivitytype(activitytypeList);//设置活动类型
		caseVo.setCompanytype(dicService.filterByUsedField(dictoryList,"24"));
		caseVo.setPersonNum(dicService.filterByUsedField(dictoryList,"22"));
		caseVo.setDuration(dicService.filterByUsedField(dictoryList,"23"));
//		caseVo.setLocation(dicService.filterByUsedField(dictoryList,"24"));
		return caseVo;
		
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
}
