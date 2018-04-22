package com.iwantrun.admin.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.admin.transfer.Message;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Service
public class ProductionInfoService {
	@Autowired
	private RestTemplate template;

	public void setTemplate(RestTemplate template) {
		this.template = template;
	}

	public String add(HttpServletRequest request) {
		return null;
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
		
		System.err.println("请求====="+param.toJSONString());
		
		/*JSONArray arr = template.postForEntity("http://127.0.0.1:9999/iwant_app/application/productionInfo/find",
				message, JSONArray.class).getBody();

		System.err.println("响应====="+arr.toJSONString());*/
		
		JSONObject obj = template.postForEntity("http://127.0.0.1:9999/iwant_app/application/productionInfo/find",
				message, JSONObject.class).getBody();

		System.err.println("响应====="+obj.toJSONString());
		
		return obj.toJSONString();
	}
}
