package com.iwantrun.core.service.application.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iwantrun.core.service.application.domain.ProductionInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = { "classpath:application.properties" })
@SpringBootTest
public class ProductionInfoServiceTest {
	@Autowired
	private ProductionInfoService service;

	@Test
	public void testFindAll() {
		if (service == null) {
			fail("test setting not valid");
		} else {
			ProductionInfo param = new ProductionInfo();
			param.setName("wlm1314");
			// param.setName("whq1314");
			param.setActivityCityCode(0);
			param.setDescirbeText1("1314 love you, my family!");
			List<ProductionInfo> infos = service.queryByCondition(param);
			System.out.println(infos);
			assertNotNull(infos);
			for (ProductionInfo info : infos) {
				System.out.println("查到了" + info);
			}
		}

	}
}
