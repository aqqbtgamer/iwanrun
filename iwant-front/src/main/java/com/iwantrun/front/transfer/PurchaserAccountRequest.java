package com.iwantrun.front.transfer;

import com.iwantrun.front.domain.PurchaserAccount;

public class PurchaserAccountRequest {
	private String sessionId;
	private String smsCode;
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
	
}
