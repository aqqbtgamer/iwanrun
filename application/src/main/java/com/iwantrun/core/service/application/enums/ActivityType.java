package com.iwantrun.core.service.application.enums;

public enum ActivityType {

	Run("1", "跑步"), Fitness("2", "健身");

	private String code;
	private String type;

	private ActivityType(String code, String type) {
		this.code = code;
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static String matches(String code) {
		ActivityType[] types = ActivityType.values();
		for (ActivityType type : types) {
			if (type.getCode().equals(code))
				return type.getType();
		}
		return "";
	}
}
