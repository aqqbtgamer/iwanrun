package com.iwantrun.core.service.application.dao;

import java.util.Optional;

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
public class ProductionInfoDaoTest {
	@Autowired
	private ProductionInfoDao productionInfoDao;

	/**
	 * id | name | activity_type_code | during |status
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		ProductionInfo param = new ProductionInfo();
		param.setActivityCityCode("1");
		param.setActivityDistCode("211");
		param.setName("ProductionInfoDaoTest");
		param.setActivityTypeCode("2");
		param.setDuring(2);
		param.setStatus(1);
		productionInfoDao.save(param);
	}

	@Test
	public void testName() throws Exception {
		Optional<ProductionInfo> dbProductionOpt = productionInfoDao.findById(72);
		System.out.println(dbProductionOpt.get());
	}
}
