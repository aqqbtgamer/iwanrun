package com.iwantrun.front.controller;

import com.iwantrun.front.service.DictionaryService;
import com.iwantrun.front.service.FavouriteService;
import com.iwantrun.front.service.ForwardService;
import com.iwantrun.front.service.SiteMessageService;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SiteMessageController {
    @Autowired
    private ForwardService service;

    @PostMapping("site_message/{query|add|update}")
    //@PostMapping("site_message/add")
    //@PostMapping("site_message/update")
    public String sendSiteMessage(HttpServletRequest request, @RequestBody String param) {
        return service.forwardRequest(request, param);
    }
/*
    public String updateState(HttpServletRequest request, @RequestBody String param) {
        return service.forwardRequest(request, param);
    }

    public String addSiteMessage(HttpServletRequest request, @RequestBody String param) {
        return service.forwardRequest(request, param);
    }
*/
/*
    @GetMapping("site_message/{type:all|unread}")
    public String getSiteMessageToMe(HttpServletRequest request, @PathVariable String type) {
        try {
            String token = CookieUtils.getLoginToken(request);
            Message result = service.getSiteMessage(token, type);
            if( result != null) {
                return result.getMessageBody();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    @GetMapping("site_message/myself")
    public String getSiteMessageMyself(HttpServletRequest request) {
        try {
            String token = CookieUtils.getLoginToken(request);
            Message result = service.getSiteMessageMyself(token);
            if( result != null) {
                return result.getMessageBody();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
*/
}