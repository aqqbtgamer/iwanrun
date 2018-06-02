package com.iwantrun.core.service.application.transfer;

import com.iwantrun.core.service.utils.JSONUtils;

/**
 * @author user
 * 获取短信请求参数封装
 * 
 * http://dx1.xitx.cn:8888/sms.aspx?userid=6739&account=a10554&password=a123456&mobile=13*****86&action=send&content=【隔壁老王】您的验证码是131481
 */
public class SMSCodeRequest {
	/**
	 * 企业id
	 */
	private String userid;
	/**
	 * 发送用户帐号
	 */
	private String account;
	/**
	 * 发送帐号密码
	 */
	private String password;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 定时发送时间
	 * 为空表示立即发送，定时发送格式2010-10-24 09:08:10
	 */
	private String action;
	/**
	 * 发送内容
	 */
	private String content;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return JSONUtils.objToJSON(this);
	}
}
