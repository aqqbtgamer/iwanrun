package com.iwantrun.core.service.application.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iwantrun.core.service.application.config.DictionaryPageConfig;
import com.iwantrun.core.service.application.dao.DictionaryDao;
import com.iwantrun.core.service.application.domain.Dictionary;

@Service
public class DictionaryService {
	
	Logger logger = LoggerFactory.getLogger(DictionaryService.class);
	
	@Autowired
	private DictionaryDao dictionaryDao ;	
	
	public String getPages() {
		return DictionaryPageConfig.getPageConfigJson();
	}

	public String getTabs(String name) {
		return DictionaryPageConfig.getPageTages(name);
	}

	public List<Dictionary> findByCondition(String filedId,String name) {
		Dictionary example = new Dictionary();
		example.setUsed_field(filedId);
		example.setName(name);
		ExampleMatcher matcher = ExampleMatcher.matchingAll()
				.withMatcher("used_field", GenericPropertyMatchers.exact())
				.withMatcher("name", GenericPropertyMatchers.exact())
				.withIgnorePaths("id","display_code","display_value","code","value");
				;
		return dictionaryDao.findAll(Example.of(example,matcher));
	}
	
	public Dictionary findByCondition(String filedId,String name,int code) {
		Dictionary example = new Dictionary();
		example.setUsed_field(filedId);
		example.setName(name);
		example.setCode(code);
		ExampleMatcher matcher = ExampleMatcher.matchingAll()
				.withMatcher("used_field", GenericPropertyMatchers.exact())
				.withMatcher("name", GenericPropertyMatchers.exact())
				.withMatcher("code", GenericPropertyMatchers.exact())
				.withIgnorePaths("id","display_code","display_value","value");
		 Optional<Dictionary> op = dictionaryDao.findOne(Example.of(example,matcher));
		 if(op.isPresent()) {
			 return op.get();
		 }else {
			 return null;
		 }
				
	}

	@Transactional
	public Dictionary add(Dictionary obj) {
		Dictionary dbRecord = this.findByCondition(obj.getUsed_field(), obj.getName(), obj.getCode());
		if(dbRecord != null) {
			dbRecord.setValue(obj.getValue());
			return dictionaryDao.saveAndFlush(dbRecord);
		}else {
			return dictionaryDao.saveAndFlush(obj);
		}		
	}
	
	@Transactional
	public void delete(int id) {
		dictionaryDao.deleteById(id);
	}
	

}
