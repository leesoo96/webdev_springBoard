'use strict'

// 페이징
function getBoardList(page) {
	var dataElem = document.querySelector('#data')
	var {typ} = dataElem.dataset // === typ = dataElem.dataset.typ
	var listFrmElem = document.querySelector('#listFrm')
	var typElem = listFrmElem.typ
	typElem.value = typ
	
	if(page) {
		listFrmElem.page.value = page
	}
	
	listFrmElem.submit()
}