package com.iwantrun.core.service.application.dao;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iwantrun.core.service.application.domain.Locations;
import com.iwantrun.core.service.application.domain.UserAccount;
import com.iwantrun.core.service.application.transfer.MixedLocations;
import com.iwantrun.core.service.utils.JSONUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.properties"})
@SpringBootTest
public class JPQLEnableRepositoryTest {
	
	@Autowired
	private JPQLEnableRepository repository;
	
	@Test
	public void testSimpleQuery() {
		List<UserAccount> result = repository.findByJPQLPage("select account from UserAccount account order by account.id desc", UserAccount.class, 1, 10);
		for(UserAccount account : result) {
			System.out.println("----------------test result --------------"+account.getId());
		}
		assertEquals(1,result.size());
	}
	
	
	@Test
	public void testLeftJoinQuery() {
		String jpql = "select new com.iwantrun.core.service.application.transfer.MixedLocations(locations,attach) "
				+ "from Locations locations inner join LocationAttachments attach on locations.id = attach.location_id";
		List<MixedLocations> mixedList = repository.findByJPQLPage(jpql, MixedLocations.class, 1, 10);
		System.out.println("---------------------------test result --------------------"+mixedList.size());
		assertEquals(2,mixedList.size());
	}
	
	@Test
	public void testDistincLeftJoinQuery() {
		String jpql = "select distinct locations "
				+ "from Locations locations inner join LocationAttachments attach on locations.id = attach.location_id";
		List<Locations> resultList = repository.findByJPQLAll(jpql, Locations.class);
		System.out.println("---------------------------test result --------------------"+resultList.size());
		assertEquals(2,resultList.size());
	}
	
	@Test
	public void testNativeSQl() {
		String nativeSql = "select username,password from iwant_account ";
		@SuppressWarnings("unchecked")
		List<Object[]>resultList = (List<Object[]>)repository.findByNativeSqlPage(nativeSql);
		Function<Object[],Map<String,Object>> mapper = 
				objArray ->{
					Map<String,Object> resultMap = new HashMap<String,Object>();	
					resultMap.put("username", objArray[0].toString());
					resultMap.put("password", objArray[1].toString());
					return resultMap;
				}
				;
		List<Map<String,Object>> result = resultList.stream().map(mapper).collect(Collectors.toList());
		System.out.println(JSONUtils.objToJSON(result));
	}
	

}
