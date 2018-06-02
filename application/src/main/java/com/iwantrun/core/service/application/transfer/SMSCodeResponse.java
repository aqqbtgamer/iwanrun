package com.iwantrun.core.service.application.transfer;

import com.iwantrun.core.service.utils.JSONUtils;

/**
 * @author user
 * 获取短信验证码，接口响应封装
 */
public class SMSCodeResponse {
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 短信验证码
	 */
	private String smsCode;
	/**
	 * 短信验证码响应信息
	 */
	private String msg;
	
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return JSONUtils.objToJSON(this);
	}
}
