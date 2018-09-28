package com.iwantrun.core.service.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;

import com.iwantrun.core.service.application.dao.OrderLocationRelationDao;
import com.iwantrun.core.service.application.domain.OrderLocationRelation;

public class OrderLocationRelationService {
	
	@Autowired
	private OrderLocationRelationDao orderLocationRelationDao ; 
	
	public List<OrderLocationRelation> findByCondition(OrderLocationRelation vo){
		
		ExampleMatcher matcher = ExampleMatcher.matchingAll()
				.withMatcher("order_id", GenericPropertyMatchers.exact())
				.withMatcher("location_id", GenericPropertyMatchers.exact())
				.withMatcher("location_case", GenericPropertyMatchers.exact())
				.withIgnorePaths("id","display_code","display_value","code","value","assignTo");
				;
		return orderLocationRelationDao.findAll(Example.of(vo,matcher));
	}

}
