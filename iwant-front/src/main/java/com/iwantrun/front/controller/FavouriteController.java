package com.iwantrun.front.controller;

import com.iwantrun.front.service.DictionaryService;
import com.iwantrun.front.service.FavouriteService;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("favourite")
public class FavouriteController {
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private FavouriteService service;

    @SuppressWarnings("rawtypes")
    @RequestMapping("/favouriteList")
    public String favouriteList(HttpServletRequest request, @RequestBody String param) {
        try {
            String token = CookieUtils.getLoginToken(request);
            Message result = service.queryFavouriteList(param, token); //查询收藏
            if( result != null) {
                return result.getMessageBody();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}