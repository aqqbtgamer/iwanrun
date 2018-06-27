package com.iwantrun.core.service.application.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.CaseTags;
import com.iwantrun.core.service.application.domain.LocationTags;
import com.iwantrun.core.service.application.domain.ProductionTags;

public interface ProductionTagsDao extends JpaRepository<ProductionTags, Integer> {
	
	default List<ProductionTags> findByProductionId(int productionId) {
		ProductionTags tagsExample = new ProductionTags();
		tagsExample.setProductionId(productionId);
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("productionId", GenericPropertyMatchers.exact())
				.withIgnorePaths("id","tagsType","tagsCode");
		return this.findAll(Example.of(tagsExample,matcher));
	}
	default List<ProductionTags> findByTagsCodes(List<Integer> tagsCode,JPQLEnableRepository repository) {
		 List<ProductionTags> list = new ArrayList<ProductionTags>();
		 String tagsCodeString = "";
		 for(Integer num : tagsCode) {
			 tagsCodeString += ""+ num + ",";
		 }
		 tagsCodeString = tagsCodeString.substring(0, tagsCodeString.length()-1);
		 String queryJPQL = "select p from ProductionInfo p where 1=1 and p.tagsCode in (" + tagsCodeString + ")";
		 list = repository.findByJPQLAll(queryJPQL, ProductionTags.class);
		 return list;
	}

}
