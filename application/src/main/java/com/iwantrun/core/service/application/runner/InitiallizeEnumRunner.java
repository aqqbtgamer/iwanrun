package com.iwantrun.core.service.application.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.iwantrun.core.service.utils.PackageScanner;

@Component
@Order(value=1)
public class InitiallizeEnumRunner implements CommandLineRunner {
	
	Logger logger = LoggerFactory.getLogger(InitiallizeEnumRunner.class);
	
	private final String ENUM_PACKAGE = "com.iwantrun.core.service.application.enums";

	@Override
	public void run(String... args) throws Exception {
		logger.info("InitiallizeEnumRunner executing...");
		String classPath = ResourceUtils.getURL("classpath:").getPath();		
		String enumPath = classPath+PackageScanner.package2Dir(ENUM_PACKAGE);
		logger.info("enumPath ..."+enumPath);
	}

}
