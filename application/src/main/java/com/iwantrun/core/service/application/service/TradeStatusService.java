package com.iwantrun.core.service.application.service;

import com.iwantrun.core.service.application.controller.SMSCodeController;
import com.iwantrun.core.service.application.dao.*;
import com.iwantrun.core.service.application.domain.Orders;
import com.iwantrun.core.service.application.domain.PurchaserAccount;
import com.iwantrun.core.service.application.domain.TradeStatus;
import com.iwantrun.core.service.application.domain.UserInfo;
import com.iwantrun.core.service.utils.DateFormatUtils;
import com.iwantrun.core.service.utils.JSONUtils;
import com.iwantrun.core.service.utils.StringFilterUtils;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.iwantrun.core.service.utils.RequestUtils.JSONGetString;

@Service
public class TradeStatusService {
    private static final Logger logger = LoggerFactory.getLogger(TradeStatusService.class);
    
    private static final String mobileReplace ="#{mobileNumber}";

    @Autowired
    //private TradeStatusDao TradeStatusDao;
    private OrdersDao ordersDao;

    @Autowired
    private JPQLEnableRepository jpql;

    @Autowired
    private UserInfoDao infoDao ;
    
    @Autowired
    private OrderHistoryDao historyDao ;
    
    @Autowired
    private PurchaserAccountDao loginDao ;

    public JSONArray getTradeStatus(JSONObject request, String userid) {
        JSONArray resultArray = new JSONArray();
        
        List<Object[]> queryedHistory = historyDao.queryOrderHistory();
        Function<Object[],JSONObject> objectToJsonFun =
        		objArray ->{
        			JSONObject jsonObject = new JSONObject();
        			jsonObject.put("id", objArray[0]);
        			jsonObject.put("orderId", objArray[1]);
        			String changeInfo = (String)objArray[2];
        			String mobileNumber = (String)objArray[5];
        			if(mobileNumber != null && changeInfo != null) {
        				mobileNumber = StringFilterUtils.maskString(3, 4, mobileNumber);
            			changeInfo = changeInfo.replace(mobileReplace, mobileNumber);
        			}
        			if(mobileNumber == null && changeInfo != null && changeInfo.contains(mobileReplace)) {
        				int orderId = (Integer)objArray[1];
        				Optional<Orders> order = ordersDao.findById(orderId);
        				if(order.isPresent()) {
        					int adviserId = order.get().getOrderAdviserId();
        					Optional<PurchaserAccount> adviser = loginDao.findById(adviserId);
        					if(adviser.isPresent()) {
        						mobileNumber = StringFilterUtils.maskString(3, 4, adviser.get().getMobileNumber());
        						changeInfo = changeInfo.replace(mobileReplace, mobileNumber);
        					}
        				}
        			}
        			jsonObject.put("mobile", mobileNumber);
        			jsonObject.put("changeInfo", changeInfo);
        			Timestamp changeTime = (Timestamp)objArray[4];
        			Date changeDate = new Date();
        			changeDate.setTime(changeTime.getTime());
        			jsonObject.put("changeTime", DateFormatUtils.formatDate(changeDate, null));
        			return jsonObject;
        		};
        		
        List<JSONObject> resultItemList = queryedHistory.stream().map(objectToJsonFun).collect(Collectors.toList());
       /* Pageable pageable = PageRequest.of(0, 20, Sort.Direction.DESC, "id" );
        Page<Object[]> pageResult = ordersDao.getRecentOrders(pageable);
        logger.info("pageResult {}", pageResult.toString());

        List<Object[]> rawResult = pageResult.getContent();
        logger.info("rawResult {}", rawResult);

        Function<Object[], JSONObject> MAPPER_MIXED_ORDER =
                objArray -> {
                    JSONObject jsonObject = new JSONObject();
                    String[] keyList = {"id", "loginId", "name", "createTime", "modifyTime", "orderStatusCode"};
                    for (int i = 0; i < keyList.length; i++) {
                        Object object = objArray[i];
                        if (keyList[i].endsWith("Time")) {
                            jsonObject.put(keyList[i], null == object ? null: object.toString());
                        } else {
                            jsonObject.put(keyList[i], object);
                        }
                    }
                    return jsonObject;
                };
        List<JSONObject> resultList = rawResult.stream().map(
                MAPPER_MIXED_ORDER
            ).collect(Collectors.toList());

        for(JSONObject jsonObject : resultList) {
            resultArray.appendElement(jsonObject);
        }*/

/*
        for(Map<String,Object> map : resultList) {
            String orderAdviserId = (String) map.get("orderAdviserId");
            if( !StringUtils.isEmpty(orderAdviserId)) {
                Integer orderAdviserIdInt = Integer.parseInt(orderAdviserId);
                if(orderAdviserId != null) {
                    List<UserInfo> adviser = infoDao.findByLoginInfoId(orderAdviserIdInt);
                    if( adviser != null && adviser.get(0)!= null) {
                        JSONObject member = new JSONObject();

                        member.put("id", map.get("orderNo"));
                        member.put("user_info", adviser.get(0).getName());
                        member.put("status", map.get("orderStatusCode"));
                        member.put("modify_time", map.get("modifyTime").toString());
                        result.add(member);
                    }
                }
            }
        }
*/    
		for (JSONObject item : resultItemList) {
			resultArray.add(item);
		}
		logger.info("resultArray {}", resultArray);
		return resultArray;
	}

