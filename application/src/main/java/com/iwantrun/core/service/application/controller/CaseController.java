package com.iwantrun.core.service.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iwantrun.core.service.application.annotation.NeedTokenVerify;
import com.iwantrun.core.service.application.domain.CaseAttachments;
import com.iwantrun.core.service.application.domain.SearchDictionaryArray;
import com.iwantrun.core.service.application.domain.CaseTags;
import com.iwantrun.core.service.application.domain.Cases;
import com.iwantrun.core.service.application.domain.Dictionary;
import com.iwantrun.core.service.application.domain.SearchDictionaryList;
import com.iwantrun.core.service.application.service.CasesService;
import com.iwantrun.core.service.application.service.DictionaryService;
import com.iwantrun.core.service.application.transfer.Message;
import com.iwantrun.core.service.application.transfer.PageDomianRequest;
import com.iwantrun.core.service.application.transfer.SimpleMessageBody;
import com.iwantrun.core.service.utils.DictionaryConfigParams;
import com.iwantrun.core.service.utils.EntityBeanUtils;
import com.iwantrun.core.service.utils.EntityDictionaryConfigUtils;
import com.iwantrun.core.service.utils.JPADBUtils;
import com.iwantrun.core.service.utils.JSONUtils;
import com.iwantrun.core.service.utils.ListUpdateUtils;
import com.iwantrun.core.service.utils.MappingGenerateUtils;
import com.iwantrun.core.service.utils.PageDataWrapUtils;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@RestController
public class CaseController {
	
	Logger logger = LoggerFactory.getLogger(CaseController.class);
	
	@Autowired
	private CasesService casesService;
	@Autowired
	private DictionaryService dictionaryService;
	

