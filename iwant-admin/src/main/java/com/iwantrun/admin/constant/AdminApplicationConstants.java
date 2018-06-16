package com.iwantrun.admin.constant;

import java.util.function.Function;

public interface AdminApplicationConstants {
	
	int HTTP_READ_TIME_OUT = 10000;
	
	int HTTP_CONNECT_TIME_OUT = 15000;
	
	String[] FILTER_EXCLUD_PATTERNS = new String[] {"/css/**",
		      "/icons/**",
		      "/images/**",
		      "/js/**",
		      "/json/**",
		      "/ueditor1_4_3_3-utf8-jsp/**",
		      "/login.html",
		      "/login",
		      "/getLoginToken"
			};
	String HOME_PAGE = "home" ;
	
	String HOME_PAGE_FILE = "home.html" ;
	
	String LOGIN_PAGE = "login" ;
	
	String LOGIN_PAGE_FILE = "login.html" ;
	
	String USER_TOKEN = "current_user";
	
	String LOGIN_TOKEN = "login_token";
	
	String HTTP_RESULT_FAILED = "failed" ;	
	

}
