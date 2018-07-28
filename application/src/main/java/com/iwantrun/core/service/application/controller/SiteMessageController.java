package com.iwantrun.core.service.application.controller;

import com.iwantrun.core.service.application.annotation.NeedTokenVerify;
import com.iwantrun.core.service.application.service.SiteMessageService;
import com.iwantrun.core.service.application.transfer.FavouriteCase;
import com.iwantrun.core.service.application.transfer.Message;
import com.iwantrun.core.service.utils.JSONUtils;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class SiteMessageController {
    private final static String SITE_ADMIN_USER = "admin";

    @Autowired
    private SiteMessageService service;

//    @NeedTokenVerify
    @PostMapping("/application/siteMessage")
    public Message sendSiteMessage(@RequestBody Message message) {
        String dataJson = message.getMessageBody();
        JSONObject object = (JSONObject) JSONValue.parse(dataJson);
        JSONObject accessToken =(JSONObject) JSONValue.parse(message.getAccessToken());
        String login_id = accessToken.getAsString("currentUser");

        service.sendSiteMessage(login_id, object);
        message.setMessageBody("OK");
        return message;
    }

//    @NeedTokenVerify
    @PostMapping("/application/siteMessage/tome")
    public Message getSiteMessageToMe(@RequestBody Message message) {
        String dataJson = message.getMessageBody();
        JSONObject object = (JSONObject) JSONValue.parse(dataJson);
        JSONObject accessToken =(JSONObject) JSONValue.parse(message.getAccessToken());
        String login_id = accessToken.getAsString("currentUser");

        JSONArray result = service.getSiteMessageSendtoMe(login_id);
        message.setMessageBody(result.toJSONString());
        return message;
    }

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
}