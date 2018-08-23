package com.iwantrun.front.controller;

import com.iwantrun.front.service.ForwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TradeStatus {
    @Autowired
    private ForwardService service;

    @PostMapping("trade_status/{query|add|delete|update}")
    public String forwardRequest(HttpServletRequest request, @RequestBody String param) {
        return service.forwardRequest(request, param);
    }
}
