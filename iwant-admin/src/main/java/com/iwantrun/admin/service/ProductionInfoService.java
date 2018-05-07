package com.iwantrun.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.admin.transfer.Message;
import com.iwantrun.admin.utils.CookieUtils;
import com.iwantrun.admin.utils.FormDataUtils;

import net.minidev.json.JSONObject;

@Service
public class ProductionInfoService {
	@Autowired
	private Environment env;
	@Autowired
	private RestTemplate template;

	@Value("${application.serverbase}")
	private String baseUrl;

	public String add(HttpServletRequest request) {
		String token = CookieUtils.getLoginToken(request);
		List<String> paramList = FormDataUtils.stringArray2List(new String[] { "name", "activity_type_code", "during",
				"during_code", "location", "order_group_price_code", "order_simulate_price_code", "group_number",
				"group_number_code", "priority", "activity_province_code", "activity_city_code", "activity_dist_code",
				"mainImage", "_ue" });
		String json = FormDataUtils.formData2Json(request, paramList);
		String postUrl = env.getProperty("application.productionInfo.add");
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(json);
		message.setRequestMethod(baseUrl + postUrl);
		ResponseEntity<Message> response = template.postForEntity(baseUrl + postUrl, message, Message.class);
		return response == null ? null : response.getBody().getMessageBody();
	}

	public String find(HttpServletRequest request) {
		String activityTypeCode = request.getParameter("activityTypeCode");
		String during = request.getParameter("during");
		String groupNumber = request.getParameter("groupNumber");
		String orderSimulatePriceCode = request.getParameter("orderSimulatePriceCode");
		String orderGroupPriceCode = request.getParameter("orderGroupPriceCode");
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String sortFlag = request.getParameter("sortFlag");

		JSONObject param = new JSONObject();

		param.put("activityTypeCode", activityTypeCode);
		param.put("during", during);
		param.put("groupNumber", groupNumber);
		param.put("orderSimulatePriceCode", orderSimulatePriceCode);
		param.put("orderGroupPriceCode", orderGroupPriceCode);
		param.put("pageNum", pageNum);
		param.put("pageSize", pageSize);
		param.put("sortFlag", sortFlag);

		Message message = new Message();
		message.setMessageBody(param.toJSONString());

		System.err.println("请求=====" + param.toJSONString());

		String postUrl = env.getProperty("application.productionInfo.find");
		JSONObject obj = template.postForEntity(baseUrl + postUrl, message, JSONObject.class).getBody();

		System.err.println("响应=====" + obj.toJSONString());

		return obj.toJSONString();
	}

	public String add(Message message) {
		String postUrl = env.getProperty("application.productionInfo.add");
		message.setRequestMethod(baseUrl + postUrl);
		ResponseEntity<Message> response = template.postForEntity(baseUrl + postUrl, message, Message.class);
		return response == null ? null : response.getBody().getMessageBody();
	}

	public String detail(HttpServletRequest request) {
		String id = request.getParameter("id");
		if (!StringUtils.isEmpty(id)) {
			Message message = new Message();
			message.setMessageBody("{\"id\":" + id + "}");
			String postUrl = env.getProperty("application.productionInfo.detail");
			JSONObject obj = template.postForEntity(baseUrl + postUrl, message, JSONObject.class).getBody();
			return obj.toJSONString();
		}
		return null;
	}

	public String edit(Message message) {
		String postUrl = env.getProperty("application.productionInfo.edit");
		message.setRequestMethod(baseUrl + postUrl);
		ResponseEntity<Message> response = template.postForEntity(baseUrl + postUrl, message, Message.class);
		return response == null ? null : response.getBody().getMessageBody();
	}

	public String unShift(Message message) {
		String postUrl = env.getProperty("application.productionInfo.unShift");
		message.setRequestMethod(baseUrl + postUrl);
		ResponseEntity<Message> response = template.postForEntity(baseUrl + postUrl, message, Message.class);
		return response == null ? null : response.getBody().getMessageBody();
	}
}