    public JSONArray getTradeStatus2(JSONObject request, String userid) {
        JSONArray result = new JSONArray();
        List<Map<String,Object>> resultList = ordersDao.getOrders(jpql, 10, 1);

        for(Map<String,Object> map : resultList) {
            String orderAdviserId = (String) map.get("orderAdviserId");
            if( !StringUtils.isEmpty(orderAdviserId)) {
                Integer orderAdviserIdInt = Integer.parseInt(orderAdviserId);
                if(orderAdviserId != null) {
                    List<UserInfo> adviser = infoDao.findByLoginInfoId(orderAdviserIdInt);
                    if( adviser != null && adviser.get(0)!= null) {
                        JSONObject member = new JSONObject();

                        member.put("id", map.get("orderNo"));
                        member.put("user_info", adviser.get(0).getName());
                        member.put("status", map.get("orderStatusCode"));
                        member.put("modify_time", map.get("modifyTime").toString());
                        result.add(member);
                    }
                }
            }
        }

        return result;
    }
    /*
    public String addTradeStatus(String userInfo, int status) {
        TradeStatus tradeStatus = new TradeStatus();

        tradeStatus.setStatus(status);
        tradeStatus.setUserInfo(userInfo);

        tradeStatusDao.save(tradeStatus);
        return "success";
    }

    public String updateTradeStatus(JSONObject request, String userid) {
        int msgid = request.getAsNumber("msgid").intValue();
        String result = "success";

        Optional<TradeStatus> optional = TradeStatusDao.findById(msgid);
        if (!optional.isPresent()) {
            result = "msgid is not exist!";
        } else {
            TradeStatus TradeStatus = optional.get();
            TradeStatus.setBlRead(true);
            TradeStatusDao.save(TradeStatus);
        }

        return result;
    }

    public String addTradeStatus(JSONObject request, String fromUserid) {
        String status = JSONGetString(request, "status");
        String userInfo = JSONGetString(request, "user_info");
        String result = "success";

        result = addTradeStatus(userInfo, status);
        return result;
    }
    public String deleteTradeStatus(JSONObject request, String fromUserid) {
        String message = JSONGetString(request, "message");
        String orderNo = JSONGetString(request, "order_no");
        String toUserId = JSONGetString(request, "user_id");
        String result = "success";

        result = addTradeStatus(fromUserid, toUserId, message, orderNo);
        return result;
    }
    */
}