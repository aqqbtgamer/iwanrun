package com.iwantrun.core.service.application.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iwantrun.core.constant.AdminApplicationConstants;
import com.iwantrun.core.service.application.dao.CastPositionDao;
import com.iwantrun.core.service.application.domain.CastPosition;
import com.iwantrun.core.service.utils.JSONUtils;

@Service
public class CastPositionService {
	
	Logger logger = LoggerFactory.getLogger(CastPositionService.class);
	
	@Autowired
	private CastPositionDao castDao ;
	
	@Transactional
	public String addAllCastPostion(List<CastPosition> addList) {
		Map<String,String> resultMap = new HashMap<String,String>();
		try {
			castDao.saveAll(addList);		
		}catch(Exception e) {
			resultMap.put("result", AdminApplicationConstants.Add_FAIL_RESULT);
			resultMap.put("message", e.getMessage());
			logger.error("add All error:",e);
		}
		resultMap.put("result", AdminApplicationConstants.Add_SUCCESS_RESULT);
		resultMap.put("size", String.valueOf(addList.size()));
		return JSONUtils.objToJSON(resultMap);
	}
	
	public String getAllCastPostion() {
		Map<String,String> resultMap = new HashMap<String,String>();
		try {
			List<CastPosition> resultList = castDao.findAll();
			resultMap.put("result", AdminApplicationConstants.Add_SUCCESS_RESULT);
			resultMap.put("list", JSONUtils.objToJSON(resultList));
		}catch(Exception e) {
			resultMap.put("result", AdminApplicationConstants.Add_FAIL_RESULT);
			resultMap.put("message", e.getMessage());
			logger.error("get All error:",e);
		}
		return JSONUtils.objToJSON(resultMap);
	}
	
	@Transactional
	public String deleteAllCastPosition() {
		Map<String,String> resultMap = new HashMap<String,String>();
		try {
			castDao.deleteAll();
			resultMap.put("result", AdminApplicationConstants.Add_SUCCESS_RESULT);			
		}catch(Exception e) {
			resultMap.put("result", AdminApplicationConstants.Add_FAIL_RESULT);
			resultMap.put("message", e.getMessage());
			logger.error("delete All error:",e);
		}
		return JSONUtils.objToJSON(resultMap);
	}

}
