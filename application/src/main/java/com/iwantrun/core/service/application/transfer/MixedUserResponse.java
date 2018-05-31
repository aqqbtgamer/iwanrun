package com.iwantrun.core.service.application.transfer;

import com.iwantrun.core.service.application.domain.PurchaserAccount;
import com.iwantrun.core.service.application.domain.UserInfo;
import com.iwantrun.core.service.application.domain.UserRole;

public class MixedUserResponse {
	
	private PurchaserAccount loginInfo ;
	
	private UserInfo userInfo ;
	
	private UserRole userRole ;
	
	public MixedUserResponse(PurchaserAccount login ,UserInfo userInfo, UserRole useRole) {
		this.loginInfo = login ;
		this.userInfo = userInfo ;
		this.userRole = useRole ;
	}

	public PurchaserAccount getLoginInfo() {
		return loginInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public UserRole getUserRole() {
		return userRole;
	}
	
	

}
