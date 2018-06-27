package com.iwantrun.core.service.application.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.iwantrun.core.service.application.domain.Cases;
import com.iwantrun.core.service.application.domain.Locations;
import com.iwantrun.core.service.application.domain.PurchaserAccount;
import com.iwantrun.core.service.application.domain.SearchDictionaryList;
import com.iwantrun.core.service.application.transfer.MixedUserResponse;
import com.mysql.jdbc.StringUtils;
@Repository
public interface CasesDao  extends JpaRepository<Cases, Integer>,JpaSpecificationExecutor<Cases> {
	
	String QUERY_CASE_ALL = "select c from Cases c where 1=1";
	
	String QUERY_CASE_ALL_COUNT="select count(*) from Cases c where 1=1";
	
	String SORE_BY = "  order by c.id asc ";
	
	 default List<Cases> findByMutipleParams(SearchDictionaryList vo,JPQLEnableRepository repository,int pageSize,int pageIndex){
		 List<Cases> list = new ArrayList<Cases>();
		 String queryJPQL = "" ;
		 queryJPQL = mutipleParams(vo,QUERY_CASE_ALL);
		 queryJPQL = queryJPQL.concat(SORE_BY);
		 list = repository.findByJPQLPage(queryJPQL, Cases.class, pageIndex, pageSize);
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
			List<String> companytype = vo.getCompanytype();
			List<Integer> duration = vo.getDuration();
			List<String> personNum = vo.getPersonNum();
		    if(activityProvinceCode != null && activityProvinceCode.size() > 0) {
				String activityProvinceCodeString="";
				for(String acp : activityProvinceCode) {
					activityProvinceCodeString += "'"+ acp + "',";
				}
				activityProvinceCodeString = activityProvinceCodeString.substring(0, activityProvinceCodeString.length()-1);
				queryJPQL = queryJPQL.concat(" and c.activityProvinceCode in ("+activityProvinceCodeString+")");
			 }
			 if(activitytype != null && activitytype.size() > 0) {
					String activitytypeString="";
					for(String act : activitytype) {
						activitytypeString += "'"+ act + "',";
					}
					activitytypeString = activitytypeString.substring(0, activitytypeString.length()-1);
					queryJPQL = queryJPQL.concat(" and c.activityCityCode in ("+activitytypeString+")");
				 }
			 if(companytype != null && companytype.size() > 0) {
					String companytypeString="";
					for(String comp : companytype) {
						companytypeString += "'"+ comp + "',";
					}
					companytypeString = companytypeString.substring(0, companytypeString.length()-1);
					queryJPQL = queryJPQL.concat(" and c.companyTypeCode in ("+companytypeString+")");
				 }
			 if(duration != null && duration.size() > 0) {
					String durationString="";
					for(Integer dur : duration) {
						durationString += ""+ dur + ",";
					}
					durationString = durationString.substring(0, durationString.length()-1);
					queryJPQL = queryJPQL.concat(" and c.during in ("+durationString+")");
				 }
			 if(personNum != null && personNum.size() > 0) {
					String personNumString="";
					for(String person : personNum) {
						personNumString += "'"+ person + "',";
					}
					personNumString = personNumString.substring(0, personNumString.length()-1);
					queryJPQL = queryJPQL.concat(" and c.groupNumber in ("+personNumString+")");
			 }
			 return  queryJPQL ;
	 }
}
