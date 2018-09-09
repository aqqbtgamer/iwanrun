package com.iwantrun.core.service.application.controller;

import com.iwantrun.core.service.application.service.TradeStatusService;
import com.iwantrun.core.service.application.transfer.Message;
import com.iwantrun.core.service.utils.RequestUtils;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/application")
@RestController
public class TradeStatusController {
    private final static String SITE_ADMIN_USER = "admin";

    @Autowired
    private TradeStatusService service;

    //    @NeedTokenVerify
    @PostMapping("trade_status/query")
    public Message getSiteMessage(@RequestBody Message message) {
        JSONObject request = RequestUtils.parseRequestMessage(message);
        String loginId = RequestUtils.getRequestLoginId(message);

        JSONArray result = service.getTradeStatus(request, loginId);
        message.setMessageBody(result.toJSONString());
        return message;
    }
/*
    //    @NeedTokenVerify
    @PostMapping("trade_status/update")
    public Message sendTradeStatus(@RequestBody Message message) {
        JSONObject request = RequestUtils.parseRequestMessage(message);
        String loginId = RequestUtils.getRequestLoginId(message);

        String result = service.updateTradeStatus(request, loginId);
        message.setMessageBody(result);
        return message;
    }

    //    @NeedTokenVerify
    @PostMapping("trade_status/add")
    public Message addTradeStatus(@RequestBody Message message) {
        JSONObject request = RequestUtils.parseRequestMessage(message);
        String loginId = RequestUtils.getRequestLoginId(message);

        String result = service.addTradeStatus(request, loginId);
        message.setMessageBody(result);
        return message;
    }

    //    @NeedTokenVerify
    @PostMapping("trade_status/delete")
    public Message deleteTradeStatus(@RequestBody Message message) {
        JSONObject request = RequestUtils.parseRequestMessage(message);
        String loginId = RequestUtils.getRequestLoginId(message);

        String result = service.deleteTradeStatus(request, loginId);
        message.setMessageBody(result);
        return message;
    }
*/
}