package com.iwantrun.core.service.application.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iwantrun.core.service.application.domain.Orders;
import com.iwantrun.core.service.application.enums.TradeStatus;
import com.iwantrun.core.service.utils.JSONUtils;
import com.iwantrun.core.service.utils.OrderNoGenerator;

import net.minidev.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.properties"})
@SpringBootTest
public class OrdersDaoTest {
	
	@Autowired
	private OrdersDao ordersDao ;
	
	@Autowired
	private JPQLEnableRepository repository;

	@Test
	public void test1() {
		Orders orders = new Orders();
		orders.setOrderNo(OrderNoGenerator.generateOrderNo());
		orders.setCreateTime(new Date());
		orders.setOrderStatusCode(TradeStatus.OPENED.getId());
		orders.setOrderOwnerId(16);
		ordersDao.save(orders);
	}
	
	@Test
	public void test2() {
		List<Map<String,Object>> result = ordersDao.findAllWithPurchaseInfoPaged(0, 10, repository);
		System.out.println(JSONUtils.objToJSON(result));
	}
	
	@Test
	public void test3() {
		Integer total = ordersDao.countAllWithPurchaseInfo(repository);
		System.out.println(total);
	}
	
	@Test
	public void test4() {
		JSONObject obj = new JSONObject();
		obj.put("orderNO", "2018061039115261700028343495");
		Integer total = ordersDao.countByExampleWithUserInfo(obj, repository);
		System.out.println(total);
	}
	
	@Test
	public void test5() throws ParseException {
		JSONObject obj = new JSONObject();
		obj.put("orderNO", "2018061039115261700028343494");
		obj.put("pageIndex", 0);
		obj.put("pageSize", 10);
		String modifyTimeFrom = "2018-06-13 12:00:00";
		String modifyTimeTo = "2018-06-15 12:00:00";
		obj.put("modifyTimeFrom", modifyTimeFrom);
		obj.put("modifyTimeTo", modifyTimeTo);
		List<Map<String, Object>> resultList = ordersDao.findByExampleWithUserInfoPaged(obj, repository);
		System.out.println(JSONUtils.objToJSON(resultList));
	}

}
