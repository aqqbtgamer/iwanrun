package com.iwantrun.core.service.application.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.iwantrun.core.service.application.dao.CaseAttachmentsDao;
import com.iwantrun.core.service.application.dao.CaseTagsDao;
import com.iwantrun.core.service.application.dao.CasesDao;
import com.iwantrun.core.service.application.dao.DictionaryDao;
import com.iwantrun.core.service.application.dao.JPQLEnableRepository;
import com.iwantrun.core.service.application.domain.CaseAttachments;
import com.iwantrun.core.service.application.domain.CaseTags;
import com.iwantrun.core.service.application.domain.Cases;
import com.iwantrun.core.service.application.domain.Dictionary;
import com.iwantrun.core.service.application.transfer.SimpleMessageBody;
import com.iwantrun.core.service.utils.JPADBUtils;
import com.iwantrun.core.service.utils.PageDataWrapUtils;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@Service
public class CasesService {
	
	Logger logger = LoggerFactory.getLogger(CasesService.class);
	
	@Autowired
	private CasesDao casesDao;
	@Autowired
	private CaseTagsDao caseTagsDao;
	@Autowired
	private CaseAttachmentsDao caseAttachmentsDao;
	@Autowired
	private Environment environment;
	@Autowired
	private JPQLEnableRepository jpqlExecute;
	@Autowired  
    private Environment env;
	@Autowired
	private DictionaryDao dictionaryDao;
	
	@Transactional
	public boolean createCase(Cases caseVo,List<CaseAttachments> attachments,List<CaseTags> tags) {
		boolean executeResult = false ;
		casesDao.saveAndFlush(caseVo);
		if(attachments != null) {
			for(CaseAttachments attach: attachments) {
				attach.setCaseId(caseVo.getId());
			}
			caseAttachmentsDao.saveAll(attachments);
		}		
		if(tags != null) {
			for(CaseTags tag : tags) {
				tag.setCaseId(caseVo.getId());
			}
			caseTagsDao.saveAll(tags);
		}	
		executeResult = true ;
		return executeResult;
	}
	
	public Page<Cases> findAllCasesPageable(int pageIndex){
		Integer pageSize = Integer.parseInt(env.getProperty("common.pageSize"));
		Pageable page =  PageRequest.of(pageIndex, pageSize, Sort.Direction.ASC, "id") ;
		return casesDao.findAll(page);
	}
	
	public Page<Cases> queryCaseByConditionPageable(int pageIndex,Cases example){		
		Integer pageSize = Integer.parseInt(env.getProperty("common.pageSize"));
		Pageable page =  PageRequest.of(pageIndex, pageSize, Sort.Direction.ASC, "id") ;
		return casesDao.findAll(JPADBUtils.generateSpecificationFromExample(example, new String[]{
				"=,id,and",
				"like,name,and",
				"like,trafficInfo,and",
				"like,foodInfo,and",
				"like,hotelInfo,and",
				"=,designDuringCode,and",
		}),page);
	}
	
	
	public Page<Cases> queryCaseBySpecificationPageable(int pageIndex,Specification<Cases> example){		
		Integer pageSize = Integer.parseInt(env.getProperty("common.pageSize"));
		Pageable page =  PageRequest.of(pageIndex, pageSize, Sort.Direction.ASC, "id") ;
		return casesDao.findAll(example,page);
	}

	/**
	 * 根据ID删除数据
	 * @param id
	 * @return
	 */
	@Transactional
	public String delete(String id) {
		SimpleMessageBody simple = new SimpleMessageBody();
		try {
			JSONObject jsonId = (JSONObject) JSONValue.parse(id);
			if(jsonId != null) {
				if(jsonId.getAsString("id")!= null) {
					Integer deleteId = Integer.parseInt(jsonId.getAsString("id"));
					this.casesDao.deleteById(deleteId);
				}
				if(jsonId.get("id[]") != null) {
					JSONArray jsonIdArray = (JSONArray) jsonId.get("id[]");
					Iterator<Object> it =  jsonIdArray.iterator();
					while(it.hasNext()) {
						Object idDelete = it.next();
						Integer deleteId = Integer.parseInt(idDelete.toString());
						this.casesDao.deleteById(deleteId);
					}
				}
			}		
			simple.setSuccessful(true);
		}catch(NumberFormatException e) {
			simple.setSuccessful(false);
			simple.setDescription(e.getMessage());
			logger.error("Id want to be deleted is not in good format",e);
		}		
		return JSONValue.toJSONString(simple);
	}

