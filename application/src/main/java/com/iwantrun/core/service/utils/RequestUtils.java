package com.iwantrun.core.service.utils;

import com.iwantrun.core.service.application.transfer.Message;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class RequestUtils {
    public static JSONObject parseRequestMessage(Message message) {
        String dataJson = message.getMessageBody();
        JSONObject object = (JSONObject) JSONValue.parse(dataJson);
        return object;
    }

    public static String getRequestLoginId(Message message) {
        JSONObject accessToken =(JSONObject) JSONValue.parse(message.getAccessToken());
        String loginId = accessToken.getAsString("currentUser");
        return loginId;
    }

    public static String JSONGetString(JSONObject reqeuest, String key, String defVal) {
        if (!reqeuest.containsKey(key)) {
            return defVal;
        }
        return reqeuest.getAsString(key);
    }
    public static String JSONGetString(JSONObject reqeuest, String key) {
        return JSONGetString(reqeuest, key, "");
    }

}
