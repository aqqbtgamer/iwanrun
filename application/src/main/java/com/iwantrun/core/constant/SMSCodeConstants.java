package com.iwantrun.core.constant;

/**
 * @author sanny 短信验证码相关常量
 */
public interface SMSCodeConstants {
	String REQ_URL_PROP_KEY = "sms_code.get.request.url";
	String REQ_USER_ID_PROP_KEY = "sms_code.get.request.userid";
	String REQ_PASS_PROP_KEY = "sms_code.get.request.password";
	String REQ_ACCOUNT_PROP_KEY = "sms_code.get.request.account";
	String REQ_ACTION_PROP_KEY = "sms_code.get.request.action";
	String REQ_CONTENT_PROP_KEY = "sms_code.get.request.content";

	String REQ_URI_VARIABLE_ACCOUNT_KEY = "account";
	String REQ_URI_VARIABLE_ACTION_KEY = "action";
	String REQ_URI_VARIABLE_CONTENT_KEY = "content";
	String REQ_URI_VARIABLE_PASST_KEY = "password";
	String REQ_URI_VARIABLE_USER_ID_KEY = "userid";
	String REQ_URI_VARIABLE_MOBILE_KEY = "mobile";

	String RES_RETURN_STATUS = "Success";
}
