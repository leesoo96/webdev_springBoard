package com.spring.sboard.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.sboard.model.BoardCmtDomain;
import com.spring.sboard.model.BoardCmtEntity;
import com.spring.sboard.model.BoardDTO;
import com.spring.sboard.model.BoardDomain;
import com.spring.sboard.model.BoardEntity;

@Mapper
public interface BoardMapper {
	
//	글목록 읽어오기
	List<BoardDomain> selBoardList(BoardDTO p);

//	페이징
	int selMaxPageNum(BoardDTO p);
	
//	글쓰기
	int insBoard(BoardEntity p);

//	글읽기
	BoardDomain selBoard(BoardDTO p);
	
//	글수정
	int updBoard(BoardEntity p);
	
//	조회수 증가
	int updBoardHits(BoardDTO p);
	
//	글삭제
	int delBoard(BoardDTO p);

//	댓글작업-----------------------------
	
//	댓글쓰기
	int insCmt(BoardCmtEntity p);
	
//	댓글읽기
	List<BoardCmtDomain> selCmtList(BoardCmtEntity p);
	
//	댓글수정
	int updCmt(BoardCmtEntity p);
	
//	댓글삭제
	int delCmt(BoardCmtEntity p);
}
