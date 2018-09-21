package com.iwantrun.core.service.application.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.iwantrun.core.constant.AdminApplicationConstants;
import com.iwantrun.core.service.application.dao.WebSiteCooperativeLogoDao;
import com.iwantrun.core.service.application.domain.WebSiteCooperativeLogo;
import com.iwantrun.core.service.utils.JSONUtils;
import com.mysql.jdbc.StringUtils;

import net.minidev.json.JSONObject;

@Service
public class WebSiteCooperativeLogoService {
	
	Logger logger = LoggerFactory.getLogger(WebSiteCooperativeLogoService.class);
	
	@Autowired  
    private Environment env;
	
	@Autowired
	private WebSiteCooperativeLogoDao logoDao ;
	
	public List<WebSiteCooperativeLogo> findAll(){
		Sort sort = new Sort(Sort.Direction.ASC,"id");
		return logoDao.findAll(sort);
	}
	
	public Page<WebSiteCooperativeLogo> findAllPaged(JSONObject json){
		Integer pageSize = Integer.parseInt(env.getProperty("common.pageSize"));
		String pageIndexStr = json.getAsString("pageIndex");
		Integer pageIndex = pageIndexStr == null ? 0:Integer.parseInt(pageIndexStr);
		Pageable page = PageRequest.of(pageIndex, pageSize, Sort.Direction.ASC, "id");
		return logoDao.findAll(page);
	}	
	
	public Page<WebSiteCooperativeLogo> findByName(JSONObject json){
		Integer pageSize = Integer.parseInt(env.getProperty("common.pageSize"));
		String pageIndexStr = json.getAsString("pageIndex");
		Integer pageIndex = pageIndexStr == null ? 0:Integer.parseInt(pageIndexStr);		
		String name = json.getAsString("name");
		if(StringUtils.isNullOrEmpty(name)) {
			return findAllPaged(json);
		}else {
			Pageable page = PageRequest.of(pageIndex, pageSize, Sort.Direction.ASC, "id");
			return logoDao.findByNameLike("%"+name+"%",page);
		}
	}
	
	@Transactional
	public String add(WebSiteCooperativeLogo logo) {
		String result = null ;
		try {
			WebSiteCooperativeLogo addResult = logoDao.save(logo);
			result = JSONUtils.objToJSON(addResult);
		}catch(Exception e) {
			logger.error("error when add ...",e);
		}
		return result ;
	}
	
	@Transactional
	public String delete(JSONObject json) {
		String result = null ;		
		try {
			String idStr = json.getAsString("id");
			Integer id = Integer.parseInt(idStr);
			logoDao.deleteById(id);
			result = AdminApplicationConstants.Add_SUCCESS_RESULT;
		}catch(Exception e) {
			logger.error("error when add ...",e);
			result = AdminApplicationConstants.Add_FAIL_RESULT;
		}
		return result ;
	}
	
}
