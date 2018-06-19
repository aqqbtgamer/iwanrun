package com.iwantrun.front.transfer;

import com.iwantrun.front.domain.PurchaserAccount;
import com.iwantrun.front.utils.JSONUtils;

public class PurchaserAccountRequest {
	private String sessionId;
	private String smsCode;
	private boolean messageLogin;
	private PurchaserAccount account;
	
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
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	@Override
	public String toString() {
		return JSONUtils.objToJSON(this);
	}

	public boolean isMessageLogin() {
		return messageLogin;
	}

	public void setMessageLogin(boolean messageLogin) {
		this.messageLogin = messageLogin;
	}
}
