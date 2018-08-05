package com.iwantrun.core.service.application.enums;

public enum GroupNumberRange {

	FiveTen(1, "5~10 人"), TenFifteen(2, "10~15 人");

	private GroupNumberRange(Integer code, String range) {
		this.code = code;
		this.range = range;
	}

	private Integer code; // 活动人数范围
	private String range; // 活动人数范围描述

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public static String matches(Integer code) {
		GroupNumberRange[] ranges = GroupNumberRange.values();
		for (GroupNumberRange range : ranges) {
			if (range.getCode() == code)
				return range.getRange();
		}
		return "";
	}
}
