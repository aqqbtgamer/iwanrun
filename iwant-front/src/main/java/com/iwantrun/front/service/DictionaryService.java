package com.iwantrun.front.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.front.domain.Case;
import com.iwantrun.front.domain.Dictionary;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.transfer.PurchaserAccountRequest;
import com.iwantrun.front.utils.JSONUtils;

@Service
public class DictionaryService {
	@Autowired
	private Environment environment;
	@Autowired
	private RestTemplate template;
	
	public Message queryDictionaryList(String name) {
		String findByName = environment.getProperty("application.dictionary.findByName");
		String baseUrl = environment.getProperty("app.server");
		
		String url = baseUrl + findByName;

		Message message = new Message();
		message.setMessageBody(name);
		message.setRequestMethod(url);
		message = template.postForEntity(url, message, Message.class).getBody();
		
		return message;
	}
		/**
		 * 根据UsedField 筛选出符合条件的list
		 * @param dictoryList  要筛选的list
		 * @param usedField  
		 * @return
		 */
		public List<Dictionary> filterByUsedField(List<Dictionary> dictoryList,String usedField){
			ArrayList<String> usedFieldList = new ArrayList<String>();
			usedFieldList.add(usedField);
			List<Dictionary> list = dictoryList.stream().filter( (Dictionary dic)-> usedFieldList.contains(dic.getUsed_field())).collect(Collectors.toList());
			return list;
		}
	
}
