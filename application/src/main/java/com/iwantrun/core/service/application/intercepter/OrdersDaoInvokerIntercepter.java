package com.iwantrun.core.service.application.intercepter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OrdersDaoInvokerIntercepter {
Logger logger = LoggerFactory.getLogger(ControllInvokerIntercepter.class);
	
	@Pointcut("execution (* com.iwantrun.core.service.application.dao.OrdersDao.*(..))")	
	public void methodPointCut() {
		
	}
	
	@Around("methodPointCut()")
	public Object intercepter(ProceedingJoinPoint  point) {
		logger.info("enter ordersDao save intercepter");
		try {
			return point.proceed();
		} catch (Throwable e) {
			logger.error("execute orders dao error ",e);
			return null;
		}
	}
}
