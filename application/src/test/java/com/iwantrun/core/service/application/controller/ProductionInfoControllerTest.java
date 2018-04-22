package com.iwantrun.core.service.application.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.iwantrun.core.service.application.domain.ProductionInfo;
import com.iwantrun.core.service.application.transfer.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
//是一级注释，用于声明一个ApplicationContext集成测试加载WebApplicationContext。作用是模拟ServletContext
@WebAppConfiguration("wac")
public class ProductionInfoControllerTest {
	private MockMvc mockMvc;
	@org.junit.Before
	public void setUp() {
		mockMvc=MockMvcBuilders.standaloneSetup(new ProductionInfoController()).build();
	}
	@Test
	public void test() throws Exception {
		ObjectMapper mapper=new ObjectMapper();
		ObjectWriter writer=mapper.writer().withDefaultPrettyPrinter();
		
		ProductionInfo param = new ProductionInfo();
		param.setActivityTypeCode(0);
		
		Message message=new Message();
		message.setAccessToken("accessToken");
		message.setMessageBody(writer.writeValueAsString(param));
		message.setRequestMethod("requestMethod");
		
		String paramsJson = writer.writeValueAsString(message);
		//perform：执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理
		//get:声明发送一个get请求的方法。
			//MockHttpServletRequestBuilder get(String urlTemplate, Object... urlVariables)：根据uri模板和uri变量值得到一个GET请求方式的。
				//另外提供了其他的请求的方法，如：post、put、delete等。
			//param：添加request的参数，如上面发送请求的时候带上了了pcode = root的参数。
			//假如使用需要发送json数据格式的时将不能使用这种方式，要使用post，可见后面被@ResponseBody注解参数的解决方法，如下
		//andExpect：添加ResultMatcher验证规则，验证控制器执行完成后结果是否正确（对返回的数据进行的判断）
		//andDo：添加ResultHandler结果处理器，比如调试时打印结果到控制台（对返回的数据进行的判断）；
		//andReturn：最后返回相应的MvcResult；然后进行自定义验证/进行下一步的异步处理（对返回的数据进行的判断）
		
		mockMvc.perform(
					MockMvcRequestBuilders.post("/application/productionInfo/find")
						.contentType(MediaType.APPLICATION_JSON)
						.content(paramsJson)
						.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
}
