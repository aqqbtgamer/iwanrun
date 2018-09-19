package com.iwantrun.core.service.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.core.service.application.service.SiteMessageService;
import com.iwantrun.core.service.application.transfer.Message;
import com.iwantrun.core.service.utils.PageDataWrapUtils;
import com.iwantrun.core.service.utils.RequestUtils;

import net.minidev.json.JSONObject;

@RequestMapping("/application")
@RestController
public class SiteMessageController {
    @Autowired
    private SiteMessageService service;

    @PostMapping("site_message/query")
    public Message getSiteMessage(@RequestBody Message message) {
        JSONObject request = RequestUtils.parseRequestMessage(message);
        String loginId = RequestUtils.getRequestLoginId(message);
		PageImpl<JSONObject> result = service.getSiteMessage(request, loginId);
		message.setMessageBody(PageDataWrapUtils.page2JsonNoCopy(result));
        return message;
    }

    @PostMapping("site_message/update")
    public Message sendSiteMessage(@RequestBody Message message) {
        JSONObject request = RequestUtils.parseRequestMessage(message);
        String loginId = RequestUtils.getRequestLoginId(message);

        String result = service.updateSiteMessage(request, loginId);
        message.setMessageBody(result);
        return message;
    }

    @PostMapping("site_message/add")
    public Message addSiteMessage(@RequestBody Message message) {
        JSONObject request = RequestUtils.parseRequestMessage(message);
        String loginId = RequestUtils.getRequestLoginId(message);

        String result = service.addSiteMessage(request, loginId);
        message.setMessageBody(result);
        return message;
    }
}