	public String get(Integer id) {
		String response = "";
		Optional<Cases> caseOP= casesDao.findById(id);
		if(caseOP.isPresent()) {
			Cases caseVo = caseOP.get();
			List<CaseAttachments> listAttch  = caseAttachmentsDao.findByCaseId(caseVo.getId());
			List<CaseTags> listTag = caseTagsDao.findByCaseId(caseVo.getId());
			JSONObject json = new JSONObject();
			json.put("caseVo", JSONValue.toJSONString(caseVo));
			json.put("listAttch", JSONValue.toJSONString(listAttch));
			json.put("listTag", JSONValue.toJSONString(listTag));
			response = json.toJSONString();
		}
		return response;
	}

	/**
	 * 1.delete all related componaunts  2.add new  attached componaunts
	 * @param location
	 * @param locationAttachments
	 * @param tagsList
	 * @return
	 */
	@Transactional
	public SimpleMessageBody modifyCase(Cases casevo, List<CaseAttachments> caseAttachments,
			List<CaseTags> tagsList) {
		SimpleMessageBody body = new SimpleMessageBody();
		body.setSuccessful(false);
		int caseId = casevo.getId();
		casesDao.save(casevo);	
		List<CaseAttachments> dbLocationAttachments = caseAttachmentsDao.findByCaseId(caseId);
		List<CaseTags> dbLocationTags = caseTagsDao.findByCaseId(caseId);
		caseAttachmentsDao.deleteAll(dbLocationAttachments);
		caseTagsDao.deleteAll(dbLocationTags);
		for(CaseAttachments attach : caseAttachments) {
			attach.setCaseId(caseId);
		}
		caseAttachmentsDao.saveAll(caseAttachments);
		for(CaseTags tags : tagsList) {
			tags.setCaseId(caseId);
		}
		caseTagsDao.saveAll(tagsList);
		body.setSuccessful(true);
		return body;
	}
	
	public PageImpl<Cases> queryCaseByDictListConditionPageable(List<String> activityProvinceCode,List<String> activitytype,List<String> companytype,List<Integer> duration,List<String> personNum,String pageIndex){	
		Integer pageSize = Integer.parseInt(environment.getProperty("common.pageSize"));
		int pageIndexInt =  pageIndex == null ? 1:Integer.parseInt(pageIndex)+1 ;
		Pageable page =  PageRequest.of(pageIndexInt-1, pageSize, Sort.Direction.ASC, "id");
		Long totalNum = casesDao.countByMutipleParams(activityProvinceCode,activitytype,companytype,duration,personNum,jpqlExecute);
		List<Cases> content = casesDao.findByMutipleParams(activityProvinceCode,activitytype,companytype,duration,personNum,jpqlExecute,pageSize,pageIndexInt);
		for( Cases vo :content) {
			List<CaseTags> listTag = caseTagsDao.findByCaseId(vo.getId());
			if(listTag!= null && listTag.size() >0 ) {
				String[] tips =new String[listTag.size()];
				for( int i=0;i< listTag.size();i++ ) {
					Dictionary dic = dictionaryDao.findByFiledNameCode(String.valueOf(listTag.get(i).getTagsType()),"case",listTag.get(i).getTagsCode());
					tips[i]=dic.getValue();

					
				}
				vo.setTips(tips);
			}
			
		}
		PageImpl<Cases> result = new PageImpl<Cases>(content, page, totalNum);
		return result;
	}
}
