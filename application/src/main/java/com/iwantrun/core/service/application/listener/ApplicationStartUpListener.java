package com.iwantrun.core.service.application.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ApplicationStartUpListener implements ApplicationListener<ContextRefreshedEvent> {

	Logger logger = LoggerFactory.getLogger(ApplicationStartUpListener.class); 
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {		
		logger.info("into application start listener ...");
	}

}
