package com.iwantrun.core.service.application.dao;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.constant.AdminApplicationConstants;
import com.iwantrun.core.service.application.domain.Cases;
import com.iwantrun.core.service.application.domain.Orders;
import com.iwantrun.core.service.application.domain.SearchDictionaryList;
import com.iwantrun.core.service.application.enums.SQLConditions;
import com.iwantrun.core.service.application.enums.TradeStatus;
import com.iwantrun.core.service.utils.NativeSQLUtils;
import com.mysql.jdbc.StringUtils;

import net.minidev.json.JSONObject;
import org.springframework.data.jpa.repository.Query;

public interface OrdersDao extends JpaRepository<Orders, Integer> {
	
	Logger logger = LoggerFactory.getLogger(OrdersDao.class);
	
	String QUERY_ORDERS_WITH_USER_INFO_SQL =" select orders.id ,orders.order_adviser_id as orderAdviserId ,login.id as loginId, info.id as infoId ,info.name,"
			+ " login.mobile_number as mobileNumber, orders.order_no as orderNo,orders.create_time as createTime,orders.order_status_code as orderStatusCode "
			+ "from biz_orders orders left outer join  sys_login_info login on orders.order_owner_id = login.id left join sys_user_info info "
			+ "on login.id = info.login_info_id where 1=1 "  ;  
	
	String COUNT_ORDERS_WITH_USER_INFO_SQL =" select count(orders.id)"
			+ "from biz_orders orders left outer join  sys_login_info login on orders.order_owner_id = login.id left join sys_user_info info "
			+ "on login.id = info.login_info_id where 1=1 "  ; 
	
	String QUERY_ORDERS_WITH_USER_ASSIGNER_INFO_SQL = " select orders.id ,orders.order_adviser_id as orderAdviserId ,login.id as loginId, info.id as infoId ,info.name,"
			+ " login.mobile_number as mobileNumber, orders.order_no as orderNo,orders.create_time as createTime,orders.order_status_code as orderStatusCode "
			+ "from biz_orders orders left outer join  sys_login_info login on orders.order_owner_id = login.id left join sys_user_info info "
			+ "on login.id = info.login_info_id left join sys_login_info assign on orders.order_adviser_id = assign.id left join sys_login_info assignInfo  "
			+ "on assign.id = assignInfo.login_id where 1=1 ";
	
	String COUNT_ORDERS_WITH_USER_ASSIGNER_INFO_SQL = "select count(orders.id)  "
			+ "from biz_orders orders left outer join  sys_login_info login on orders.order_owner_id = login.id left join sys_user_info info "
			+ "on login.id = info.login_info_id left join sys_login_info assign on orders.order_adviser_id = assign.id left join sys_login_info assignInfo  "
			+ "on assign.id = assignInfo.login_id where 1=1 ";
	
	
	String ORDERBY_DESC = "order by orders.id asc";
	
	String QUERY_BY_LOGIN_ID=" and login.login_id='";
	
