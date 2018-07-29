package com.iwantrun.front.controller;

import com.iwantrun.front.service.DictionaryService;
import com.iwantrun.front.service.FavouriteService;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("favourite")
public class FavouriteController {
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private FavouriteService service;

    @SuppressWarnings("rawtypes")
    @RequestMapping("/query/{type:case|product|location}")
    public String queryFavourite(HttpServletRequest request, @PathVariable String type) {
        try {
            String token = CookieUtils.getLoginToken(request);
            Message result = service.queryFavouriteList(type, token); //查询收藏
            if( result != null) {
                return result.getMessageBody();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @RequestMapping("/add")
    public String addFavourite(HttpServletRequest request, @RequestBody String param) {
        try {
            String token = CookieUtils.getLoginToken(request);
            Message result = service.addFavourite(param, token);
            if( result != null) {
                return result.getMessageBody();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    @RequestMapping("/delete")
    public String delFavourite(HttpServletRequest request, @RequestBody String param) {
        try {
            String token = CookieUtils.getLoginToken(request);
            Message result = service.delFavourite(param, token);
            if( result != null) {
                return result.getMessageBody();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}