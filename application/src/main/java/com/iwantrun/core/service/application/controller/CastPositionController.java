package com.iwantrun.core.service.application.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.core.service.application.annotation.NeedTokenVerify;
import com.iwantrun.core.service.application.domain.CastPosition;
import com.iwantrun.core.service.application.service.CastPositionService;
import com.iwantrun.core.service.application.transfer.Message;
import com.iwantrun.core.service.utils.JSONUtils;

@RestController
@RequestMapping("/application/castposition/")
public class CastPositionController {
	
	Logger logger = LoggerFactory.getLogger(CastPositionController.class);
	
	@Autowired
	private CastPositionService castPosition ;
	
	@RequestMapping("getAll")
	public Message getAll(@RequestBody Message message) {
		String result = castPosition.getAllCastPostion();
		message.setMessageBody(result);
		return message;
	}
	
	@RequestMapping("addAll")
	@NeedTokenVerify
	public Message addAll(@RequestBody Message message) {
		String dataJSON = message.getMessageBody();
		List<CastPosition> list = JSONUtils.toList(dataJSON, CastPosition.class);
		castPosition.deleteAllCastPosition();
		String result = castPosition.addAllCastPostion(list);
		message.setMessageBody(result);
		return message;
	}
	
	@RequestMapping("deleteAll")
	@NeedTokenVerify
	public Message deleteAll(@RequestBody Message message) {		
		String result = castPosition.deleteAllCastPosition();
		message.setMessageBody(result);
		return message;
	}
	
	
	

}