	Function<Object[],Map<String,Object>> MAPPER_MIXED_ORDER =
			objArray -> {
				Map<String,Object> result = new HashMap<String,Object>();
				int index = 0 ;
				result.put("id", objArray[index] == null ? null:objArray[index].toString());
				index ++ ;
				result.put("orderAdviserId", objArray[index] == null ? null:objArray[index].toString());
				index ++ ;
				result.put("loginId", objArray[index] == null ? null:objArray[index].toString());
				index ++ ;
				result.put("infoId", objArray[index] == null ? null:objArray[index].toString());
				index ++ ;
				result.put("name", objArray[index] == null ? null:objArray[index].toString());
				index ++ ;
				result.put("mobileNumber", objArray[index] == null ? null:objArray[index].toString());
				index ++ ;
				result.put("orderNo",objArray[index]== null ? null: objArray[index].toString());
				index ++ ;
				if(objArray[index] != null) {
					Timestamp createTime = (Timestamp) objArray[index];
					Calendar createTimeCal = Calendar.getInstance();
					createTimeCal.setTimeInMillis(createTime.getTime());
					SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					result.put("createTime", smf.format(createTimeCal.getTime()));
				}
				index ++ ;
				if(objArray[index] != null) {
					String orderStatusCodeStr = objArray[index].toString();
					Integer orderStatusCode = Integer.parseInt(orderStatusCodeStr);
					result.put("orderStatusCode", orderStatusCode);
					TradeStatus orderStatusEnum = TradeStatus.matchById(orderStatusCode);
					if(orderStatusEnum != null) {
						result.put("orderStatusCodeString", orderStatusEnum.getName());
					}
				}					
				return result ;
			};			
				
	
	default List<Map<String,Object>> findAllWithPurchaseInfoPaged(int pageIndex,int pageSize,JPQLEnableRepository repository){
				@SuppressWarnings("unchecked")
				List<Object[]> rawResult = (List<Object[]>)repository.findByNativeSqlPage(QUERY_ORDERS_WITH_USER_INFO_SQL, pageIndex, pageSize);
				return rawResult.stream().map(MAPPER_MIXED_ORDER).collect(Collectors.toList());
	}
	
	default Integer countAllWithPurchaseInfo(JPQLEnableRepository repository) {			
		@SuppressWarnings("unchecked")
		List<Object> rawResult = repository.findByNativeSqlPage(COUNT_ORDERS_WITH_USER_INFO_SQL);
		Object raw = rawResult.get(0);
		return AdminApplicationConstants.MAPPER_FOR_INTEGER.apply(raw);
	}
	
	default List<Map<String,Object>> findByExampleWithUserInfoPaged(JSONObject requestObj,JPQLEnableRepository repository){
		Integer pageIndex = (Integer) requestObj.get("pageIndex");
		Integer pageSize = (Integer) requestObj.get("pageSize");
		String sql = buildQuerySql(QUERY_ORDERS_WITH_USER_ASSIGNER_INFO_SQL,requestObj,ORDERBY_DESC);
		@SuppressWarnings("unchecked")
		List<Object[]> rawResult = (List<Object[]>)repository.findByNativeSqlPage(sql, pageIndex, pageSize);
		return rawResult.stream().map(MAPPER_MIXED_ORDER).collect(Collectors.toList());
	}
	
	@SuppressWarnings("unchecked")
	default Integer countByExampleWithUserInfo(JSONObject requestObj,JPQLEnableRepository repository) {
		String sql = buildQuerySql(COUNT_ORDERS_WITH_USER_ASSIGNER_INFO_SQL,requestObj,null);
		List<Object> rawResult = repository.findByNativeSqlPage(sql);
		Object raw = rawResult.get(0);
		return AdminApplicationConstants.MAPPER_FOR_INTEGER.apply(raw);
	}
	
