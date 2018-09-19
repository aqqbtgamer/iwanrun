package com.iwantrun.core.service.application.service;

import static com.iwantrun.core.service.utils.RequestUtils.JSONGetString;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.iwantrun.core.service.application.dao.JPQLEnableRepository;
import com.iwantrun.core.service.application.dao.PurchaserAccountDao;
import com.iwantrun.core.service.application.dao.SiteMessageDao;
import com.iwantrun.core.service.application.domain.PurchaserAccount;
import com.iwantrun.core.service.application.domain.SiteMessage;

import net.minidev.json.JSONObject;

@Service
public class SiteMessageService {
    @Autowired
    private SiteMessageDao siteMessageDao;
	@Autowired
	private Environment environment;
	@Autowired
	private JPQLEnableRepository jpqlExecute;
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
		siteMessage.setOrderNo(null != orderNo ? orderNo : "");
        siteMessage.setCreateTime(new Date());
		siteMessage.setIsRead(SiteMessage.NO_READ);
		// siteMessage.setBlRead(false);

        siteMessageDao.save(siteMessage);
        return "success";
    }

    public String addSiteMessage(String toUserId, String message) {
        return addSiteMessage("", toUserId, message, "");
    }

	public PageImpl<JSONObject> getSiteMessage(JSONObject request, String userid) {

		String pageSizeStr;
		if (request == null || !request.containsKey("pageSize")) {
			pageSizeStr = environment.getProperty("common.pageSize");
		} else {
			pageSizeStr = request.getAsString("pageSize");
		}

		Integer pageSize = Integer.valueOf(pageSizeStr);
		Integer pageIndex = Integer.valueOf(JSONGetString(request, "pageIndex"));
		int pageIndexInt = pageIndex == null ? 1 : pageIndex + 1;
		Pageable page = PageRequest.of(pageIndexInt - 1, pageSize);

		String isRead = JSONGetString(request, "isRead");
		SiteMessage vo = new SiteMessage();
		vo.setFromUser(userid);
		vo.setSendtoUser(userid);
		vo.setIsRead(isRead);

		Long totalNum;
		if (StringUtils.isEmpty(isRead)) {
			totalNum = siteMessageDao.countByFromUserOrSendtoUser(userid, userid);
		} else {
			totalNum = siteMessageDao.countByIsReadAndFromUserOrSendtoUser(isRead, userid, userid);
		}
		List<SiteMessage> list = siteMessageDao.findByMutipleParams(vo, jpqlExecute, pageSize, pageIndexInt);

		List<JSONObject> resultList = new ArrayList<>();
		for (SiteMessage siteMessage : list) {
			JSONObject obj = new JSONObject();
			obj.put("msgid", siteMessage.getId());
			obj.put("message", siteMessage.getMessageText());
			obj.put("blread", siteMessage.isBlRead());
			Date createTime = siteMessage.getCreateTime();
			if (createTime != null) {
				obj.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime));
			} else {
				obj.put("timestamp", "");
			}
			obj.put("order_no", siteMessage.getOrderNo());
			resultList.add(obj);
		}

		return new PageImpl<JSONObject>(resultList, page, totalNum);
	}
    public String updateSiteMessage(JSONObject request, String userid) {
        int msgid = request.getAsNumber("msgid").intValue();
        String result = "success";

        Optional<SiteMessage> optional = siteMessageDao.findById(msgid);
        if (!optional.isPresent()) {
            result = "msgid is not exist!";
        } else {
            SiteMessage siteMessage = optional.get();
			// siteMessage.setBlRead(true);
			siteMessage.setIsRead(SiteMessage.HAS_READ);
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
