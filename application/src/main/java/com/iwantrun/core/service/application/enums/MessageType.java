package com.iwantrun.core.service.application.enums;

public enum MessageType {
	InnerMessage(0,"站内信"),
	ShortMessage(1,"手机短信"),
	WeixinMessage(2,"微信推送"),
	MailMessage(3,"邮件推送")
	;
	private int id ;
	
	private String name ;
	
	MessageType(int id ,String name){
		this.id = id ;
		this.name= name ;		
	}
	
	public int getId() {
		return id;
	}
	

	public String getName() {
		return name;
	}


	public static MessageType matchByName(String name) {
		for(MessageType messageType : MessageType.values()) {
			if(messageType.getName().equals(name)) {
				return messageType;
			}
		}
		return null;
	}
	
	public static MessageType matchById(Integer id) {
		if(id == null) {
			return null;
		}
		for(MessageType messageType : MessageType.values()) {
			if(messageType.getId() == id) {
				return messageType;
			}
		}
		return null;
	}

}
