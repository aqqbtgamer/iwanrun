package com.iwantrun.core.service.application.intercepter;

import java.util.Date;
import java.util.Optional;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iwantrun.core.service.application.dao.OrderHistoryDao;
import com.iwantrun.core.service.application.dao.OrdersDao;
import com.iwantrun.core.service.application.domain.OrderHistory;
import com.iwantrun.core.service.application.domain.Orders;
import com.iwantrun.core.service.application.enums.TradeStatus;

@Aspect
@Component
public class OrdersDaoInvokerIntercepter {
	Logger logger = LoggerFactory.getLogger(OrdersDaoInvokerIntercepter.class);
	
	@Autowired
	private OrderHistoryDao historyDao ;
	
	@Autowired
	private OrdersDao ordersDao ;
	
	private static final String target = "save" ;	
	
	private static final String createInfo = "用户#{mobileNumber}提交了订单 ";
	
	private static final String assignInfo = "管理员指派订单给咨询师 #{mobileNumber}";
	
	private static final String executeInfo = "咨询师 #{mobileNumber}更新了订单 ";
	
	private static final String completeInfo = "用户 #{mobileNumber} 同意草案 订单实施中 ";
	
	private static final String finishedInfo = "用户#{mobileNumber}同意结项 ";
	
	private static final String updateInfo = "用户#{mobileNumber}不同意结项 ";
	
	@Pointcut("execution (* com.iwantrun.core.service.application.dao.OrdersDao.*(..))")	
	public void methodPointCut() {}
	
	@Around("methodPointCut()")
	public Object intercepter(ProceedingJoinPoint  point) {
		logger.info("enter ordersDao save intercepter");
		try {
			MethodSignature  method = (MethodSignature) point.getSignature();
			String name  = method.getName();
			OrderHistory history = new OrderHistory();			
			boolean historyFlag = false;
			Object[] args = point.getArgs();
			Orders order = null;
			int id = 0 ;
			if(name != null && name.contains(target)) {
				historyFlag = true ;
				if(args[0] instanceof Orders) {
					order = (Orders) args[0];
					id = order.getId();
					//save后的orders状态				
					history.setAfterStatusCode(order.getOrderStatusCode());	
					if(id != 0) {
						Optional<Orders> op = ordersDao.findById(id);
						if(op.isPresent()) {
							history.setBeforeStatusCode(op.get().getOrderStatusCode());
						}
					}
					//根据save后状态 填入不同的changeInfo
					if(id  == 0 && TradeStatus.OPENED.getId() == order.getOrderStatusCode() ) {
						history.setChange_by(order.getOrderOwnerId());
						history.setChangeInfo(createInfo);	
						history.setIfChangeByAdmin(0);					
					}else if(TradeStatus.ASSIGNED.getId() == order.getOrderStatusCode() && order.getOrderStatusCode() != history.getBeforeStatusCode()  ) {
						history.setChange_by(null);
						history.setChangeInfo(assignInfo);	
						history.setIfChangeByAdmin(1);					
					}else if(TradeStatus.ASSIGNED.getId() == order.getOrderStatusCode() && order.getOrderStatusCode() == history.getBeforeStatusCode()) {
						history.setChange_by(order.getOrderAdviserId());
						history.setChangeInfo(executeInfo);	
						history.setIfChangeByAdmin(0);
					}					
					else if(TradeStatus.EXECUTING.getId() == order.getOrderStatusCode()) {
						history.setChange_by(order.getOrderAdviserId());
						history.setChangeInfo(completeInfo);	
						history.setIfChangeByAdmin(0);		
					}
					else if(TradeStatus.COMPLETED.getId() == order.getOrderStatusCode()) {
						history.setChange_by(order.getOrderOwnerId());
						history.setChangeInfo(finishedInfo);	
						history.setIfChangeByAdmin(0);		
					}
					else if(TradeStatus.COMPLETED_UPDATE.getId() == order.getOrderStatusCode()) {
						history.setChange_by(order.getOrderOwnerId());
						history.setChangeInfo(updateInfo);	
						history.setIfChangeByAdmin(0);		
					}
				}				
			}
			Object result = point.proceed();
			if(historyFlag) {	
				//save后的Order id
				history.setOrderId(((Orders)result).getId());
				if(id != 0) {
					Optional<Orders> op = ordersDao.findById(id);
					if(op.isPresent()) {
						history.setBeforeStatusCode(op.get().getOrderStatusCode());
					}
				}
				history.setChangeTime(new Date());
				historyDao.save(history);
			}
			return result ;
		} catch (Throwable e) {
			logger.error("execute orders dao error ",e);
			return null;
		}
	}
}
