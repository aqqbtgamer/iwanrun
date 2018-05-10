package com.iwantrun.core.service.application.dao;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.LocationAttachments;

public interface LocationAttachmentsDao extends JpaRepository<LocationAttachments, Integer> {

	default List<LocationAttachments> findByLocationId(int locationId){
		LocationAttachments attachExample = new LocationAttachments();
		attachExample.setLocation_id(locationId);
		ExampleMatcher matcher1 = ExampleMatcher.matching()
				.withMatcher("location_id", GenericPropertyMatchers.exact())
				.withIgnorePaths("id","fileName","pagePath","fileSnapShot","filePath");			
		List<LocationAttachments> listAttch = this.findAll(Example.of(attachExample,matcher1));
		return listAttch;
	}
}
