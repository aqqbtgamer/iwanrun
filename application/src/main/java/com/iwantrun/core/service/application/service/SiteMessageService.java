package com.iwantrun.core.service.application.service;

import com.iwantrun.core.service.application.dao.SiteMessageDao;
import com.iwantrun.core.service.application.domain.SiteMessage;
import com.iwantrun.core.service.application.transfer.SimpleMessageBody;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteMessageService {
    @Autowired
    private SiteMessageDao siteMessageDao;

    public SimpleMessageBody sendSiteMessage(String from_user, JSONObject data) {
        SimpleMessageBody body = new SimpleMessageBody();
        SiteMessage siteMessage = new SiteMessage();
        body.setSuccessful(false);

        siteMessage.setFromUser(from_user);
        siteMessage.setSendtoUser(data.getAsString("to_user"));
        siteMessage.setMessageText(data.getAsString("message"));
        siteMessage.setOrderNo(data.getAsString("order_no"));

        siteMessageDao.save(siteMessage);
        body.setSuccessful(true);
        return body;
    }

    public JSONArray getSiteMessageSendto(String userid) {
        JSONArray result = new JSONArray();

        List<SiteMessage> list = siteMessageDao.findAllBySendtoUser(userid);
        for (SiteMessage siteMessage: list) {
            JSONObject obj = new JSONObject();
            obj.put("from_user", siteMessage.getFromUser());
            obj.put("sendto_user", siteMessage.getSendtoUser());
            obj.put("message", siteMessage.getMessageText());
            obj.put("blread", siteMessage.isBlRead());
            obj.put("timestamp", siteMessage.getCreateTime().toString());
        }

        return result;
    }

    public JSONArray getSiteMessageByFrom(String userid) {
        JSONArray result = new JSONArray();

        List<SiteMessage> list = siteMessageDao.findAllByFromUser(userid);
        for (SiteMessage siteMessage: list) {
            JSONObject obj = new JSONObject();
            obj.put("from_user", siteMessage.getFromUser());
            obj.put("sendto_user", siteMessage.getSendtoUser());
            obj.put("message", siteMessage.getMessageText());
            obj.put("blread", siteMessage.isBlRead());
            obj.put("timestamp", siteMessage.getCreateTime().toString());
        }

        return result;
    }
}
