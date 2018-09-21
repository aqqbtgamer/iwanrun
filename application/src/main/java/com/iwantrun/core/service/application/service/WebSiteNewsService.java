package com.iwantrun.core.service.application.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.iwantrun.core.service.application.dao.JPQLEnableRepository;
import com.iwantrun.core.service.application.dao.WebSiteNewsDao;
import com.iwantrun.core.service.application.domain.WebSiteNews;

import net.minidev.json.JSONObject;

@Service
public class WebSiteNewsService {

	Logger logger = LoggerFactory.getLogger(WebSiteNewsService.class);
	
	@Autowired
	private WebSiteNewsDao newsDao ; 
	
	@Autowired  
    private Environment env;
	
	@Autowired
	private JPQLEnableRepository jpqlExecute;
	
	public WebSiteNews addWebSiteMessage(WebSiteNews news) {
		return newsDao.save(news);
	}
	
	public List<WebSiteNews> queryWebSiteNews(){
		Sort sort = new Sort(Sort.Direction.DESC,"newsDate");
		return newsDao.findAll(sort);
	}
	
	public Page<WebSiteNews> queryWebSiteNewsPageable(Pageable page){		
		return newsDao.findAll(page);
	}
	
	public void deleteNews(int newsId) {
		newsDao.deleteById(newsId);
	}

	public Page<Map<String,Object>> queryByCondition(JSONObject jsonObj) {
		String pageIndexStr = jsonObj.getAsString("pageIndex");
		Integer pageIndex = pageIndexStr == null ? 0:Integer.parseInt(pageIndexStr);
		Integer pageSize = Integer.parseInt(env.getProperty("common.pageSize"));
		List<Map<String,Object>> resultList = newsDao.queryByCondition(pageIndex, pageSize, jsonObj,jpqlExecute);
		Integer total = newsDao.countByCondition(jsonObj,jpqlExecute);
		Pageable page =  PageRequest.of(pageIndex, pageSize, Sort.Direction.ASC, "id");
		PageImpl<Map<String,Object>> result = new PageImpl<Map<String,Object>>(resultList, page, total);
		return result;
	}
}
