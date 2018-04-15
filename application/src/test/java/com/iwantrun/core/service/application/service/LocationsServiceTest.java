package com.iwantrun.core.service.application.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iwantrun.core.service.application.domain.LocationAttachments;
import com.iwantrun.core.service.application.domain.Locations;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.properties"})
@SpringBootTest
public class LocationsServiceTest {
	
	@Autowired
	private LocationsService service ;

	@Test
	public void test() {
		Locations location = new Locations();
		location.setName("test1");
		location.setActiveTypeCode("1");
		location.setStatus(1);
		location.setLocation("啊哈哈哈哈");
		List<LocationAttachments> list = new ArrayList<LocationAttachments>();
		LocationAttachments attach = new LocationAttachments();
		attach.setFilePath("a/b/c");
		attach.setFileName("测试");
		attach.setPagePath("啊哈哈哈哈");
		list.add(attach);
		boolean result = service.createLocations(location, list);
		assertEquals(true, result);
	}

}
