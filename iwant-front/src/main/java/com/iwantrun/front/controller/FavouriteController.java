package com.iwantrun.front.controller;

import com.iwantrun.front.service.DictionaryService;
import com.iwantrun.front.service.FavouriteService;
import com.iwantrun.front.transfer.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("favourite")
public class FavouriteController {
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private FavouriteService service;
    @SuppressWarnings("rawtypes")
    @RequestMapping("/favouriteList")
    @ResponseBody
    public String favouriteList(@RequestBody String param) {
        try {
            Message result = service.queryFavouriteList(param); //查询收藏
            if( result != null) {
                return result.getMessageBody();
            }
        } catch (Exception e) {

        }
        return null;
    }
}