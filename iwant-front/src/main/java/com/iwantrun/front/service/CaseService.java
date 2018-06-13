package com.iwantrun.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iwantrun.front.domain.Case;
import com.iwantrun.front.domain.Dictionary;
import com.iwantrun.front.utils.JSONUtils;
@Service
public class CaseService {
	@Autowired
	private DictionaryService dicService;
	
	public Case caseDictionaryDataDo(String messageBodyJson) {
		List<Dictionary> dictoryList = JSONUtils.toList(messageBodyJson, Dictionary.class);
		Case caseVo = new Case();
		List<Dictionary> activitytypeList = dicService.filterByUsedField(dictoryList,"9");
		caseVo.setActivitytype(activitytypeList);//设置活动类型
		caseVo.setCompanytype(dicService.filterByUsedField(dictoryList,"24"));
		caseVo.setPersonNum(dicService.filterByUsedField(dictoryList,"22"));
		caseVo.setDuration(dicService.filterByUsedField(dictoryList,"23"));
//		caseVo.setLocation(dicService.filterByUsedField(dictoryList,"24"));
		return caseVo;
		
	}

}
