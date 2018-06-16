package com.iwantrun.core.service.application.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.iwantrun.core.service.application.dao.JPQLEnableRepository;
import com.iwantrun.core.service.application.dao.OrdersDao;
import com.iwantrun.core.service.utils.PageDataWrapUtils;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@Service
public class OrdersService {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private JPQLEnableRepository jpql;
	
	@Autowired
	private OrdersDao ordersDao ;
	
	@Autowired  
    private Environment env;
	
	public String findAll(JSONObject requestObj) {
		String pageIndexStr = requestObj.getAsString("pageIndex");
		Integer pageIndex = pageIndexStr == null ? 0:Integer.parseInt(pageIndexStr);
		Integer pageSize = Integer.parseInt(environment.getProperty("common.pageSize"));
		List<Map<String,Object>> resultList = ordersDao.findAllWithPurchaseInfoPaged(pageIndex, pageSize, jpql);
		Integer total = ordersDao.countAllWithPurchaseInfo(jpql);
		Pageable page =  PageRequest.of(pageIndex, pageSize, Sort.Direction.ASC, "id");
		PageImpl<Map<String,Object>> result = new PageImpl<Map<String,Object>>(resultList, page, total);
		return PageDataWrapUtils.page2JsonNoCopy(result);
	}
	
	public String findByExample(JSONObject requestObj) {
		String pageIndexStr = requestObj.getAsString("pageIndex");
		int pageSize = Integer.parseInt(env.getProperty("common.pageSize"));
		String objString = requestObj.getAsString("obj");
		JSONObject obj = (JSONObject) JSONValue.parse(objString);
		Integer pageIndex = pageIndexStr == null ? 0:Integer.parseInt(pageIndexStr);
		obj.put("pageIndex", pageIndex);
		obj.put("pageSize", pageSize);
		String orderStatusStr = obj.getAsString("orderStatus");
		Integer orderStatus = orderStatusStr == null ? null :Integer.parseInt(orderStatusStr);
		obj.put("orderStatus", orderStatus);
		List<Map<String,Object>> resultList = ordersDao.findByExampleWithUserInfoPaged(obj, jpql);
		Integer total = ordersDao.countByExampleWithUserInfo(obj, jpql);
		Pageable page =  PageRequest.of(pageIndex, pageSize, Sort.Direction.ASC, "id");
		PageImpl<Map<String,Object>> result = new PageImpl<Map<String,Object>>(resultList, page, total);
		return PageDataWrapUtils.page2JsonNoCopy(result);
	}

}
