package com.iwantrun.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.admin.constant.AdminApplicationConstants;

@Configuration
public class RESTTemplateConfig {
	
	@Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory){
        return new RestTemplate(factory);
    }
	
	@Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(AdminApplicationConstants.HTTP_READ_TIME_OUT);//ms
        factory.setConnectTimeout(AdminApplicationConstants.HTTP_CONNECT_TIME_OUT);//ms
        return factory;
    }

}
