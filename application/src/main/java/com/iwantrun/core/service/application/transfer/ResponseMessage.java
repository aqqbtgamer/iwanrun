package com.iwantrun.core.service.application.transfer;

import java.util.List;

import com.iwantrun.core.service.utils.JSONUtils;

public class ResponseMessage {
	
	private String code="0100";
	
	private String msg;
	
	private List<?> list;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return JSONUtils.objToJSON(this);
	}
	
	
	
	

}
