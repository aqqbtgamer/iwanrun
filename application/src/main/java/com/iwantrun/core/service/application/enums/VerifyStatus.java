package com.iwantrun.core.service.application.enums;

public enum VerifyStatus {
	Verifed(1,"验证通过"),
	Not_Verified(2,"未验证通过")
	;
	private int id ;
	
	private String name ;
	
	VerifyStatus(int id ,String name){
		this.id = id ;
		this.name = name ;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public static VerifyStatus matchByName(String name) {
		for(VerifyStatus verify : VerifyStatus.values()) {
			if(verify.getName().equals(name)) {
				return verify;
			}
		}
		return null;
	}
	
	public static VerifyStatus matchById(Integer id) {
		if(id == null) {
			return null;
		}
		for(VerifyStatus verify : VerifyStatus.values()) {
			if(verify.getId() == id) {
				return verify;
			}
		}
		return null;
	}
}
