package com.iwantrun.core.service.application.transfer;

import java.util.List;

import com.iwantrun.core.service.application.domain.ProductionInfo;
import com.iwantrun.core.service.application.domain.ProductionInfoAttachments;

public class ProductionInfoRequest {
	private ProductionInfo info;
	private int[] arr;
	private List<ProductionInfoAttachments> attachments;

	public ProductionInfo getInfo() {
		return info;
	}

	public void setInfo(ProductionInfo info) {
		this.info = info;
	}

	public List<ProductionInfoAttachments> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<ProductionInfoAttachments> attachments) {
		this.attachments = attachments;
	}

	public int[] getArr() {
		return arr;
	}

	public void setArr(int[] arr) {
		this.arr = arr;
	}
}
