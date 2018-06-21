package com.iwantrun.front.transfer;

import com.iwantrun.front.utils.JSONUtils;

public class SMSCodeRequest {
	/**
	 * 手机号码
	 */
	private String mobile;
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return JSONUtils.objToJSON(this);
	}
}
