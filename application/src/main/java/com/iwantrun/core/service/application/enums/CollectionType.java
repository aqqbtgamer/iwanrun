package com.iwantrun.core.service.application.enums;

public enum CollectionType {
	
	;
	
	private int id ;
	
	private String name ;
	
	CollectionType(int id ,String name){
		this.id = id ;
		this.name= name ;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public static CollectionType matchByName(String name) {
		for(CollectionType colletionType : CollectionType.values()) {
			if(colletionType.getName().equals(name)) {
				return colletionType;
			}
		}
		return null;
	}
	
	public static CollectionType matchById(Integer id) {
		if(id == null) {
			return null;
		}
		for(CollectionType colletionType : CollectionType.values()) {
			if(colletionType.getId() == id) {
				return colletionType;
			}
		}
		return null;
	}

	
	
}
