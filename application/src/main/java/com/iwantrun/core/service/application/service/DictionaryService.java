package com.iwantrun.core.service.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.iwantrun.core.service.application.config.DictionaryPageConfig;

@Service
public class DictionaryService {
	
	Logger logger = LoggerFactory.getLogger(DictionaryService.class);
	
	//@Autowired
	//private DictionaryDao dictionaryDao ;	
	
	public String getPages() {
		return DictionaryPageConfig.getPageConfigJson();
	}
	

}
