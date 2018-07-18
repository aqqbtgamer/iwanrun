package com.iwantrun.core.service.application.controller;

import com.iwantrun.core.service.application.domain.Cases;
import com.iwantrun.core.service.application.domain.Dictionary;
import com.iwantrun.core.service.application.service.CasesService;
import com.iwantrun.core.service.application.service.DictionaryService;
import com.iwantrun.core.service.application.service.FavouriteService;
import com.iwantrun.core.service.application.transfer.FavouriteCase;
import com.iwantrun.core.service.application.transfer.Message;
import com.iwantrun.core.service.application.transfer.PageDomianRequest;
import com.iwantrun.core.service.utils.EntityDictionaryConfigUtils;
import com.iwantrun.core.service.utils.JPADBUtils;
import com.iwantrun.core.service.utils.JSONUtils;
import com.iwantrun.core.service.utils.PageDataWrapUtils;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class FavouriteController {
    Logger logger = LoggerFactory.getLogger(CaseController.class);

    @Autowired
    private FavouriteService favouriteService;
    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping("/application/favourite/queryFavourite")
    public Message queryFavourite(@RequestBody Message message) {
        String dataJson = message.getMessageBody();
        JSONObject object = (JSONObject) JSONValue.parse(dataJson);

        // FIXME: how to get userId from session.
        int userId = 0;
        String caseType = object.getAsString("name");
        if ("location" == caseType) {
            List<FavouriteCase> favouriteCaseList = favouriteService.queryFavouriteCase(userId, caseType);
            message.setMessageBody(JSONUtils.objToJSON(favouriteCaseList));
        }
        return message;
    }
}
