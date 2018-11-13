package com.iwantrun.core.service.application.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.iwantrun.core.constant.AdminApplicationConstants;
import com.iwantrun.core.service.application.dao.CaseLocationRelationDao;
import com.iwantrun.core.service.application.dao.JPQLEnableRepository;
import com.iwantrun.core.service.application.dao.ProductionCaseRelationDao;
import com.iwantrun.core.service.application.dao.ProductionLocationRelationDao;
import com.iwantrun.core.service.application.domain.CaseLocationRelation;
import com.iwantrun.core.service.application.domain.Cases;
import com.iwantrun.core.service.application.domain.Dictionary;
import com.iwantrun.core.service.application.domain.Locations;
import com.iwantrun.core.service.application.domain.OrderMessage;
import com.iwantrun.core.service.application.domain.ProductionCaseRelation;
import com.iwantrun.core.service.application.domain.ProductionInfo;
import com.iwantrun.core.service.application.domain.ProductionLocationRelation;
import com.iwantrun.core.service.utils.EntityDictionaryConfigUtils;
import com.iwantrun.core.service.utils.JSONUtils;
import com.iwantrun.core.service.utils.PageDataWrapUtils;
import com.iwantrun.core.service.utils.StringFilterUtils;

@Service
public class RelationService {
	
	Logger logger = LoggerFactory.getLogger(RelationService.class);
		
	@Autowired
	private CaseLocationRelationDao caseLocationRelationDao ;	
	
	@Autowired
	private ProductionCaseRelationDao productionCaseRelationDao ; 	
	
	@Autowired
	private ProductionLocationRelationDao productionLocationRelationDao ;	
	
	@Autowired
	private JPQLEnableRepository sqlExecute ;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private Environment environment;
	
	public CaseLocationRelationDao getCaseLocationRelationDao() {
		return caseLocationRelationDao;
	}

	public ProductionCaseRelationDao getProductionCaseRelationDao() {
		return productionCaseRelationDao;
	}

	public ProductionLocationRelationDao getProductionLocationRelationDao() {
		return productionLocationRelationDao;
	}



	private final Map<String,Integer> relationtypeMap = new HashMap<String,Integer>(10);
	
	private final Map<Integer,String> relationDaoMap = new HashMap<Integer,String>(10);
	
	private final Map<String,String> entityMap = new HashMap<String,String>(10);
	
	private final Map<String,Class<?>> entityClassMap = new HashMap<String,Class<?>>(10);
	
