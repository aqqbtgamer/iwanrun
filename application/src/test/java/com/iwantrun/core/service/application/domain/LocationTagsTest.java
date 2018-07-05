package com.iwantrun.core.service.application.domain;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

public class LocationTagsTest {

	@Test
	public void testPropertyUtils() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		LocationTags tags = new LocationTags();
		//OrderHistory history = new OrderHistory();
		PropertyUtils.setProperty(tags, "tagsType", 1);
		//PropertyUtils.setProperty(history, "orderId", 1);
		/*PropertyDescriptor[] desc = PropertyUtils.getPropertyDescriptors(LocationTags.class);
		for(PropertyDescriptor descri : desc) {
			try {
				System.out.println(descri.getName());
				System.out.println(descri.getReadMethod().getName());
				System.out.println(descri.getWriteMethod().getName());
			}catch(Exception e) {
				
			}
			
		}*/
		//BeanUtils.setProperty(tags, "tagsType", 2);
		//tags.setTagsType(2);
		System.out.println(tags.getTagsType());
	}

}
