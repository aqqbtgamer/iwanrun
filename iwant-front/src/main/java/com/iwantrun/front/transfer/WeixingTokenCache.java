package com.iwantrun.front.transfer;

import java.util.Date;

public class WeixingTokenCache {
	
	public static int acessCalidateTime = 3600000;  
	
	private String accessToken ;
	
	private Date generateDate ;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getGenerateDate() {
		return generateDate;
	}

	public void setGenerateDate(Date generateDate) {
		this.generateDate = generateDate;
	}
	
	

}
