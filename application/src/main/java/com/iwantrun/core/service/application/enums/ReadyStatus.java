package com.iwantrun.core.service.application.enums;

public enum ReadyStatus {
	Read(1,"已读"),
	unRead(2,"未读")
	;
	private int id ;
	
	private String name ;
	
	ReadyStatus(int id ,String name){
		this.id = id ;
		this.name = name ;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public static ReadyStatus matchByName(String name) {
		for(ReadyStatus read : ReadyStatus.values()) {
			if(read.getName().equals(name)) {
				return read;
			}
		}
		return null;
	}
	
	public static ReadyStatus matchById(Integer id) {
		if(id == null) {
			return null;
		}
		for(ReadyStatus read : ReadyStatus.values()) {
			if(read.getId() == id) {
				return read;
			}
		}
		return null;
	}

}
