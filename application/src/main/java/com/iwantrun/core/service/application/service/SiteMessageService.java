package com.iwantrun.core.service.application.service;

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

import static com.iwantrun.core.constant.AdminApplicationConstants.SITE_ADMIN_USER;

@Service
public class SiteMessageService {
    @Autowired
    private SiteMessageDao siteMessageDao;

    @Autowired
    private UserAccountDao userAccountDao;

    @Autowired
    private PurchaserAccountDao purchaserAccountDao;

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

        siteMessage.setFromUser(from_user);
        siteMessage.setSendtoUser(to_user);
        siteMessage.setMessageText(data.getAsString("message"));
        siteMessage.setOrderNo(data.getAsString("order_no"));
        siteMessage.setCreateTime(new Date());

        siteMessageDao.save(siteMessage);
        body.setSuccessful(true);
        return body;
    }

    public JSONArray getSiteMessageSendtoMe(String userid) {
        JSONArray result = new JSONArray();
        // find send to me
        if (!isPurcherUser(userid)) {
            userid = SITE_ADMIN_USER;
        }

        List<SiteMessage> list = siteMessageDao.findAllBySendtoUser(userid);
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
}
