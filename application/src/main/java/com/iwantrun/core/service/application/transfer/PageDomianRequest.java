package com.iwantrun.core.service.application.transfer;

public class PageDomianRequest<T> {
	
	private int pageIndex ;
	
	private T obj ;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}	

}
