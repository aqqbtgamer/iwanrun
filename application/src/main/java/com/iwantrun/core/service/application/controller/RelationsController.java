package com.iwantrun.core.service.application.controller;

import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.core.constant.AdminApplicationConstants;
import com.iwantrun.core.service.application.annotation.NeedTokenVerify;
import com.iwantrun.core.service.application.service.RelationService;
import com.iwantrun.core.service.application.transfer.Message;

@RestController
@RequestMapping("application/relations")
public class RelationsController {
	Logger logger = LoggerFactory.getLogger(RelationsController.class);
	
	@Autowired
	private RelationService relationService ;
	
	@RequestMapping("add")
	@NeedTokenVerify
	public Message addRelations(@RequestBody Message message) {
		String inputJson = message.getMessageBody();
		String result;
		try {
			result = relationService.addRelation(inputJson);
		} catch (IllegalAccessException e) {
			logger.error("error add relations",e);
			result = AdminApplicationConstants.Add_FAIL_RESULT;
		} catch (InvocationTargetException e) {
			logger.error("error add relations",e);
			result = AdminApplicationConstants.Add_FAIL_RESULT;
		} catch (NoSuchMethodException e) {
			logger.error("error add relations",e);
			result = AdminApplicationConstants.Add_FAIL_RESULT;
		}
		message.setMessageBody(result);
		return message;
	}
	
	@RequestMapping("queryBindings")
	public Message queryRelationBinding(@RequestBody Message message) {
		String inputJson = message.getMessageBody();
		String result;
		try {
			result = relationService.queryRelations(inputJson);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			logger.error("error queryRelationBinding",e);
			result = AdminApplicationConstants.Add_FAIL_RESULT;
		}
		message.setMessageBody(result);
		return message;
	}
	
	@RequestMapping("deleteBindings")
	public Message deleteBindings(@RequestBody Message message) {
		String inputJson = message.getMessageBody();
		String result;
		try {
			result = relationService.deleteBindings(inputJson);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			logger.error("error queryRelationBinding",e);
			result = AdminApplicationConstants.Add_FAIL_RESULT;
		}
		message.setMessageBody(result);
		return message;
	}
}
