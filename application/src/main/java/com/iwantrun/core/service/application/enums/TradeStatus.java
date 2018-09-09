package com.iwantrun.core.service.application.enums;

public enum TradeStatus {
	CLOSED(-1,"已关闭"),
	OPENED(0,"已提交"),
	ASSIGNED(1,"定制中"),
	EXECUTING(2,"实施中"),
	COMPLETED(3,"已完成"),
	COMPLETED_UPDATE(4,"修改")
	;
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
