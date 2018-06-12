package com.iwantrun.core.service.utils;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class OrderNoGeneratorTest {

	@Test
	public void testGenerateOrderNoConflict() {
		List<String> orderList = new ArrayList<String>();
		for(int i =0 ; i< 100000 ;i++) {
			orderList.add(OrderNoGenerator.generateOrderNo());
		}
		List<String> result = orderList.stream().distinct().collect(Collectors.toList());
		assertEquals(orderList.size() ,result.size());
	}
	
	@Test
	public void testGenerateOrderNo() {
		String result = OrderNoGenerator.generateOrderNo();
		System.out.println(result);
	}

}
