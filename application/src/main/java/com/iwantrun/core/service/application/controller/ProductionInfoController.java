package com.iwantrun.core.service.application.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.core.service.application.annotation.NeedTokenVerify;
import com.iwantrun.core.service.application.domain.ProductionInfo;
import com.iwantrun.core.service.application.service.ProductionInfoService;
import com.iwantrun.core.service.application.transfer.Message;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@RestController
public class ProductionInfoController {
	Logger logger = LoggerFactory.getLogger(LocationsController.class);
	
	@Autowired
	ProductionInfoService productionInfoService;

	public void setProductionInfoService(ProductionInfoService productionInfoService) {
		this.productionInfoService = productionInfoService;
	}
	
	/**
	 * 按照指定的字段筛选、查找产品，如活动类型、天数、人数、参考价格等
	 */
	@RequestMapping("/application/productionInfo/find")
	@NeedTokenVerify
	public Message findByParam(@RequestBody Message message) {
		JSONObject body = (JSONObject) JSONValue.parse(message.getMessageBody());
		
		ProductionInfo param = new ProductionInfo();
		
		param.setActivityTypeCode(body.getAsNumber("activityTypeCode").intValue());
		param.setDuring(body.getAsNumber("during").intValue());
		param.setGroupNumber(body.getAsNumber("groupNumber").intValue());
		param.setOrderSimulatePriceCode(body.getAsNumber("orderSimulatePriceCode").intValue());
		param.setOrderGroupPriceCode(body.getAsNumber("orderGroupPriceCode").intValue());
		
		List<ProductionInfo> infos =productionInfoService.findAllByParam(param);
		
		return null;
	}
}
