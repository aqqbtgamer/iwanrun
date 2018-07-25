package com.iwantrun.front.controller;

import com.iwantrun.front.service.DictionaryService;
import com.iwantrun.front.service.FavouriteService;
import com.iwantrun.front.service.SiteMessageService;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SiteMessageController {
    @Autowired
    private SiteMessageService service;

    @PutMapping("site_message")
    @PostMapping("site_message")
    public String sendSiteMessage(HttpServletRequest request, @RequestBody String param) {
        try {
            String token = CookieUtils.getLoginToken(request);
            Message result = service.sendSiteMessage(param, token);
            if( result != null) {
                return result.getMessageBody();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @GetMapping("site_message")
    public String getSiteMessageToMe(HttpServletRequest request) {
        try {
            String token = CookieUtils.getLoginToken(request);
            Message result = service.getSiteMessageToMe(token);
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
}