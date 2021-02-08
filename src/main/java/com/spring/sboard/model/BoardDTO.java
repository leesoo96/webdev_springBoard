package com.spring.sboard.model;

import org.apache.ibatis.type.Alias;

@Alias("BoardDTO")
public class BoardDTO extends BoardEntity {
	
	private int recordPageCnt;
	private int page;
	private int pageIdx;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRecordPageCnt() {
		return recordPageCnt;
	}
	public void setRecordPageCnt(int recordPageCnt) {
		this.recordPageCnt = recordPageCnt;
	}
	public int getPageIdx() {
		return pageIdx;
	}
	public void setPageIdx(int pageIdx) {
		this.pageIdx = pageIdx;
	}
	
	
}