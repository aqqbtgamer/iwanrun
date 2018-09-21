package com.iwantrun.core.service.application.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.constant.AdminApplicationConstants;
import com.iwantrun.core.service.application.domain.WebSiteNews;
import com.iwantrun.core.service.application.enums.SQLConditions;
import com.iwantrun.core.service.utils.NativeSQLUtils;
import com.mysql.jdbc.StringUtils;

import net.minidev.json.JSONObject;

public interface WebSiteNewsDao extends JpaRepository<WebSiteNews, Integer> {
	Logger logger = LoggerFactory.getLogger(WebSiteNewsDao.class);
	
	String SQL_QUERY_BY_CONDITION = "select id,news_content,news_date from biz_website_news where 1=1" ;
	
	String SQL_COUNT_BY_CONDITION = "select count(id) from biz_website_news where 1=1" ;
	
	String ORDER_BY = "order by id asc" ;
	
	Function<Object[],Map<String,Object>> MAPPER_WebsiteNews = 
			objArray ->{
				Map<String,Object> result = new HashMap<String,Object>();				
				int index = 0 ;
				result.put("id", objArray[index] == null ? null:objArray[index].toString());
				index++ ;
				result.put("newsContent", objArray[index] == null ? null:objArray[index].toString());
				index++ ;
				if(objArray[index] != null) {
					Timestamp time = (Timestamp) objArray[index];
					Calendar createTime = Calendar.getInstance();
					createTime.setTimeInMillis(time.getTime());
					SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					result.put("newsDate", smf.format(createTime.getTime()));
				}
				return result;
			};

	
	default List<Map<String, Object>> queryByCondition(Integer pageIndex, Integer pageSize, JSONObject jsonObj, JPQLEnableRepository jpqlExecute){			
		String sql = buildSql(SQL_QUERY_BY_CONDITION,jsonObj);
		@SuppressWarnings("unchecked")
		List<Object[]> rawResult = jpqlExecute.findByNativeSqlPage(sql, pageIndex, pageSize) ;
		return rawResult.stream().map(MAPPER_WebsiteNews).collect(Collectors.toList());
	}
	
	
	default String buildSql(String sql ,JSONObject jsonObj) {
		String start = jsonObj.getAsString("start");
		String end = jsonObj.getAsString("end");
		String newsContent = jsonObj.getAsString("newsContent");
		if(!StringUtils.isNullOrEmpty(start)) {
			try {
				Date startDate = NativeSQLUtils.format.parse(start);
				sql = NativeSQLUtils.contractQueryCondition("news_date", startDate, SQLConditions.And, sql, SQLConditions.Greater);
			} catch (ParseException e) {
				logger.error("date is not well formatted : ",e);
			}
		}
		if(!StringUtils.isNullOrEmpty(end)) {
			try {
				Date endDate = NativeSQLUtils.format.parse(end);
				sql = NativeSQLUtils.contractQueryCondition("news_date", endDate, SQLConditions.And, sql, SQLConditions.Less);
			} catch (ParseException e) {
				logger.error("date is not well formatted : ",e);
			}
		}
		if(!StringUtils.isNullOrEmpty(newsContent)) {
			sql = NativeSQLUtils.contractQueryCondition("news_content", newsContent, SQLConditions.And, sql, SQLConditions.Like);
		}
		sql = sql + ORDER_BY ;
		return sql ;
	}

	default Integer countByCondition(JSONObject jsonObj, JPQLEnableRepository jpqlExecute) {
		String sql = buildSql(SQL_COUNT_BY_CONDITION,jsonObj);
		@SuppressWarnings("unchecked")
		List<Object> rawResult = jpqlExecute.findByNativeSqlPage(sql);
		Object raw = rawResult.get(0);
		return AdminApplicationConstants.MAPPER_FOR_INTEGER.apply(raw);
	}
	
	

}
