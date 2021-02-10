'use strict'

//글 제목 클릭
function clkArticle(i_board, searchType, searchText) {		
	var url = `/board/detail?i_board=${i_board}&searchType=${searchType}&searchText=${searchText}`;
	location.href = url; //주소값 이동
}

var dataElem = document.querySelector('#data')
var listFrmElem = document.querySelector('#listFrm')
var typElem = listFrmElem.typ 

var searchType = document.querySelector('#searchType')
var searchText = document.querySelector('#searchText')

// 페이징 & 검색
function getBoardList(page) {
	
	var searchTextValue = searchText.value
	if(searchTextValue !== '') {
		var searchTypeValue = searchType.value
		
		listFrmElem.searchType.value = searchTypeValue
		listFrmElem.searchText.value = searchTextValue
	}
	
	var {typ} = dataElem.dataset // === typ = dataElem.dataset.typ
	typElem.value = typ
	
	if(page) {
		listFrmElem.page.value = page
	}
	
	listFrmElem.submit()
}

// 검색 엔터 기능 
function onSearch(e) {
	if(e.keyCode.value === 13) {
		getBoardList()
	}
}