	default String buildQuerySql(String sql,JSONObject obj,String ordeBy) {
		if(sql == null) {
			return sql ;
		}
		String orderNO =  (String) obj.get("orderNO");
		String mobile1 = (String) obj.get("mobile1");
		String mobile2 = (String) obj.get("mobile2");
		String createTimeFrom = (String) obj.get("createTimeFrom");
		String createTimeTo = (String) obj.get("createTimeTo");
		String modifyTimeFrom = (String) obj.get("modifyTimeFrom");
		String modifyTimeTo = (String) obj.get("modifyTimeTo");
		Integer orderStatus = (Integer)obj.get("orderStatus");
		sql = NativeSQLUtils.contractQueryCondition("orders.order_no", orderNO, SQLConditions.And, sql, SQLConditions.Equals);
		sql = NativeSQLUtils.contractQueryCondition("login.mobile_number", mobile1, SQLConditions.And, sql, SQLConditions.Like);
		sql = NativeSQLUtils.contractQueryCondition("assign.mobile_number", mobile2, SQLConditions.And, sql, SQLConditions.Like);
		sql = NativeSQLUtils.contractQueryCondition("orders.order_status_code", orderStatus, SQLConditions.And, sql, SQLConditions.Equals);
		if(!StringUtils.isNullOrEmpty(createTimeFrom)) {
			try {
				Date createTimeFromDate = NativeSQLUtils.format.parse(createTimeFrom);
				sql = NativeSQLUtils.contractQueryCondition("orders.create_time", createTimeFromDate, SQLConditions.And, sql, SQLConditions.Greater);
			} catch (ParseException e) {
				logger.error("date is not well formatted : ",e);
			}
		}
		if(!StringUtils.isNullOrEmpty(createTimeTo)) {
			try {
				Date createTimeToDate = NativeSQLUtils.format.parse(createTimeTo);
				sql = NativeSQLUtils.contractQueryCondition("orders.create_time", createTimeToDate, SQLConditions.And, sql, SQLConditions.Less);
			} catch (ParseException e) {
				logger.error("date is not well formatted : ",e);
			}
		}
		if(!StringUtils.isNullOrEmpty(modifyTimeFrom)) {
			try {
				Date modifyTimeFromDate = NativeSQLUtils.format.parse(modifyTimeFrom);
				sql = NativeSQLUtils.contractQueryCondition("orders.modify_time", modifyTimeFromDate, SQLConditions.And, sql, SQLConditions.Greater);
			} catch (ParseException e) {
				logger.error("date is not well formatted : ",e);
			}
		}
		if(!StringUtils.isNullOrEmpty(modifyTimeTo)) {
			try {
				Date modifyTimeToDate = NativeSQLUtils.format.parse(modifyTimeTo);
				sql = NativeSQLUtils.contractQueryCondition("orders.modify_time", modifyTimeToDate, SQLConditions.And, sql, SQLConditions.Less);
			} catch (ParseException e) {
				logger.error("date is not well formatted : ",e);
			}
		}
		if(ordeBy != null) {
			sql = sql.concat(NativeSQLUtils.BLANK).concat(ordeBy);
		}
		return sql ;
	}
	@SuppressWarnings("unchecked")
	default List<Map<String,Object>> getOrdersByLoginId(JPQLEnableRepository repository,int pageSize,int pageIndex,String loginIdSql){
		 String sql = QUERY_ORDERS_WITH_USER_INFO_SQL +loginIdSql;
		 List<Object[]> rawResult = (List<Object[]>)repository.findByNativeSqlPage(sql, pageIndex, pageSize);
			return rawResult.stream().map(MAPPER_MIXED_ORDER).collect(Collectors.toList());
	}
	@SuppressWarnings("unchecked")
	default Integer countAllWithOrdersByLoginId(JPQLEnableRepository repository,String loginIdSql) {	
		String sql = COUNT_ORDERS_WITH_USER_INFO_SQL+loginIdSql;
		List<Object> rawResult = repository.findByNativeSqlPage(sql);
		Object raw = rawResult.get(0);
		return AdminApplicationConstants.MAPPER_FOR_INTEGER.apply(raw);
	}

	default List<Map<String,Object>> getOrders(JPQLEnableRepository repository,int pageSize,int pageIndex){
		String sql = QUERY_ORDERS_WITH_USER_INFO_SQL;
		List<Object[]> rawResult = (List<Object[]>)repository.findByNativeSqlPage(sql, pageIndex, pageSize);
		return rawResult.stream().map(MAPPER_MIXED_ORDER).collect(Collectors.toList());
	}

	String QUERY_RECENT_ORDERS_WITH_USER_INFO_SQL =
			"SELECT orders.id, login.loginId, info.name, orders.createTime, orders.modifyTime, orders.orderStatusCode" +
			" FROM Orders orders" +
			" INNER JOIN PurchaserAccount login ON orders.orderOwnerId = login.id" +
			" LEFT JOIN UserInfo info ON login.id = info.loginInfoId" +
			" WHERE 1 =1" +
			" ORDER BY orders.modifyTime";
	@Query(value = QUERY_RECENT_ORDERS_WITH_USER_INFO_SQL)
	Page<Object[]> getRecentOrders(Pageable pageable);
}
