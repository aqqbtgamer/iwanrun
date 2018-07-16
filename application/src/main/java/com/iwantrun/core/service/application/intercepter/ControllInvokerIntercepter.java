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
import com.iwantrun.core.service.application.transfer.Message;
import com.iwantrun.core.service.utils.LoginTokenVerifyUtils;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

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
		Object[] args =point.getArgs();
		MethodSignature methodSignature = (MethodSignature)signature;
		NeedTokenVerify need = methodSignature.getMethod().getAnnotation(NeedTokenVerify.class);
		if(need != null) {
			logger.info("need TokenVerify");
			boolean validToken = false ;
			try {
				if(args != null && args.length >0) {
					for(Object arg : args) {
						if(arg instanceof Message) {
							String accessToken = ((Message)arg).getAccessToken();
							JSONObject object =(JSONObject) JSONValue.parse(accessToken);							
							validToken = 
									LoginTokenVerifyUtils.verifyLoginToken
									(object.getAsString("sessionId"), object.getAsString("currentUser"), object.getAsString("loginToken"));
						}
					}
				}
				if(!validToken) {
					return "token verify failed  not valid user";
				}
			}catch(Exception e) {
				logger.info("getting input params error");
				return null;
			}
		}		
		try {
			return point.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}		
	}

}
