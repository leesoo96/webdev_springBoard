package com.spring.sboard.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		if(p.getRecordPageCnt() == 0) {
			p.setRecordPageCnt(5);
		}
		
		if(p.getPage() == 0) {
			p.setPage(1);
		}
		
		BoardParentDomain parentDomain = new BoardParentDomain();
		parentDomain.setMaxPageNum(mapper.selMaxPageing(p));
		parentDomain.setList(mapper.selBoardList(p));
		parentDomain.setPage(p.getPage());
		parentDomain.setRecordPage(p.getRecordPageCnt());
		
		return parentDomain;
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
