package com.iwantrun.core.service.application.transfer;

public class Token {
	
	private String tokenHolder ;
	
	private String tokenSession ;
	
	private long tokenMaxTime ;

	public String getTokenHolder() {
		return tokenHolder;
	}

	public void setTokenHolder(String tokenHolder) {
		this.tokenHolder = tokenHolder;
	}

	public String getTokenSession() {
		return tokenSession;
	}

	public void setTokenSession(String tokenSession) {
		this.tokenSession = tokenSession;
	}

	public long getTokenMaxTime() {
		return tokenMaxTime;
	}

	public void setTokenMaxTime(long tokenMaxTime) {
		this.tokenMaxTime = tokenMaxTime;
	}

}
