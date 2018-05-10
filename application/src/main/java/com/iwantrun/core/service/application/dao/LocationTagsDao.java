package com.iwantrun.core.service.application.dao;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.LocationTags;

public interface LocationTagsDao extends JpaRepository<LocationTags, Integer> {
	
	default List<LocationTags> findByLocationId(int locationId) {
		LocationTags tagsExample = new LocationTags();
		tagsExample.setLocationId(locationId);
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("locationId", GenericPropertyMatchers.exact())
				.withIgnorePaths("id","tagsType","tagsCode");
		return this.findAll(Example.of(tagsExample,matcher));
	}

}
