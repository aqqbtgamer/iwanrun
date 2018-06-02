package com.iwantrun.core.service.application.transfer;

import com.iwantrun.core.service.application.domain.PurchaserAccount;
import com.iwantrun.core.service.application.domain.UserInfo;
import com.iwantrun.core.service.application.domain.UserRole;

/**
 * @author WXP22
 *
 */
public class MixedUserResponse {
	
	private PurchaserAccount loginInfo ;
	
	private UserInfo userInfo ;
	
	private UserRole userRole ;
	
	private Integer id ;
	
	public MixedUserResponse(PurchaserAccount login ,UserInfo userInfo, UserRole useRole) {
		this.loginInfo = login ;
		this.userInfo = userInfo ;
		this.userRole = useRole ;
		if(this.loginInfo != null) {
			this.id = loginInfo.getId();
		}
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

	public Integer getId() {
		return id;
	}
	
	
	
	

}
