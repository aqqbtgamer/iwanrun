package com.iwantrun.core.service.application.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.iwantrun.core.service.application.domain.Cases;
import com.iwantrun.core.service.application.domain.Locations;
@Repository
public interface LocationsDao  extends JpaRepository<Locations, Integer>,JpaSpecificationExecutor<Locations> {
	
String QUERY_CASE_ALL = "select loc from Locations loc where 1=1";
	
	String QUERY_CASE_ALL_COUNT="select count(*) from Locations loc where 1=1";
	
	String SORE_BY = "  order by loc.id asc ";
	
	 default List<Locations> findByMutipleParams(List<String> activityProvinceCode,List<String> activitytype,List<Integer> duration,List<String> personNum,List<String> specialTagsCode,List<String> locationTypeCode,JPQLEnableRepository repository,int pageSize,int pageIndex){
		 List<Locations> list = new ArrayList<Locations>();
		 String queryJPQL = "" ;
		 queryJPQL = mutipleParams(activityProvinceCode,activitytype,duration,personNum,specialTagsCode,locationTypeCode,QUERY_CASE_ALL);
		 queryJPQL = queryJPQL.concat(SORE_BY);
		 list = repository.findByJPQLPage(queryJPQL, Locations.class, pageIndex, pageSize);
		return list;
	}
	 default Long countByMutipleParams(List<String> activityProvinceCode,List<String> activitytype,List<Integer> duration,List<String> personNum,List<String> specialTagsCode,List<String> locationTypeCode,JPQLEnableRepository repository) {
		 String queryJPQL = "" ;
		 queryJPQL = mutipleParams(activityProvinceCode,activitytype,duration,personNum,specialTagsCode,locationTypeCode,QUERY_CASE_ALL_COUNT);
		 return repository.findOneJPQL(queryJPQL, Long.class);
	 }
	 default String mutipleParams(List<String> activityProvinceCode,List<String> activitytype,List<Integer> duration,List<String> personNum,List<String> specialTagsCode,List<String> locationTypeCode,String queryJPQL) {
		    if(activityProvinceCode != null && activityProvinceCode.size() > 0) {
				String activityProvinceCodeString="";
				for(String acp : activityProvinceCode) {
					activityProvinceCodeString = "'"+ acp + "',";
				}
				activityProvinceCodeString = activityProvinceCodeString.substring(0, activityProvinceCodeString.length()-1);
				queryJPQL = queryJPQL.concat(" and loc.activityProvinceCode in ("+activityProvinceCodeString+")");
			 }
			 if(activitytype != null && activitytype.size() > 0) {
					String activitytypeString="";
					for(String act : activitytype) {
						activitytypeString += "'"+ act + "',";
					}
					activitytypeString = activitytypeString.substring(0, activitytypeString.length()-1);
					queryJPQL = queryJPQL.concat(" and loc.activeTypeCode in ("+activitytypeString+")");
				 }
			
			 if(duration != null && duration.size() > 0) {
					String durationString="";
					for(Integer dur : duration) {
						durationString += ""+ dur + ",";
					}
					durationString = durationString.substring(0, durationString.length()-1);
					queryJPQL = queryJPQL.concat(" and loc.during in ("+durationString+")");
				 }
			 if(personNum != null && personNum.size() > 0) {
					String personNumString="";
					for(String person : personNum) {
						personNumString += "'"+ person + "',";
					}
					personNumString = personNumString.substring(0, personNumString.length()-1);
					queryJPQL = queryJPQL.concat(" and loc.groupNumberLimitCode in ("+personNumString+")");
			 }
			 if(specialTagsCode != null && specialTagsCode.size() > 0) {
					String specialTagsCodeString="";
					for(String sp : specialTagsCode) {
						specialTagsCodeString += "'"+ sp + "',";
					}
					specialTagsCodeString = specialTagsCodeString.substring(0, specialTagsCodeString.length()-1);
					queryJPQL = queryJPQL.concat(" and loc.specialTagsCode in ("+specialTagsCodeString+")");
			 }
			 if(locationTypeCode != null && locationTypeCode.size() > 0) {
					String locationTypeCodeString="";
					for(String loc : locationTypeCode) {
						locationTypeCodeString += "'"+ loc + "',";
					}
					locationTypeCodeString = locationTypeCodeString.substring(0, locationTypeCodeString.length()-1);
					queryJPQL = queryJPQL.concat(" and loc.locationTypeCode in ("+locationTypeCodeString+")");
			 }
			 return  queryJPQL ;
	 }
}
