package com.iwantrun.core.service.application.dao;

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

}
