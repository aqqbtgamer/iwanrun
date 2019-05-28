package com.iwantrun.core.service.application.enums;

public enum EntityType {
	
	Locaction("location"),
	Case("case"),
	Production("production");
	
	private String name ;
	
	private EntityType(String name) {
		this.name  = name ;
	}
	
	public String getName() {
		return this.name ;
	}
	
	public static EntityType match(String name) {
		for(EntityType entity :EntityType.values()) { 
			if(entity.getName().equals(name)) {
				return entity;
			}
		}
		return null;
	}

}
