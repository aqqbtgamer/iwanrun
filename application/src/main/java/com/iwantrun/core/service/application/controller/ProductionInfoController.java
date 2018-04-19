package com.iwantrun.core.service.application.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
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
	 * 按照指定的字段筛选、查找产品，如活动类型、天数、人数、参考价格等 按照指定的字段对产品列表进行排序，如访问热度、上架时间、参考价格等
	 */
	@RequestMapping("/application/productionInfo/find")
	@NeedTokenVerify
	public List<ProductionInfo> findByParam(@RequestBody Message message) {

		JSONObject body = (JSONObject) JSONValue.parse(message.getMessageBody());

		Number activityTypeCode = body.getAsNumber("activityTypeCode");
		Number during = body.getAsNumber("during");
		Number groupNumber = body.getAsNumber("groupNumber");
		Number orderSimulatePriceCode = body.getAsNumber("orderSimulatePriceCode");
		Number orderGroupPriceCode = body.getAsNumber("orderGroupPriceCode");
		Number pageNum = body.getAsNumber("pageNum");
		Number pageSize = body.getAsNumber("pageSize");
		String sortFlag = body.getAsString("sortFlag");

		ProductionInfo param = new ProductionInfo();

		if (null != activityTypeCode) {
			param.setActivityTypeCode(activityTypeCode.intValue());
		}
		if (null != during) {
			param.setDuring(during.intValue());
		}
		if (null != groupNumber) {
			param.setGroupNumber(groupNumber.intValue());
		}
		if (null != orderSimulatePriceCode) {
			param.setOrderSimulatePriceCode(orderSimulatePriceCode.intValue());
		}
		if (null != orderGroupPriceCode) {
			param.setOrderGroupPriceCode(orderGroupPriceCode.intValue());
		}
		if (pageNum == null) {
			pageNum = 0;
		}
		if (pageSize == null) {
			pageSize = 10;
		}

		Pageable page;
		if (StringUtils.isEmpty(sortFlag)) {
			page = PageRequest.of(pageNum.intValue(), pageSize.intValue());
		} else {
			page = PageRequest.of(pageNum.intValue(), pageSize.intValue(), Direction.ASC, sortFlag);
		}

		return productionInfoService.findAllByParam(param, page);
	}
}
