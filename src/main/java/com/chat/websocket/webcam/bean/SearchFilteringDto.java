package com.chat.websocket.webcam.bean;

public class SearchFilteringDto<T> {

	private int page;
	private int size;
	private String order;
	private boolean asc;
	private T search;

	public SearchFilteringDto() {
		super();
	}

	public SearchFilteringDto(int page, int size, String order, boolean asc, T object) {
		super();
		this.page = page;
		this.size = size;
		this.order = order;
		this.asc = asc;
		this.search = object;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public boolean getAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public T getSearch() {
		return search;
	}

	public void setSearch(T object) {
		this.search = object;
	}

}