	@SuppressWarnings("unchecked")
	@RequestMapping("/application/cases/add")
	@NeedTokenVerify
	public Message addCase(@RequestBody Message message) {
		Message response = new Message();
		response.setAccessToken(message.getAccessToken());
		response.setRequestMethod(message.getRequestMethod());
		Cases Case = new Cases();
		String dataJson = message.getMessageBody();
		JSONObject object = (JSONObject) JSONValue.parse(dataJson);
		Map<String,String> mappingRelation = 
				MappingGenerateUtils.generateMappingRelation(new String[] {
						"name=>name",
						"activityProvinceCode=>activityProvinceCode",
						"activityCityCode=>activityCityCode",
						"activityDistCode=>activityDistCode",
						"location=>location",
						"activityTypeCode=>activityTypeCode",
						"companyTypeCode=>companyTypeCode",
						"groupNumber=>groupNumber",
						"during=>during",
						"designDuringCode=>designDuringCode",
					    "executeDuringCode=>executeDuringCode",
					    "trafficInfo=>trafficInfo",
					    "foodInfo=>foodInfo",
					    "hotelInfo=>hotelInfo",
					    "priority=>priority",
					    "simulatePriceCode=>simulatePriceCode",
					    "orderId=>orderId",
						"descirbeText1=>_ue",
						"descirbeText2=>mainImage"
				});
		EntityBeanUtils.beanCreateFromJson(Case, mappingRelation, object);
		List<CaseAttachments> caseAttachments = new ArrayList<CaseAttachments>();
		Map<String,String> mappingRelation0 = 
				MappingGenerateUtils.generateMappingRelation(new String[] {
						"filePath=>bean"
				});
		JSONArray array = (JSONArray) object.get("imgManage[]");		
		EntityBeanUtils.listBeanCreateFromJson(caseAttachments, mappingRelation0, array, CaseAttachments.class);
		Function<String,String> fun = s -> {
			int index = s.lastIndexOf("/");
			return s.substring(index+1);
		} ;
		
		BiFunction<String,Integer,String> biFun = (value,index) -> value+"-"+index ;
		ListUpdateUtils.updateListProperty(caseAttachments, 
				new String[] {
						"filePath=>fileName"
				}, 
				new Function[] {
						fun
				}
				, new String[] {
						"pagePath==sideImage"
				}
		 		, (BiFunction<String,Integer,String>[])new BiFunction[]{
						biFun
				} 
		);
		JSONArray tags = (JSONArray) object.get("specialKeyWord[]");
		Map<String,String> mappingRelation1 = 
				MappingGenerateUtils.generateMappingRelation(new String[] {
						"tagsCode=>bean"
				});
		List<CaseTags> tagsList = new ArrayList<CaseTags>();
		EntityBeanUtils.listBeanCreateFromJson(tagsList, mappingRelation1, tags, CaseTags.class);
		Supplier<Integer> tagsTypeSupplier = () ->{
			return DictionaryConfigParams.CASE_TAGS_TYPE;
		};
		ListUpdateUtils.updateListPropertyWithSupplier(tagsList, new String[]{
				"tagsType"
		}, new Supplier[] {
			tagsTypeSupplier
		});
		boolean updateResult =casesService.createCase(Case, caseAttachments,tagsList);
		if(updateResult) {			
			response.setMessageBody(String.valueOf(Case.getId()));
		}else {
			response.setMessageBody("failed");
		}
		return response;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/application/cases/modify")
	@NeedTokenVerify
	public Message modifyCase(@RequestBody Message message) {
		String dataJson = message.getMessageBody();
		logger.info("received json:"+dataJson);
		Cases casevo = new Cases();
		JSONObject object = (JSONObject) JSONValue.parse(dataJson);
		Map<String,String> mappingRelation = 
				MappingGenerateUtils.generateMappingRelation(new String[] {
						"id=>id",
						"name=>name",
						"activityProvinceCode=>activityProvinceCode",
						"activityCityCode=>activityCityCode",
						"activityDistCode=>activityDistCode",
						"location=>location",
						"activityTypeCode=>activityTypeCode",
						"companyTypeCode=>companyTypeCode",
						"groupNumber=>groupNumber",
						"during=>during",
						"designDuringCode=>designDuringCode",
					    "executeDuringCode=>executeDuringCode",
					    "trafficInfo=>trafficInfo",
					    "foodInfo=>foodInfo",
					    "hotelInfo=>hotelInfo",
					    "priority=>priority",
					    "simulatePriceCode=>simulatePriceCode",
					    "orderId=>orderId",
						"descirbeText1=>_ue",
						"descirbeText2=>mainImage"
				});
		EntityBeanUtils.beanCreateFromJson(casevo, mappingRelation, object);
		List<CaseAttachments> caseAttachments = new ArrayList<CaseAttachments>();
		Map<String,String> mappingRelation0 = 
				MappingGenerateUtils.generateMappingRelation(new String[] {
						"filePath=>bean"
				});
		JSONArray array = (JSONArray) object.get("imgManage[]");		
		EntityBeanUtils.listBeanCreateFromJson(caseAttachments, mappingRelation0, array, CaseAttachments.class);
		Function<String,String> fun = s -> {
			int index = s.lastIndexOf("/");
			return s.substring(index+1);
		} ;
		
		BiFunction<String,Integer,String> biFun = (value,index) -> value+"-"+index ;
		ListUpdateUtils.updateListProperty(caseAttachments, 
				new String[] {
						"filePath=>fileName"
				}, 
				new Function[] {
						fun
				}
				, new String[] {
						"pagePath==sideImage"
				}
		 		, (BiFunction<String,Integer,String>[])new BiFunction[]{
						biFun
				} 
		);
		JSONArray tags = (JSONArray) object.get("specialKeyWord[]");
		Map<String,String> mappingRelation1 = 
				MappingGenerateUtils.generateMappingRelation(new String[] {
						"tagsCode=>bean"
				});
		List<CaseTags> tagsList = new ArrayList<CaseTags>();
		EntityBeanUtils.listBeanCreateFromJson(tagsList, mappingRelation1, tags, CaseTags.class);
		Supplier<Integer> tagsTypeSupplier = () ->{
			return DictionaryConfigParams.CASE_TAGS_TYPE;
		};
		ListUpdateUtils.updateListPropertyWithSupplier(tagsList, new String[]{
				"tagsType"
		}, new Supplier[] {
			tagsTypeSupplier
		});
		SimpleMessageBody updateResult =casesService.modifyCase(casevo, caseAttachments,tagsList);
		message.setMessageBody(JSONValue.toJSONString(updateResult));
		return message;
	}
	
	@RequestMapping("/application/cases/findAll")
	public Message findAll(@RequestBody Message message) {
		String dataJson = message.getMessageBody();
		JSONObject object = (JSONObject) JSONValue.parse(dataJson);
		String pageIndex = object.getAsString("pageIndex");
		Page<Cases> resultPage =casesService.findAllCasesPageable(Integer.parseInt(pageIndex));
		Map<String,Dictionary> dictionnaryMap = EntityDictionaryConfigUtils.getDictionaryMaping(new Cases());
		dictionaryService.dictionaryFilter(resultPage.getContent(), dictionnaryMap);
		message.setMessageBody(PageDataWrapUtils.page2Json(resultPage));
		return message;		
	}
	
	@RequestMapping("/application/cases/findByExample")
	public Message findByExample(@RequestBody Message message) {
		String dataJson = message.getMessageBody();
		PageDomianRequest example = JSONUtils.jsonToObj(dataJson, PageDomianRequest.class);
		Page<Cases> resultPage = null ;
		if(!example.getObj().containsKey("*")) {
			resultPage =casesService.queryCaseByConditionPageable(example.getPageIndex(), example.getObjAsType(Cases.class));
		}else {
			String value = (String) example.getObj().get("*");
			Cases defaultLocation = new Cases();
			defaultLocation.setName(value);
			defaultLocation.setLocation(value);
			String[] defaultSpecification = new String[] {
					"like,name,or",
					"like,location,or",
			};
			Specification<Cases> specification = JPADBUtils.generateSpecificationFromExample(defaultLocation, defaultSpecification);
			resultPage = casesService.queryCaseBySpecificationPageable(example.getPageIndex(), specification);
		}
		
		message.setMessageBody(PageDataWrapUtils.page2Json(resultPage));
		return message;		
	}
	
	@RequestMapping("/application/cases/delete")
	@NeedTokenVerify
	public Message delete(@RequestBody Message message) {
		String id = message.getMessageBody();
		String result = casesService.delete(id);
		message.setMessageBody(String.valueOf(result));
		return message ;
	}
	
	@RequestMapping("/application/cases/get")
	public Message get(@RequestBody Message message) {
		String jsonId = message.getMessageBody();
		JSONObject objectId = (JSONObject) JSONValue.parse(jsonId);
		Integer id = Integer.parseInt(objectId.getAsString("id"));
		String result = casesService.get(id);
		message.setMessageBody(result);
		return message;
	}
	@RequestMapping("/application/cases/queryCaseByCondition")
	public Message queryCaseByCondition(@RequestBody Message message) {
		String dataJson = message.getMessageBody();
		SearchDictionaryList queryVo =JSONUtils.jsonToObj(dataJson, SearchDictionaryList.class);
		String json = caseListQuery(queryVo);
		message.setMessageBody(json);
		return message;		
	}
	public String caseListQuery(SearchDictionaryList queryVo) {
		if( queryVo != null ) {
			SearchDictionaryList vo = new SearchDictionaryList();
			List<String> activityProvinceCode = new ArrayList<>();
			List<String> activitytype = new ArrayList<>();
			List<String> companytype = new ArrayList<>();
			List<Integer> duration = new ArrayList<>();
			List<String> personNum = new ArrayList<>();
			List<String> activityProvinceCodeArray = queryVo.getActivityProvinceCode();
			if(activityProvinceCodeArray != null && activityProvinceCodeArray.size() > 0) {
				activityProvinceCode = dictionaryService.dictionaryParamSwitchString(activityProvinceCodeArray);
				vo.setActivityProvinceCode(activityProvinceCode);
			}
			List<String> activitytypeArray = queryVo.getActivitytype();
			if(activitytypeArray != null && activitytypeArray.size() > 0) {
				activitytype = dictionaryService.dictionaryParamSwitchString(activitytypeArray);
				vo.setActivitytype(activitytype);
			}
			List<String> companytypeArray = queryVo.getCompanytype();
			if(companytypeArray != null && companytypeArray.size() > 0) {
				companytype = dictionaryService.dictionaryParamSwitchString(companytypeArray);
				vo.setCompanytype(companytype);
			}
			List<Integer> durationArray = queryVo.getDuration();
			if(durationArray != null && durationArray.size() > 0) {
				duration = dictionaryService.dictionaryParamSwitch(durationArray);
				vo.setDuration(duration);
			}
			List<String> personNumArray = queryVo.getPersonNum();
			if(personNumArray != null && personNumArray.size() > 0) {
				personNum = dictionaryService.dictionaryParamSwitchString(personNumArray);
				vo.setPersonNum(personNum);
			}
			PageImpl<Cases> result = casesService.queryCaseByDictListConditionPageable( vo, queryVo.getPageIndex());
			Map<String,Dictionary> dictionnaryMap = EntityDictionaryConfigUtils.getDictionaryMaping(new Cases());
			dictionaryService.dictionaryFilter(result.getContent(), dictionnaryMap);
			return PageDataWrapUtils.page2JsonNoCopy(result);
		}
		return "";
		
	} 
	
}
