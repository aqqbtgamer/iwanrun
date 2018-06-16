package com.iwantrun.core.constant;

import java.util.function.Function;

public interface AdminApplicationConstants {

	int HTTP_READ_TIME_OUT = 15000;
	
	int HTTP_CONNECT_TIME_OUT = 20000;
	
	String USER_COMPANY_CREDENTIAL = "company_credential";
	
	int ADMIN_ROLE_TYPE = 1;
	
	Function<Object,Integer> MAPPER_FOR_INTEGER =
			objArray -> {
				Integer total =  0;
				if(objArray != null) {
					total = Integer.parseInt(objArray.toString());
				}
				return total;					
			};		

}
