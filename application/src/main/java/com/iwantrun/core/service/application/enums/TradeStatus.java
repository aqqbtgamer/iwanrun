package com.iwantrun.core.service.application.enums;

public enum TradeStatus {
	CLOSED(-1,"已关闭"),
	OPENED(0,"已提交"),
	ASSIGNED(1,"已指派"),
	EXECUTING(2,"定制中"),
	COMPLETED(3,"草案通过"),
	COMPLETED_UPDATE(4,"意向确认中"),
	CARRYING(5,"实施中"),
	FINSHED(6,"已结项"),
	UNFINISHED(7,"未结项");
	
	private int id ;
	
	private String name ;
	
	TradeStatus(int id ,String name){
		this.id = id ;
		this.name = name ;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public static TradeStatus matchByName(String name) {
		for(TradeStatus trade : TradeStatus.values()) {
			if(trade.getName().equals(name)) {
				return trade;
			}
		}
		return null;
	}
	
	public static TradeStatus matchById(Integer id) {
		if(id == null) {
			return null;
		}
		for(TradeStatus trade : TradeStatus.values()) {
			if(trade.getId() == id) {
				return trade;
			}
		}
		return null;
	}

}
