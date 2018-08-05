package com.iwantrun.core.service.application.enums;

public enum OrderGroupPriceRange {

	FiveTenHundred(1, "5~10 天"), TenFifteenHundred(2, "10~15 天");

	private Integer code; // 订单团体参考报价范围
	private String range; // 订单团体参考报价范围描述

	private OrderGroupPriceRange(Integer code, String range) {
		this.code = code;
		this.range = range;
	}

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
		OrderGroupPriceRange[] ranges = OrderGroupPriceRange.values();
		for (OrderGroupPriceRange range : ranges) {
			if (range.getCode() == (code))
				return range.getRange();
		}
		return "";
	}
}
