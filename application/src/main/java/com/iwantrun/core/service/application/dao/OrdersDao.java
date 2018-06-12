package com.iwantrun.core.service.application.dao;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.Orders;
import com.iwantrun.core.service.application.enums.TradeStatus;

public interface OrdersDao extends JpaRepository<Orders, Integer> {
	
	String QUERY_ORDERS_WITH_USER_INFO_SQL =" select orders.id ,login.id as loginId, info.id as infoId ,info.name,"
			+ " login.mobile_number as mobileNumber, orders.order_no as orderNo,orders.create_time as createTime,orders.order_status_code as orderStatusCode "
			+ "from biz_orders orders left outer join  sys_login_info login on orders.order_owner_id = login.id left join sys_user_info info "
			+ "on login.id = info.login_info_id where 1=1 order by orders.id asc"  ;  
	
	String COUNT_ORDERS_WITH_USER_INFO_SQL =" select count(orders.id)"
			+ "from biz_orders orders left outer join  sys_login_info login on orders.order_owner_id = login.id left join sys_user_info info "
			+ "on login.id = info.login_info_id "  ; 
	
	default List<Map<String,Object>> findAllWithPurchaseInfoPaged(int pageIndex,int pageSize,JPQLEnableRepository repository){
		Function<Object[],Map<String,Object>> mapper =
				objArray -> {
					Map<String,Object> result = new HashMap<String,Object>();
					int index = 0 ;
					result.put("id", objArray[index] == null ? null:objArray[index].toString());
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
				@SuppressWarnings("unchecked")
				List<Object[]> rawResult = (List<Object[]>)repository.findByNativeSqlPage(QUERY_ORDERS_WITH_USER_INFO_SQL, pageIndex, pageSize);
				return rawResult.stream().map(mapper).collect(Collectors.toList());
	}
	
	default Integer countAllWithPurchaseInfo(JPQLEnableRepository repository) {
		Function<Object,Integer> mapper =
				objArray -> {
					Integer total =  0;
					if(objArray != null) {
						total = Integer.parseInt(objArray.toString());
					}
					return total;					
				};				
		@SuppressWarnings("unchecked")
		List<Object> rawResult = repository.findByNativeSqlPage(COUNT_ORDERS_WITH_USER_INFO_SQL);
		Object raw = rawResult.get(0);
		return mapper.apply(raw);
	}

}
