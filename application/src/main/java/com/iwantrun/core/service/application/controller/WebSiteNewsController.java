package com.iwantrun.core.service.application.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.core.constant.AdminApplicationConstants;
import com.iwantrun.core.service.application.annotation.NeedTokenVerify;
import com.iwantrun.core.service.application.domain.WebSiteNews;
import com.iwantrun.core.service.application.service.WebSiteNewsService;
import com.iwantrun.core.service.application.transfer.Message;
import com.iwantrun.core.service.utils.JSONUtils;
import com.iwantrun.core.service.utils.PageDataWrapUtils;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@RestController
@RequestMapping("/application/webSiteNews")
public class WebSiteNewsController {
	
	Logger logger = LoggerFactory.getLogger(WebSiteNewsController.class);
	
	@Value("${common.pageSize}")
	private int commonPageSize;
	
	@Autowired
	private WebSiteNewsService newService ;
	
	@RequestMapping("findAll")
	public Message findAll(@RequestBody Message message) {
		List<WebSiteNews> allNews = newService.queryWebSiteNews();
		JSONArray newsArray = new JSONArray();
		for(WebSiteNews news: allNews) {
			newsArray.add(PageDataWrapUtils.simpleBeanCopy(news));
		}		
		message.setMessageBody(newsArray.toJSONString());
		return message;
	}
	
	@RequestMapping("findAllPaged")	
	public Message findAllPaged(@RequestBody Message message) {
		String dataJson = message.getMessageBody();
		JSONObject object = (JSONObject) JSONValue.parse(dataJson);
		String pageIndex = object.getAsString("pageIndex");
		int pageInt = Integer.parseInt(pageIndex);
		Pageable page =  PageRequest.of(pageInt, commonPageSize, Sort.Direction.ASC, "id") ;
		Page<WebSiteNews> pageResult = newService.queryWebSiteNewsPageable(page);
		message.setMessageBody(PageDataWrapUtils.page2Json(pageResult));
		return message;
	}
	
	@RequestMapping("addNews")
	@NeedTokenVerify
	public Message addNews(@RequestBody Message message) {		
		String dataJson = message.getMessageBody();		
		WebSiteNews webSiteNews = JSONUtils.jsonToObj(dataJson, WebSiteNews.class);
		webSiteNews.setNewsDate(new Date());
		WebSiteNews result = newService.addWebSiteMessage(webSiteNews);
		message.setMessageBody(JSONUtils.objToJSON(result));
		return message;
	}
	
	@RequestMapping("deleteNews")
	@NeedTokenVerify
	public Message deleteNews(@RequestBody Message message) {
		try {
			String dataJson = message.getMessageBody();		
		    JSONObject  jsonObj =  (JSONObject) JSONValue.parse(dataJson);
		    String idStr  = jsonObj.getAsString("id");
		    int id = Integer.parseInt(idStr);
		    newService.deleteNews(id);
		    message.setMessageBody(AdminApplicationConstants.Add_SUCCESS_RESULT);
		}catch(Exception e) {
			logger.error("删除失败",e);
			 message.setMessageBody(AdminApplicationConstants.Add_FAIL_RESULT);
		}
		
		return message;
	}
	
	
	@RequestMapping("queryByCondition")	
	public Message queryByCondition(@RequestBody Message message) {
		try {
			String dataJson = message.getMessageBody();		
		    JSONObject  jsonObj =  (JSONObject) JSONValue.parse(dataJson);
		    JSONObject obj = (JSONObject) jsonObj.get("obj");
		    String pageIndexStr = jsonObj.getAsString("pageIndex");
		    obj.put("pageIndex", pageIndexStr);
		    Page<Map<String,Object>> pageResult = newService.queryByCondition(obj);
		    message.setMessageBody(PageDataWrapUtils.page2JsonNoCopy(pageResult));
		}catch(Exception e) {
			logger.error("删除失败",e);
			 message.setMessageBody(AdminApplicationConstants.Add_FAIL_RESULT);
		}		
		return message;
	}

}
