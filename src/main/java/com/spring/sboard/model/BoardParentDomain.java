package com.spring.sboard.model;

import java.util.List;

public class BoardParentDomain {
	private int page;
	private int recordPage;
	private int maxPageNum;
	private List<BoardDomain> list;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRecordPage() {
		return recordPage;
	}
	public void setRecordPage(int recordPage) {
		this.recordPage = recordPage;
	}
	public int getMaxPageNum() {
		return maxPageNum;
	}
	public void setMaxPageNum(int maxPageNum) {
		this.maxPageNum = maxPageNum;
	}
	public List<BoardDomain> getList() {
		return list;
	}
	public void setList(List<BoardDomain> list) {
		this.list = list;
	}
}
