package com.iwantrun.front.service;

import com.iwantrun.front.transfer.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SiteMessageService {
    @Autowired
    private Environment environment;
    @Autowired
    private RestTemplate template;

    public Message forwardRequestService(String reqUrl, String param, String token) {
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

    public Message sendSiteMessage(String param, String token) {
        String findByName = environment.getProperty("application.siteMessage");
        String baseUrl = environment.getProperty("app.server");

        String url = baseUrl + findByName;

        Message message = new Message();
        message.setAccessToken(token);
        message.setMessageBody(param);
        message.setRequestMethod(url);
        message = template.postForEntity(url, message, Message.class).getBody();

        return message;
    }

    public Message getSiteMessage(String token, String type) {
        String findByName = environment.getProperty("application.siteMessage");
        String baseUrl = environment.getProperty("app.server");

        String url = baseUrl + findByName;

        Message message = new Message();
        message.setAccessToken(token);
        message.setRequestMethod(url);
        message.setMessageBody(type);
        message = template.postForEntity(url, message, Message.class).getBody();

        return message;
    }

    public Message getSiteMessageMyself(String token) {
        String findByName = environment.getProperty("application.siteMessage.myself");
        String baseUrl = environment.getProperty("app.server");

        String url = baseUrl + findByName;

        Message message = new Message();
        message.setAccessToken(token);
        message.setRequestMethod(url);
        message = template.postForEntity(url, message, Message.class).getBody();

        return message;
    }
}
