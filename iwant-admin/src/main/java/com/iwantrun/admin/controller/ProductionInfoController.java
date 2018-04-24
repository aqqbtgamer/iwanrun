package com.iwantrun.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.admin.service.ProductionInfoService;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@RestController
public class ProductionInfoController {
	@Autowired
	private ProductionInfoService service;
	
	public void setService(ProductionInfoService service) {
		this.service = service;
	}

	/**
	 * 按照指定的字段筛选、查找产品，如活动类型、天数、人数、参考价格等 
	 * 按照指定的字段对产品列表进行排序，如访问热度、上架时间、参考价格等
	 */
	@RequestMapping("/productionInfo/find")
	@ResponseBody
	public String find(HttpServletRequest request) {
		return service.find(request);
	}
	
	@RequestMapping("/productionInfo/add")
	@ResponseBody
	public String add(@RequestBody String json, HttpServletRequest request) {
		String mainImage = request.getParameter("mainImage");
		return service.add(json);
	}
}
