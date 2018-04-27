package com.iwantrun.core.service.utils;

import java.lang.reflect.InvocationTargetException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import com.iwantrun.core.service.application.enums.SQLConditions;

public class JPADBUtils {
	static Logger logger = LoggerFactory.getLogger(JPADBUtils.class);
	
	public static <T> Specification<T> generateSpecificationFromExample(T t, String[] conditions){
		Specification<T> specify = new Specification<T>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.isNotNull(root.get("id").as(Integer.class));
				int index = 0 ;
				Predicate previous = null ;
				for(String condition : conditions) {
					String[] opArray = condition.split(",");
					SQLConditions opration = SQLConditions.matchSymbol(opArray[0]);
					String propertyName = opArray[1];
					SQLConditions connector = SQLConditions.matchSymbol(opArray[2]);					
					try {
						if(preCheck(PropertyUtils.getSimpleProperty(t, propertyName))) {
							Predicate customerPre = generatepredicateExample(criteriaBuilder,t,root,propertyName,opration);
							if(index ++ > 0) {
								previous  = connectPredicate(previous,customerPre, criteriaBuilder, connector);
							}else {
								previous = customerPre ;
							}							
						}						
					} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
						logger.error("生成条件查询失败",e);
					}
				}
				return criteriaBuilder.and(predicate,previous);
			}
			
		};
		return specify ;				
	}
	
	private static boolean preCheck(Object property) {
		if(property == null || property.equals(Integer.valueOf(0)) ) {
			return false ;
		}else {
			return true ;
		}
	}

	private static <T> Predicate generatepredicateExample(CriteriaBuilder criteriaBuilder,T t,Root<T> root, String propertyName,SQLConditions opration) 
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Predicate perdicate = null ;
		if(criteriaBuilder == null || t == null || root == null || propertyName == null || opration == null) {
			return perdicate;
		}else {			
			switch(opration) {
			case Equals:
				perdicate = criteriaBuilder.equal(root.get(propertyName).as(PropertyUtils.getPropertyType(t, propertyName)), PropertyUtils.getSimpleProperty(t, propertyName));
				break;
			case NotEquals:
				perdicate = criteriaBuilder.notEqual(root.get(propertyName).as(PropertyUtils.getPropertyType(t, propertyName)), PropertyUtils.getSimpleProperty(t, propertyName));
				break;
			case Greater:
				perdicate = criteriaBuilder.greaterThanOrEqualTo(root.get(propertyName), BeanUtils.getProperty(t, propertyName));
				break;
			case GreaterThan:
				perdicate = criteriaBuilder.greaterThan(root.get(propertyName), BeanUtils.getProperty(t, propertyName));
				break;
			case Less:
				perdicate = criteriaBuilder.lessThanOrEqualTo(root.get(propertyName), BeanUtils.getProperty(t, propertyName));
				break;
			case LessThan:
				perdicate = criteriaBuilder.lessThan(root.get(propertyName), BeanUtils.getProperty(t, propertyName));
				break;
			case Like:
				perdicate = criteriaBuilder.like(root.get(propertyName), "%"+BeanUtils.getProperty(t, propertyName)+"%");
				break;
			case NotNull:
				perdicate = criteriaBuilder.isNotNull(root.get(propertyName));
				break;
			case Null:
				perdicate = criteriaBuilder.isNull(root.get(propertyName));
				break;
			default:
				break;
			}
			return perdicate;
		}
				
	}
	
	
	private static Predicate connectPredicate(Predicate pre, Predicate cur,CriteriaBuilder builder,SQLConditions condition) {
		if(pre == null || cur == null || builder == null ||condition == null) {
			return pre ;
		}else {
			switch(condition) {
			case And :
				return builder.and(pre,cur);
			case Or :
				return builder.or(pre,cur);
			default:
				return pre;
			}
		}
	}
	

}
	
	


