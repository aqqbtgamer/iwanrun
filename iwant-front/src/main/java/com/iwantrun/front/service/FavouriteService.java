package com.iwantrun.front.service;

import com.iwantrun.front.transfer.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FavouriteService {
    @Autowired
    private Environment environment;
    @Autowired
    private RestTemplate template;
    @Autowired
    private DictionaryService dicService;

    public Message queryFavouriteList(String param, String token) {
        String findByName = environment.getProperty("application.favourite.queryFavourite");
        String baseUrl = environment.getProperty("app.server");

        String url = baseUrl + findByName;

        Message message = new Message();
        message.setAccessToken(token);
        message.setMessageBody(param);
        message.setRequestMethod(url);
        message = template.postForEntity(url, message, Message.class).getBody();

        return message;
    }

    public Message addFavourite(String param, String token) {
        String findByName = environment.getProperty("application.favourite.addFavourite");
        String baseUrl = environment.getProperty("app.server");

        String url = baseUrl + findByName;

        Message message = new Message();
        message.setAccessToken(token);
        message.setMessageBody(param);
        message.setRequestMethod(url);
        message = template.postForEntity(url, message, Message.class).getBody();

        return message;
    }

    public Message delFavourite(String param, String token) {
        String findByName = environment.getProperty("application.favourite.delFavourite");
        String baseUrl = environment.getProperty("app.server");

        String url = baseUrl + findByName;

        Message message = new Message();
        message.setAccessToken(token);
        message.setMessageBody(param);
        message.setRequestMethod(url);
        message = template.postForEntity(url, message, Message.class).getBody();

        return message;
    }
}
