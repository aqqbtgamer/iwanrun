package com.iwantrun.core.service.application.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public Message findByParam(@RequestBody Message message) {
		JSONObject body = (JSONObject) JSONValue.parse(message.getMessageBody());

		ProductionInfo param = new ProductionInfo();

		param.setActivityTypeCode(body.getAsNumber("activityTypeCode").intValue());
		param.setDuring(body.getAsNumber("during").intValue());
		param.setGroupNumber(body.getAsNumber("groupNumber").intValue());
		param.setOrderSimulatePriceCode(body.getAsNumber("orderSimulatePriceCode").intValue());
		param.setOrderGroupPriceCode(body.getAsNumber("orderGroupPriceCode").intValue());

		int pageNum = body.getAsNumber("pageNum").intValue();
		int pageSize = body.getAsNumber("pageSize").intValue();

		Pageable page;
		String sortFlag = body.getAsString("sortFlag");
		if (StringUtils.isEmpty(sortFlag)) {
			page = PageRequest.of(pageNum, pageSize);
		} else {
			// Sort.by(Direction.ASC, sortFlag
			// The type com.querydsl.core.types.OrderSpecifier cannot be resolved. It is indirectly referenced from required .class files
			// Pageable page = new QPageRequest(pageNum, pageSize, QSort.by(Direction.ASC, sortFlag)); 如果这样写就会报上面的编译错误，最后决定如下
			page = PageRequest.of(pageNum, pageSize, Direction.ASC, sortFlag);
		}
		new Sort(Direction.ASC, sortFlag);

		List<ProductionInfo> infos = productionInfoService.findAllByParam(param, page);

		return null;
	}
}
