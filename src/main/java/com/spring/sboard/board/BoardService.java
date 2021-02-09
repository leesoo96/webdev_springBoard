package com.spring.sboard.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.sboard.common.Const;
import com.spring.sboard.model.BoardCmtDomain;
import com.spring.sboard.model.BoardCmtEntity;
import com.spring.sboard.model.BoardDTO;
import com.spring.sboard.model.BoardDomain;
import com.spring.sboard.model.BoardEntity;
import com.spring.sboard.model.BoardParentDomain;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper mapper;

//	글목록 읽어오기
	public BoardParentDomain selBoardList(BoardDTO p) {
		if(p.getTyp() == 0) {
			p.setTyp(1);
		}
		
		if(p.getRecordCntPerPage() == 0) {
			p.setRecordCntPerPage(5);
		}
		if(p.getPage() == 0) {
			p.setPage(1);
		}

		int sIdx = (p.getPage() - 1) * p.getRecordCntPerPage();
		p.setsIdx(sIdx);

		BoardParentDomain bpd = new BoardParentDomain();
		bpd.setMaxPageNum(mapper.selMaxPageNum(p));
		bpd.setList(mapper.selBoardList(p));
		bpd.setPage(p.getPage());
		bpd.setRecordCntPerPage(p.getRecordCntPerPage());
		
		final int SIDE_NUM = Const.PAGE_SIDE_NUM; // 3
		int pageLen = SIDE_NUM * 2 + 1; // 7
		int page = p.getPage(); // 페이징 번호(1,2,3...)
		int maxPage = bpd.getMaxPageNum(); // 마지막 페이징되는 번호 
																// 게임게시판의 5개씩 글이 보일경우 11 
		int startPage = 1; // 페이징 시작번호
		int endPage = page + SIDE_NUM; // 페이징번호 + 3 => 페이징 끝번호
		System.out.println("page = "+page+" endPage=" + endPage); 
		
		if(pageLen < maxPage) {
			System.out.println("startPage1 = "+startPage);
			
			if(SIDE_NUM < page) { 
				startPage = page - SIDE_NUM; 
				System.out.println("startPage2 = "+startPage);
			} 
			if(startPage > maxPage - pageLen) { 
				System.out.println("pageLen = "+pageLen);
				startPage = maxPage - pageLen + 1;
				System.out.println("startPage3 = "+startPage);
			}
			
			if(endPage > maxPage) {
				endPage = maxPage;
				System.out.println("maxPage= " +maxPage + " endPage = "+endPage);
			} else if(endPage < pageLen) {
				endPage = pageLen;
				System.out.println("Last endPage = "+endPage + " pageLen = " + pageLen);
			}
		} else { // 마지막페이징번호 
			endPage = maxPage;
		}
		
		bpd.setStartPage(startPage);
		bpd.setEndPage(endPage);	
		
		return bpd;
	}
	
//	글쓰기
	public int insBoard(BoardEntity p) {
		return mapper.insBoard(p);
	}
	
//	글읽기
	public BoardDomain selBoard(BoardDTO p) {
		mapper.updBoardHits(p);	
		return mapper.selBoard(p);
	}
	
//	글수정
	public int updBoard(BoardEntity p) {
		return mapper.updBoard(p);
	}
	
//	글삭제
	public int delBoard(BoardDTO p) {
		return mapper.delBoard(p);
	}

//	댓글작업 ---------------------------------------
	
//	댓글쓰기
	public int insCmt(BoardCmtEntity p) {
		return mapper.insCmt(p);
	}
	
//	댓글읽기
	public List<BoardCmtDomain> selCmtList(BoardCmtEntity p) {
		return mapper.selCmtList(p);
	}
	
//	댓글수정
	public int updcmt(BoardCmtEntity p) {
		return mapper.updCmt(p);
	}
	
//	댓글삭제
	public int delCmt(BoardCmtEntity p) {
		return mapper.delCmt(p);
	}
}
