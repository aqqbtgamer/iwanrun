package com.iwantrun.front.service;

import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Service
public class ForwardService {
    @Autowired
    private Environment environment;
    @Autowired
    private RestTemplate template;

    private Message forwardRequestService(String reqUrl, String param, String token) {
        String frontPrefix = environment.getProperty("server.servlet.context-path");
        String appPrefix = environment.getProperty("application.servlet.context-path");
        String baseUrl = environment.getProperty("app.server");

        String url = baseUrl + appPrefix + reqUrl.substring(frontPrefix.length());

        Message message = new Message();
        message.setAccessToken(token);
        message.setMessageBody(param);
        message.setRequestMethod(url);
        message = template.postForEntity(url, message, Message.class).getBody();

        return message;
    }

    public String forwardRequest(HttpServletRequest request, @RequestBody String param) {
        try {
            String token = CookieUtils.getLoginToken(request);
            Message result = forwardRequestService(request.getRequestURI(), param, token);
            if( result != null) {
                return result.getMessageBody();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
