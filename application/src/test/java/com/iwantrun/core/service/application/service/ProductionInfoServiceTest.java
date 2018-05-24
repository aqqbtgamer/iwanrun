package com.iwantrun.core.service.application.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

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
			//param.setName("wlm1314");
			param.setName("whq1314");
			param.setActivityCityCode(0);
//			param.setDescirbeText1("1314 love you, my family!");
//			Sort sort= new Sort(Direction.DESC, "priority");
			/*List<ProductionInfo> infos = service.findAllByParam(param);
			System.out.println(infos);
			assertNotNull(infos);
			for (ProductionInfo info : infos) {
				System.out.println("查到了" + info);
			}*/
		}

	}
	@Test
	public void save() {
		ProductionInfo param = new ProductionInfo();
		param.setName("whq1314");
		param.setActivityCityCode(0);
		param.setActivityTypeCode(0);
		param.setDuring(6);
		param.setStatus(0);
		param.setDescirbeText1("1314 love you, my family!");
		param = service.save(param);
		assertNotNull(param);
	}
	@Test
	public void edit() {
		ProductionInfo param = service.findById(1);
		param.setName("____wlm1314");
		service.edit(param);
	}
	@Test
	public void unShift() {
		ProductionInfo param = new ProductionInfo();
		param.setId(1);
		int num=service.unShift(param);
		System.out.println(num);
	}
}
