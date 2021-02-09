package com.spring.sboard.model;

import org.apache.ibatis.type.Alias;

@Alias("BoardDTO")
public class BoardDTO extends BoardEntity {
	
//	페이징 변수
	private int recordCntPerPage;
	private int sIdx;
	private int page;
	
//	검색용 변수
	private String searchText;
	private int searchType;
	
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public int getSearchType() {
		return searchType;
	}
	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRecordCntPerPage() {
		return recordCntPerPage;
	}
	public void setRecordCntPerPage(int recordCntPerPage) {
		this.recordCntPerPage = recordCntPerPage;
	}
	public int getsIdx() {
		return sIdx;
	}
	public void setsIdx(int sIdx) {
		this.sIdx = sIdx;
	}
}