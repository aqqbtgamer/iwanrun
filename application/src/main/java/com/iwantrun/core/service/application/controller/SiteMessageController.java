package com.iwantrun.core.service.application.controller;

import com.iwantrun.core.service.application.annotation.NeedTokenVerify;
import com.iwantrun.core.service.application.service.SiteMessageService;
import com.iwantrun.core.service.application.transfer.FavouriteCase;
import com.iwantrun.core.service.application.transfer.Message;
import com.iwantrun.core.service.utils.JSONUtils;
import com.iwantrun.core.service.utils.RequestUtils;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/application")
@RestController
public class SiteMessageController {
    private final static String SITE_ADMIN_USER = "admin";

    @Autowired
    private SiteMessageService service;

    //    @NeedTokenVerify
    @PostMapping("site_message/query")
    public Message getSiteMessage(@RequestBody Message message) {
        JSONObject request = RequestUtils.parseRequestMessage(message);
        String loginId = RequestUtils.getRequestLoginId(message);

        JSONArray result = service.getSiteMessage(request, loginId);
        message.setMessageBody(result.toJSONString());
        return message;
    }

    //    @NeedTokenVerify
    @PostMapping("site_message/update")
    public Message sendSiteMessage(@RequestBody Message message) {
        JSONObject request = RequestUtils.parseRequestMessage(message);
        String loginId = RequestUtils.getRequestLoginId(message);

        String result = service.updateSiteMessage(request, loginId);
        message.setMessageBody(result);
        return message;
    }

    //    @NeedTokenVerify
    @PostMapping("site_message/add")
    public Message addSiteMessage(@RequestBody Message message) {
        JSONObject request = RequestUtils.parseRequestMessage(message);
        String loginId = RequestUtils.getRequestLoginId(message);

        String result = service.addSiteMessage(request, loginId);
        message.setMessageBody(result);
        return message;
    }

/*
//    @NeedTokenVerify
    @PostMapping("/application/siteMessage/myself")
    public Message getSiteMessageMyself(@RequestBody Message message) {
        String dataJson = message.getMessageBody();
        JSONObject object = (JSONObject) JSONValue.parse(dataJson);
        JSONObject accessToken =(JSONObject) JSONValue.parse(message.getAccessToken());
        String login_id = accessToken.getAsString("currentUser");

        JSONArray result = service.getSiteMessageByFrom(login_id);
        message.setMessageBody(result.toJSONString());
        return message;
    }
*/
}