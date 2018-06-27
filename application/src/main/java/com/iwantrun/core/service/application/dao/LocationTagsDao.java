package com.iwantrun.core.service.application.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.iwantrun.core.service.application.domain.LocationTags;
import com.iwantrun.core.service.application.domain.Locations;

public interface LocationTagsDao extends JpaRepository<LocationTags, Integer>,JpaSpecificationExecutor<LocationTags>  {
	
	default List<LocationTags> findByLocationId(int locationId) {
		LocationTags tagsExample = new LocationTags();
		tagsExample.setLocationId(locationId);
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("locationId", GenericPropertyMatchers.exact())
				.withIgnorePaths("id","tagsType","tagsCode");
		return this.findAll(Example.of(tagsExample,matcher));
	}
	default List<LocationTags> findByTagsCodes(List<Integer> tagsCode,JPQLEnableRepository repository) {
		 List<LocationTags> list = new ArrayList<LocationTags>();
		 String tagsCodeString = "";
		 for(Integer num : tagsCode) {
			 tagsCodeString += ""+ num + ",";
		 }
		 tagsCodeString = tagsCodeString.substring(0, tagsCodeString.length()-1);
		 String queryJPQL = "select t from LocationTags t where 1=1 and t.tagsCode in (" + tagsCodeString + ")";
		 list = repository.findByJPQLAll(queryJPQL, LocationTags.class);
		 return list;
	}

}
