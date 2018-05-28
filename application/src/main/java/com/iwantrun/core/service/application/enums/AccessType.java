package com.iwantrun.core.service.application.enums;

public enum AccessType {
	Menu(0,"菜单"),
	Api(1,"api接口"),
	DataRead(2,"数据接口"),
	DataModify(3,"数据修改")
	;
	private int id ;
	
	private String name ;
	
	AccessType(int id, String name){
		this.id = id ;
		this.name = name ;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public static AccessType matchByName(String name) {
		for(AccessType acessType : AccessType.values()) {
			if(acessType.getName().equals(name)) {
				return acessType;
			}
		}
		return null;
	}
	
	public static AccessType matchById(int id) {
		for(AccessType acessType : AccessType.values()) {
			if(acessType.getId() == id) {
				return acessType;
			}
		}
		return null;
	}
	
	

}