	public RelationService() {
		relationtypeMap.put("location", 1);
		relationtypeMap.put("case", 2);
		relationtypeMap.put("production", 5);
		relationDaoMap.put(3, "caseLocationRelationDao");
		relationDaoMap.put(6, "productionLocationRelationDao");
		relationDaoMap.put(7, "productionCaseRelationDao");
		entityMap.put("location", "locations");
		entityMap.put("case", "cases");
		entityMap.put("production", "ProductionInfo");
		entityClassMap.put("location", Locations.class);
		entityClassMap.put("case", Cases.class);
		entityClassMap.put("production", ProductionInfo.class);
	}
	
	
	public String addRelation(String inputJson) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Map<String,Object> paramMap = JSONUtils.toMap(inputJson, Object.class);
		String oprationId = (String) paramMap.get("oprationId");
		String type = (String) paramMap.get("type");
		String target = (String) paramMap.get("target");
		@SuppressWarnings("unchecked")
		ArrayList<String> targetIds = (ArrayList<String>) paramMap.get("targetIds");
		Object dao = indentifyRelationDao(type,target);
		if(dao != null) {			
			Object[] entities = indentifyEntity(type,target,oprationId,targetIds);	
			if(entities != null && entities.length > 0) {
				List<?> dbEntities = queryDbBindingRelations(type,oprationId,dao);			
				List<?> needAddEntites = Arrays.asList(entities).stream().filter(t -> !dbEntities.contains(t)).collect(Collectors.toList());
				if(needAddEntites != null && needAddEntites.size() > 0) {
					Object resultObj = invokeDao(dao,"saveAll",new Object[]{needAddEntites},false);
					if(resultObj == null) {
						return AdminApplicationConstants.Add_FAIL_RESULT;
					}else {
						return AdminApplicationConstants.Add_SUCCESS_RESULT ;
					}					
				}else {
					return AdminApplicationConstants.Add_SUCCESS_RESULT ;
				}
			}else {
				return AdminApplicationConstants.Add_SUCCESS_RESULT ;
			}
		}else {
			return AdminApplicationConstants.Add_FAIL_RESULT ;
		}		
	}
	
	
	
	public String queryRelations(String inputJson) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Map<String,Object> paramMap = JSONUtils.toMap(inputJson, Object.class);
		String oprationId = (String) paramMap.get("oprationId");
		String type = (String) paramMap.get("type");
		String pageIndex = (String) paramMap.get("pageIndex");
		String target = (String) paramMap.get("target");
		String pageSize  = environment.getProperty("common.pageSize");
		String jpql = generateRelationEntityJPQL(oprationId,type,target);	
		String jpqlCount = generateRelationEntityCountJPQL(oprationId,type,target);		 
		List<?> bindingList = sqlExecute.findByJPQLPage(jpql, entityClassMap.get(target), Integer.parseInt(pageIndex)+1, Integer.parseInt(pageSize));
		Long total = sqlExecute.findOneJPQL(jpqlCount, Long.class);
		Pageable page =  PageRequest.of(Integer.parseInt(pageIndex), Integer.parseInt(pageSize), Sort.Direction.ASC, "id");
		@SuppressWarnings({ "unchecked", "rawtypes" })
		PageImpl<?> bindingPage = new PageImpl(bindingList,page,total);
		Map<String,Dictionary> dictionnaryMap = EntityDictionaryConfigUtils.getDictionaryMaping(bindingList.get(0));
		dictionaryService.dictionaryFilter(bindingPage.getContent(), dictionnaryMap);
		return PageDataWrapUtils.page2Json(bindingPage);
	}
	
	
	public String deleteBindings(String inputJson) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Map<String,Object> paramMap = JSONUtils.toMap(inputJson, Object.class);
		String oprationId = (String) paramMap.get("oprationId");
		String typeId = (String) paramMap.get("typeId");
		String type = (String) paramMap.get("type");
		String target = (String) paramMap.get("target");
		Object dao = indentifyRelationDao(type,target);
		List<?> relations = queryDbBindingRelations(type,typeId,target,oprationId,dao);
		//deleteInBatch
		return invokeDao(dao,"deleteInBatch",new Object[] {relations},false).toString();		
	}
	
	
	private Object indentifyRelationDao(String type,String target) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Integer val1 = relationtypeMap.get(type);
		Integer val2 = relationtypeMap.get(target);
		if(val1 != null && val2 != null) {
			String daoName = (String) relationDaoMap.get(val1+val2);
			return PropertyUtils.getProperty(this, daoName);
		}else {
			return null ;
		}
	}
	
	
	private List<?> queryDbBindingRelations(String type,String oprationId,Object dao) {
		return (List<?>)invokeDao(dao,"getBy"+StringFilterUtils.firstCapString(type)+"Id",new Object[]{Integer.parseInt(oprationId)},true);
	}
	
	private List<?> queryDbBindingRelations(String type,String typeId,String target, String oprationId, Object dao){
		return (List<?>)invokeDao(dao,"getBy"+StringFilterUtils.firstCapString(type)+"IdAnd"+StringFilterUtils.firstCapString(target)+"Id",new Object[]{Integer.parseInt(typeId),Integer.parseInt(oprationId)},true);
	}
	
	private Object invokeDao(Object dao , String method,Object[] params,boolean query) {
		if(dao != null && method != null && params != null) {			
			try {
				Method[] methods = dao.getClass().getMethods();
				for(Method m : methods) {
					if(m.getName().equals(method)) {
						if(query) {
							return m.invoke(dao, params);
						}else {
							m.invoke(dao, params);
							return AdminApplicationConstants.Add_SUCCESS_RESULT;
						}						
					}
				}				
				return AdminApplicationConstants.Add_FAIL_RESULT ;
			} catch (SecurityException e) {				
				logger.error("invoker dao method error",e);
				return null ;
			} catch (IllegalAccessException e) {
				logger.error("invoker dao method error",e);
				return null ;
			} catch (IllegalArgumentException e) {
				logger.error("invoker dao method error",e);
				return null ;
			} catch (InvocationTargetException e) {
				logger.error("invoker dao method error",e);
				return null ;
			}
		}else {
			return null ;
		}
	}
	
	private Object[] indentifyEntity(String type, String target,String oprationId,List<String> targetIds) {
		if(type != null && target != null && oprationId!= null && targetIds!= null) {
			Integer val1 = relationtypeMap.get(type);
			Integer val2 = relationtypeMap.get(target);
			Object[] resultArray = null;
			switch(val1+val2) {
				case 3 :
					CaseLocationRelation[] entitys3 = new CaseLocationRelation[targetIds.size()];
					for(int i = 0 ; i<targetIds.size() ; i++) {
						entitys3[i] = new CaseLocationRelation();
						if(val1 == 1) {
							entitys3[i].setCaseId(Integer.parseInt(targetIds.get(i)));
							entitys3[i].setLocationId(Integer.parseInt(oprationId));
						}else {
							entitys3[i].setCaseId(Integer.parseInt(oprationId));
							entitys3[i].setLocationId(Integer.parseInt(targetIds.get(i)));
						}						
					}
					resultArray = entitys3 ;
					break;
				case 6 :
					ProductionLocationRelation[] entitys6 = new ProductionLocationRelation[targetIds.size()];
					for(int i = 0 ; i<targetIds.size() ; i++) {
						entitys6[i] = new ProductionLocationRelation();
						if(val1 == 1) {
							entitys6[i].setProductionId(Integer.parseInt(targetIds.get(i)));
							entitys6[i].setLocationId(Integer.parseInt(oprationId));
						}else {
							entitys6[i].setProductionId(Integer.parseInt(oprationId));
							entitys6[i].setLocationId(Integer.parseInt(targetIds.get(i)));
						}						
					}
					resultArray = entitys6 ;
					break;
				case 7 :
					ProductionCaseRelation[]  entitys7 = new ProductionCaseRelation[targetIds.size()];
					for(int i = 0 ; i<targetIds.size() ; i++) {
						entitys7[i] = new ProductionCaseRelation();
						if(val1 == 2) {
							entitys7[i].setProductionId(Integer.parseInt(targetIds.get(i)));
							entitys7[i].setCaseId(Integer.parseInt(oprationId));
						}else {
							entitys7[i].setProductionId(Integer.parseInt(oprationId));
							entitys7[i].setCaseId(Integer.parseInt(targetIds.get(i)));
						}						
					}
					resultArray = entitys7 ;
					break;
			}			
			return resultArray ;
		}else {
			return null ;
		}
	}
	
	
	private String generateRelationEntityJPQL(String oprationId , String type,String target) {
		StringBuilder jpql = new StringBuilder().append("select ") ;
		String relationEntityClassName = entityMap.get(target);
		//1. select entity.* Entity from Entity entity  
		jpql.append(relationEntityClassName).append(" from ")
		.append(StringFilterUtils.firstCapString(relationEntityClassName))
		.append(" ").append(relationEntityClassName);
		//2. inner join RelationEntityName relationEntityName on entity.id = relationEntityName.typeId
		jpql.append(" inner join ");
		String relationEntityDaoName = relationDaoMap.get(relationtypeMap.get(type) + relationtypeMap.get(target));
		String relationEntityName = relationEntityDaoName.substring(0, relationEntityDaoName.indexOf("Dao"));
		jpql.append(StringFilterUtils.firstCapString(relationEntityName)).append(" ").append(relationEntityName)
		.append(" on ").append(relationEntityClassName).append(".id = ").append(relationEntityName).append(".")
		.append(target).append("Id");
		//3. and entity.id = oprationId
		jpql.append(" and ").append(relationEntityName).append(".").append(type).append("Id = ").append(oprationId);
		return jpql.toString();
	}
	
	private String generateRelationEntityCountJPQL(String oprationId , String type,String target) {
		StringBuilder jpql = new StringBuilder().append("select ") ;
		String relationEntityClassName = entityMap.get(target);
		//1. select count(*) from Entity entity  
		jpql.append(" count(*)").append(" from ")
		.append(StringFilterUtils.firstCapString(relationEntityClassName))
		.append(" ").append(relationEntityClassName);
		//2. inner join RelationEntityName relationEntityName on entity.id = relationEntityName.typeId
		jpql.append(" inner join ");
		String relationEntityDaoName = relationDaoMap.get(relationtypeMap.get(type) +relationtypeMap.get(target));
		String relationEntityName = relationEntityDaoName.substring(0, relationEntityDaoName.indexOf("Dao"));
		jpql.append(StringFilterUtils.firstCapString(relationEntityName)).append(" ").append(relationEntityName)
		.append(" on ").append(relationEntityClassName).append(".id = ").append(relationEntityName).append(".")
		.append(type).append("Id");
		//3. and entity.id = oprationId
		jpql.append(" and ").append(relationEntityName).append(".").append(type).append("Id = ").append(oprationId);
		return jpql.toString();
	}

	
	
	
}
