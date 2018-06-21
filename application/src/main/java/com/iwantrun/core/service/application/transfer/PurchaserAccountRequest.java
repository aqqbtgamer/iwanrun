package com.iwantrun.core.service.application.transfer;

import com.iwantrun.core.service.application.domain.PurchaserAccount;

public class PurchaserAccountRequest {
	
	private String sessionId;
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	private String smsCode;
	private PurchaserAccount account;
	/**
	 * 是否是短信登录
	 */
	private boolean messageLogin;
	
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	public PurchaserAccount getAccount() {
		return account;
	}
	public void setAccount(PurchaserAccount account) {
		this.account = account;
	}

	public boolean isMessageLogin() {
		return messageLogin;
	}

	public void setMessageLogin(boolean messageLogin) {
		this.messageLogin = messageLogin;
	}
	
}
