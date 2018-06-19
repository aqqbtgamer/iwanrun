package com.iwantrun.core.service.application.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface DictionaryField {
	
	String name();
	
	int usedField();
	
	String aliasField() default "" ;
}
