package com.iwantrun.admin.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
@WebAppConfiguration
public class ProductionInfoControllerTest {
	private MockMvc mvc;
	@Before
	public void before() {
		mvc=MockMvcBuilders.standaloneSetup(new ProductionInfoController()).build();
	}
	@Test
	public void test() {
		try {
			mvc.perform(MockMvcRequestBuilders.get("/productionInfo/find").accept(MediaType.APPLICATION_FORM_URLENCODED).param("activityTypeCode", "0"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(MockMvcResultHandlers.print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
