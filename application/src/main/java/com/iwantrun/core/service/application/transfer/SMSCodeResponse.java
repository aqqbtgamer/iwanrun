package com.iwantrun.core.service.application.transfer;

import com.iwantrun.core.service.utils.JSONUtils;

/**
 * @author user
 * 获取短信验证码，接口响应封装
 */
public class SMSCodeResponse {
	/**
	 * 是否成功：
	 * 	Success-成功  
	 * 	Faild-失败
	 */
	private String returnstatus;
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 短信验证码
	 */
	private String smsCode;
	/**
	 * 加过密的短信验证码
	 */
	private String encryptedSMSCode;
	
	/**
	 * 短信验证码响应信息
	 */
	private String message;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	
	public String getReturnstatus() {
		return returnstatus;
	}
	public void setReturnstatus(String returnstatus) {
		this.returnstatus = returnstatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String toString() {
		return JSONUtils.objToJSON(this);
	}
	public String getEncryptedSMSCode() {
		return encryptedSMSCode;
	}
	public void setEncryptedSMSCode(String encryptedSMSCode) {
		this.encryptedSMSCode = encryptedSMSCode;
	}
}
