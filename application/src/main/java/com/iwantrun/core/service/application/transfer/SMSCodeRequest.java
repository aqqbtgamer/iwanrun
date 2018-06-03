package com.iwantrun.core.service.application.transfer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.iwantrun.core.service.utils.JSONUtils;

import static com.iwantrun.core.constant.SMSCodeConstants.*;


/**
 * @author user
 * 获取短信请求参数封装
 * 
 * http://dx1.xitx.cn:8888/sms.aspx?userid=6739&account=a10554&password=a123456&mobile=13*****86&action=send&content=【隔壁老王】您的验证码是131481
 */
@Component
public class SMSCodeRequest {
	/**
	 * 企业id
	 */
	@Value("${"+REQ_USER_ID_PROP_KEY+"}")
	private String userid;
	/**
	 * 发送用户帐号
	 */
	@Value("${"+REQ_ACCOUNT_PROP_KEY+"}")
	private String account;
	/**
	 * 发送帐号密码
	 */
	@Value("${"+REQ_PASS_PROP_KEY+"}")
	private String password;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 定时发送时间
	 * 为空表示立即发送，定时发送格式2010-10-24 09:08:10
	 */
	@Value("${"+REQ_ACTION_PROP_KEY+"}")
	private String action;
	/**
	 * 发送内容
	 */
	@Value("${"+REQ_CONTENT_PROP_KEY+"}")
	private String content;
	
	/**
	 * 短信验证码请求地址
	 */
	@Value("${"+REQ_URL_PROP_KEY+"}")
	private String url;
	
	private String smsCode;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
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
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
}
