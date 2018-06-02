package com.iwantrun.core.service.application.enums;

public enum Gender {
	Male(1,"男性"),
	
	Female(2,"女性"),
	
	Other(3,"其他");
		
	private int id ;
	
	private String desc;
	
	Gender(int id,String desc) {
		this.id = id ;
		this.desc = desc ;
	}

	public int getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	
	public static Gender matchByName(String name) {
		for(Gender gender : Gender.values()) {
			if(gender.getDesc().equals(name)) {
				return gender;
			}
		}
		return null;
	}
	
	public static Gender matchById(Integer id) {
		if(id == null) {
			return null ;
		}
		for(Gender gender : Gender.values()) {
			if(gender.getId() == id) {
				return gender;
			}
		}
		return null;
	}

}
