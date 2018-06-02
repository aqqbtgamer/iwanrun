package com.iwantrun.core.service.application.enums;

public enum RoleType {
	Purchase(1,"采购方用户"),
	Advisor(2,"咨询师用户"),
	;
	private int id ;
	
	private String name ;
	
	RoleType(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public static RoleType matchByName(String name) {
		for(RoleType roleType : RoleType.values()) {
			if(roleType.getName().equals(name)) {
				return roleType;
			}
		}
		return null;
	}
	
	public static RoleType matchById(Integer id) {
		if(id == null) {
			return null;
		}
		for(RoleType roleType : RoleType.values()) {
			if(roleType.getId() == id) {
				return roleType;
			}
		}
		return null;
	}
}
