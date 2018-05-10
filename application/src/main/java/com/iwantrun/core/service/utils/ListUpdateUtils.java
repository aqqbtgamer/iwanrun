package com.iwantrun.core.service.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ListUpdateUtils {
	static Logger logger = LoggerFactory.getLogger(ListUpdateUtils.class);
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> updateListProprtyFromAnthoer(List<T> tList, String copyName,String copy2Name,@SuppressWarnings("rawtypes") Function fun) {
		if(tList == null || copyName == null || copy2Name ==null) {
			return tList;
		}else {
			for(T t : tList) {
				try {
					Object propertyCopy = BeanUtils.getProperty(t, copyName);
					if(fun != null) {
						BeanUtils.setProperty(t, copy2Name, fun.apply(propertyCopy));
					}else {
						BeanUtils.setProperty(t, copy2Name, propertyCopy);
					}
				} catch (IllegalAccessException e) {
					logger.error("list update failed",e);
				} catch (InvocationTargetException e) {
					logger.error("list update failed",e);
				} catch (NoSuchMethodException e) {
					logger.error("list update failed",e);
				}
								
			}
			return tList;
		}
	}
	
	
	public static <T> List<T> updateListPropertyWithValueFunction(List<T> tList ,String propertyName, String value,BiFunction<String,Integer,String> fun){
		if(tList == null || propertyName ==null ) {
			return tList ;
		}else {
			for(int i = 0 ; i< tList.size() ; i++) {
				T t = tList.get(i);
				String valueChange = value;
				if(fun != null) {
					valueChange = fun.apply(value, i);
				}
				try {
					BeanUtils.setProperty(t, propertyName, valueChange);
				} catch (IllegalAccessException e) {
					logger.error("list update failed",e);
				} catch (InvocationTargetException e) {
					logger.error("list update failed",e);
				}
			}
		}
		return tList;
	}
	
	public static <T> List<T> updateListPropertyWithSupplier(List<T> tList , String[] propertyNames, Supplier<Object>[] supliers){
		if(tList == null || propertyNames == null || supliers == null || propertyNames.length != supliers.length) {
			
		}else {
			for(T t : tList) {
				for(int i = 0; i< propertyNames.length ; i++) {
					try {
						PropertyUtils.setSimpleProperty(t, propertyNames[i], supliers[i].get());
					} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
						logger.error("copy property的时候出错  具体参考statck ",e );
					}
				}
			}
		}
		return tList ;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> List<T> updateListProperty(List<T> tList ,String[] copyConfig,Function[] copyFunctions, String[] valueConfig,BiFunction<String,Integer,String>[] valueFunctions){
		if(tList == null) {
			return tList ;
		}
		if(copyConfig != null || valueConfig != null) {
			int index = 0 ;
			for(T t: tList) {
				if(copyConfig != null) {
					for(int i=0; i<copyConfig.length ; i++) {
						String config = copyConfig[i];
						String[] entry = config.split("=>");
						try {
							Object propertyCopy = BeanUtils.getProperty(t, entry[0].trim());
							if(copyFunctions[i] != null) {
								BeanUtils.setProperty(t, entry[1].trim(), copyFunctions[i].apply(propertyCopy));
							}else {
								BeanUtils.setProperty(t, entry[1].trim(), propertyCopy);
							}
						} catch (IllegalAccessException e) {
							logger.error("list update failed",e);
						} catch (InvocationTargetException e) {
							logger.error("list update failed",e);
						} catch (NoSuchMethodException e) {
							logger.error("list update failed",e);
						}
					}
				}
				if(valueConfig != null) {
					for(int i=0; i<valueConfig.length ; i++) {
						String config = valueConfig[i];
						String[] entry = config.split("==");
						String valueChange = entry[1];
						if(valueFunctions[i] != null) {
							valueChange = valueFunctions[i].apply(valueChange, index);
						}
						try {
							BeanUtils.setProperty(t, entry[0], valueChange);
						} catch (IllegalAccessException e) {
							logger.error("list update failed",e);
						} catch (InvocationTargetException e) {
							logger.error("list update failed",e);
						}
					}
				}
				index ++ ;
			}
			
		}
		return tList;
	}
	

}
