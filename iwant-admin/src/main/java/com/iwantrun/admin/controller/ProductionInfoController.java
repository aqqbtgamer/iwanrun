package com.iwantrun.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.admin.domain.ProductionInfo;
import com.iwantrun.admin.service.ProductionInfoService;
import com.iwantrun.admin.transfer.Message;
import com.iwantrun.admin.utils.CookieUtils;
import com.iwantrun.admin.utils.JSONUtils;

@RestController
public class ProductionInfoController {
	@Autowired
	private ProductionInfoService service;
	
	/**
	 * 按照指定的字段筛选、查找产品，如活动类型、天数、人数、参考价格等 
	 * 按照指定的字段对产品列表进行排序，如访问热度、上架时间、参考价格等
	 */
	@RequestMapping("/productionInfo/find")
	@ResponseBody
	public String find(HttpServletRequest request) {
		return service.find(request);
	}
	

	/**
	 * 无条件按照分页查询所有结果
	 */
	@RequestMapping("/productionInfo/findAll")
	@ResponseBody
	public String findAll(HttpServletRequest request) {
		return service.findAll(request);
	}
	
	/**
	 * 按照name分页查询所有结果
	 */
	@RequestMapping("/productionInfo/queryAll")
	@ResponseBody
	public String queryAll(HttpServletRequest request) {
		return service.queryAll(request);
	}
	
	/**
	 * 根据ID查找产品信息
	 */
	@RequestMapping("/productionInfo/detail")
	@ResponseBody
	public String detail(HttpServletRequest request) {
		return service.detail(request);
	}
	
	@RequestMapping("/productionInfo/add")
	@ResponseBody
	public String add(@RequestBody Message message, HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		message.setAccessToken(token);
		return service.add(message);
	}
	
	@RequestMapping("/productionInfo/edit")
	@ResponseBody
	public String edit(@RequestBody Message message, HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		message.setAccessToken(token);
		return service.edit(message);
	}
	
	@RequestMapping("/productionInfo/unShift")
	@ResponseBody
	public String unShift(@RequestBody ProductionInfo info, HttpServletRequest request) {
		Message message = new Message();
		String token = CookieUtils.getLoginToken(request);
		message.setAccessToken(token);
		message.setMessageBody(JSONUtils.objToJSON(info));
		return service.unShift(message);
	}
}
