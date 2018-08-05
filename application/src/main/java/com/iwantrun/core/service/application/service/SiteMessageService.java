package com.iwantrun.core.service.application.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.iwantrun.core.service.application.dao.PurchaserAccountDao;
import com.iwantrun.core.service.application.dao.SiteMessageDao;
import com.iwantrun.core.service.application.dao.UserAccountDao;
import com.iwantrun.core.service.application.domain.PurchaserAccount;
import com.iwantrun.core.service.application.domain.SiteMessage;
import com.iwantrun.core.service.application.transfer.SimpleMessageBody;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.iwantrun.core.constant.AdminApplicationConstants.SITE_ADMIN_USER;
import static com.iwantrun.core.service.utils.RequestUtils.JSONGetString;

@Service
public class SiteMessageService {
    @Autowired
    private SiteMessageDao siteMessageDao;

    //@Autowired
    //private UserAccountDao userAccountDao;

    @Autowired
    private PurchaserAccountDao purchaserAccountDao;

    public String addSiteMessage(String fromUserId, String toUserId, String message, String orderNo) {
        SiteMessage siteMessage = new SiteMessage();
        if (null == fromUserId || fromUserId.isEmpty()) {
            fromUserId = "system";
        }

        PurchaserAccount account = purchaserAccountDao.findByLoginId(toUserId);
        if (null == account || !toUserId.equals(account.getLoginId())) {
            return "send to user_id not found!";
        }

        siteMessage.setFromUser(fromUserId);
        siteMessage.setSendtoUser(toUserId);
        siteMessage.setMessageText(message);
        siteMessage.setOrderNo(null == orderNo ? orderNo: "");
        siteMessage.setCreateTime(new Date());
        siteMessage.setBlRead(false);

        siteMessageDao.save(siteMessage);
        return "success";
    }

    public String addSiteMessage(String toUserId, String message) {
        return addSiteMessage("", toUserId, message, "");
    }

    public JSONArray getSiteMessage(JSONObject request, String userid) {
        JSONArray result = new JSONArray();
        boolean blAll = JSONGetString(request,"type").equals("all");

        List<SiteMessage> list = siteMessageDao.findAllBySendtoUser(userid);
        for (SiteMessage siteMessage: list) {
            if (!blAll && siteMessage.isBlRead()) {
                continue;
            }
            JSONObject obj = new JSONObject();
            obj.put("msgid", siteMessage.getId());
            obj.put("message", siteMessage.getMessageText());
            obj.put("blread", siteMessage.isBlRead());
            obj.put("timestamp", siteMessage.getCreateTime().toString());
            obj.put("order_no", siteMessage.getOrderNo());
            result.appendElement(obj);
        }

        return result;
    }
    public String updateSiteMessage(JSONObject request, String userid) {
        int msgid = request.getAsNumber("msgid").intValue();
        String result = "success";

        Optional<SiteMessage> optional = siteMessageDao.findById(msgid);
        if (!optional.isPresent()) {
            result = "msgid is not exist!";
        } else {
            SiteMessage siteMessage = optional.get();
            siteMessage.setBlRead(true);
            siteMessageDao.save(siteMessage);
        }

        return result;
    }

    public String addSiteMessage(JSONObject request, String fromUserid) {
        String message = JSONGetString(request, "message");
        String orderNo = JSONGetString(request, "order_no");
        String toUserId = JSONGetString(request, "user_id");
        String result = "success";

        result = addSiteMessage(fromUserid, toUserId, message, orderNo);
        return result;
    }
/*
    private boolean isPurcherUser(String from_user) {
        PurchaserAccount user = purchaserAccountDao.findByLoginId(from_user);
        if (null != user && from_user.equals(user.getLoginId())) {
            return true;
        }
        return false;
    }

    public SimpleMessageBody sendSiteMessage(String from_user, JSONObject data) {

        String to_user = data.getAsString("to_user");
        if (isPurcherUser(from_user)) {
            to_user = SITE_ADMIN_USER;
        }

        SimpleMessageBody body = new SimpleMessageBody();
        SiteMessage siteMessage = new SiteMessage();
        body.setSuccessful(false);

        //siteMessage.setFromUser(from_user);
        //siteMessage.setSendtoUser(to_user);
        siteMessage.setMessageText(data.getAsString("message"));
        siteMessage.setOrderNo(data.getAsString("order_no"));
        siteMessage.setCreateTime(new Date());

        siteMessageDao.save(siteMessage);
        body.setSuccessful(true);
        return body;
    }
*/
/*
    public JSONArray getSiteMessageByFrom(String userid) {
        JSONArray result = new JSONArray();

        List<SiteMessage> list = siteMessageDao.findAllByFromUser(userid);
        for (SiteMessage siteMessage: list) {
            JSONObject obj = new JSONObject();
            obj.put("id", siteMessage.getId());
            obj.put("from_user", siteMessage.getFromUser());
            obj.put("sendto_user", siteMessage.getSendtoUser());
            obj.put("message", siteMessage.getMessageText());
            obj.put("blread", siteMessage.isBlRead());
            obj.put("timestamp", siteMessage.getCreateTime().toString());
            result.appendElement(obj);
        }

        return result;
    }
*/
}
