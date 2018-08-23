package com.iwantrun.core.service.application.service;

import com.iwantrun.core.service.application.dao.*;
import com.iwantrun.core.service.application.domain.PurchaserAccount;
import com.iwantrun.core.service.application.domain.TradeStatus;
import com.iwantrun.core.service.application.domain.UserInfo;
import com.iwantrun.core.service.utils.JSONUtils;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.iwantrun.core.service.utils.RequestUtils.JSONGetString;

@Service
public class TradeStatusService {
    @Autowired
    //private TradeStatusDao TradeStatusDao;
    private OrdersDao ordersDao;

    @Autowired
    private JPQLEnableRepository jpql;

    @Autowired
    private PurchaserAccountDao purchaserAccountDao;

    @Autowired
    private UserInfoDao infoDao ;
    public JSONArray getTradeStatus(JSONObject request, String userid) {
        JSONArray result = new JSONArray();
        List<Map<String,Object>> resultList = ordersDao.getOrders(jpql, 10, 0);

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