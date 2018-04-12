package com.iwantrun.core.service.application.intercepter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.iwantrun.core.service.application.annotation.NeedTokenVerify;

@Aspect
@Component
public class ControllInvokerIntercepter {
	
	Logger logger = LoggerFactory.getLogger(ControllInvokerIntercepter.class);
	
	@Pointcut("execution (* com.iwantrun.core.service.application.controller..*(..)) ")
	public void methodPointCut() {
		
	}
	
	@Around("methodPointCut()")
	public Object intercepter(ProceedingJoinPoint  point) {
		logger.info("enter controller intercepter");
		Signature signature = point.getSignature();
		MethodSignature methodSignature = (MethodSignature)signature;
		NeedTokenVerify need = methodSignature.getMethod().getAnnotation(NeedTokenVerify.class);
		if(need != null) {
			logger.info("need TokenVerify");
		}		
		try {
			return point.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}		
	}

}
