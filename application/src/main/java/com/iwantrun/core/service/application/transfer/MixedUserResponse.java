package com.iwantrun.core.service.application.transfer;

import java.util.List;

import com.iwantrun.core.service.application.domain.PurchaserAccount;
import com.iwantrun.core.service.application.domain.UserInfo;
import com.iwantrun.core.service.application.domain.UserInfoAttachments;
import com.iwantrun.core.service.application.domain.UserRole;

/**
 * @author WXP22
 *
 */
public class MixedUserResponse {
	
	private PurchaserAccount loginInfo ;
	
	private UserInfo userInfo ;
	
	private UserRole userRole ;
	
	private List<UserInfoAttachments> companyCredentials;

	private List<UserInfoAttachments> headImgs;

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

	public List<UserInfoAttachments> getCompanyCredentials() {
		return companyCredentials;
	}

	public void setCompanyCredentials(List<UserInfoAttachments> companyCredentials) {
		this.companyCredentials = companyCredentials;
	}

	public List<UserInfoAttachments> getHeadImgs() {
		return headImgs;
	}

	public void setHeadImgs(List<UserInfoAttachments> headImgs) {
		this.headImgs = headImgs;
	}

	public Integer getId() {
		return id;
	}
	
	
	
	

}
