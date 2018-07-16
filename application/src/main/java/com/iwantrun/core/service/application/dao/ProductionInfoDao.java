package com.iwantrun.core.service.application.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iwantrun.core.service.application.domain.Cases;
import com.iwantrun.core.service.application.domain.Locations;
import com.iwantrun.core.service.application.domain.ProductionInfo;
import com.iwantrun.core.service.application.domain.SearchDictionaryList;

@Repository
public interface ProductionInfoDao
		extends JpaRepository<ProductionInfo, Integer>, JpaSpecificationExecutor<ProductionInfo> {
	
	@Modifying
	@Query("update ProductionInfo d set d.status = ?1 where d.id = ?2")
	int updateStatusById(int status, int id);
	
	String QUERY_CASE_ALL = "select p from ProductionInfo p where 1=1";
	
	String QUERY_CASE_ALL_COUNT="select count(*) from ProductionInfo p where 1=1";
	
	String SORE_BY = "  order by p.priority desc ";
	 default List<ProductionInfo> findByMutipleParams(SearchDictionaryList vo,JPQLEnableRepository repository,int pageSize,int pageIndex){
		 List<ProductionInfo> list = new ArrayList<ProductionInfo>();
		 String queryJPQL = "" ;
		 queryJPQL = mutipleParams(vo,QUERY_CASE_ALL);
		 queryJPQL = queryJPQL.concat(SORE_BY);
		 list = repository.findByJPQLPage(queryJPQL, ProductionInfo.class, pageIndex, pageSize);
		return list;
	}
	 default Long countByMutipleParams(SearchDictionaryList vo,JPQLEnableRepository repository) {
		 String queryJPQL = "" ;
		 queryJPQL = mutipleParams(vo,QUERY_CASE_ALL_COUNT);
		 return repository.findOneJPQL(queryJPQL, Long.class);
	 }
	 default String mutipleParams(SearchDictionaryList vo,String queryJPQL) {
		    List<String> activityProvinceCode = vo.getActivityProvinceCode();
			List<String> activitytype = vo.getActivitytype();
			List<Integer> duration = vo.getDuration();
			List<String> personNum = vo.getPersonNum();
			List<Integer> specialTagsCode = vo.getSpecialTagsCode();
			List<Integer> orderSimulatePriceCode = vo.getOrderSimulatePriceCode();
		    if(activityProvinceCode != null && activityProvinceCode.size() > 0) {
				String activityProvinceCodeString="";
				for(String acp : activityProvinceCode) {
					activityProvinceCodeString += "'"+ acp + "',";
				}
				activityProvinceCodeString = activityProvinceCodeString.substring(0, activityProvinceCodeString.length()-1);
				queryJPQL = queryJPQL.concat(" and p.activityProvinceCode in ("+activityProvinceCodeString+")");
			 }
			 if(activitytype != null && activitytype.size() > 0) {
					String activitytypeString="";
					for(String act : activitytype) {
						activitytypeString += "'"+ act + "',";
					}
					activitytypeString = activitytypeString.substring(0, activitytypeString.length()-1);
					queryJPQL = queryJPQL.concat(" and p.activityTypeCode in ("+activitytypeString+")");
				 }
			
			 if(duration != null && duration.size() > 0) {
					String durationString="";
					for(Integer dur : duration) {
						durationString += ""+ dur + ",";
					}
					durationString = durationString.substring(0, durationString.length()-1);
					queryJPQL = queryJPQL.concat(" and p.during in ("+durationString+")");
				 }
			 if(personNum != null && personNum.size() > 0) {
					String personNumString="";
					for(String person : personNum) {
						personNumString += "'"+ person + "',";
					}
					personNumString = personNumString.substring(0, personNumString.length()-1);
					queryJPQL = queryJPQL.concat(" and p.groupNumber in ("+personNumString+")");
			 }
			 if(specialTagsCode != null && specialTagsCode.size() > 0) {
					String specialTagsCodeString="";
					for(Integer sp : specialTagsCode) {
						specialTagsCodeString += ""+ sp + ",";
					}
					specialTagsCodeString = specialTagsCodeString.substring(0, specialTagsCodeString.length()-1);
					queryJPQL = queryJPQL.concat(" and p.id in ("+specialTagsCodeString+")");
			 }
			 if(orderSimulatePriceCode != null && orderSimulatePriceCode.size() > 0) {
					String orderSimulatePriceCodeString="";
					for(Integer loc : orderSimulatePriceCode) {
						orderSimulatePriceCodeString += ""+ loc + ",";
					}
					orderSimulatePriceCodeString = orderSimulatePriceCodeString.substring(0, orderSimulatePriceCodeString.length()-1);
					queryJPQL = queryJPQL.concat(" and p.orderSimulatePriceCode in ("+orderSimulatePriceCodeString+")");
			 }
			 return  queryJPQL ;
	 }
}
