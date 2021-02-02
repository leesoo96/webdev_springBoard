package com.spring.sboard.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.sboard.common.Const;
import com.spring.sboard.common.SecurityUtils;
import com.spring.sboard.model.BoardCmtDomain;
import com.spring.sboard.model.BoardCmtEntity;
import com.spring.sboard.model.BoardDTO;
import com.spring.sboard.model.BoardEntity;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService service;
	
	@GetMapping("/home") // home 페이지 표시
	public void home() {}
	
	@GetMapping("/list") // 글목록 불러오기
	public void list(Model model, BoardDTO p) {
		model.addAttribute(Const.KEY_LIST, service.selBoardList(p));
	}
	
	@GetMapping("/reg") // 글쓰기 페이지 표시
	public String reg() {
		return "board/regmod";
	}
	
	@PostMapping("/reg")  // 글쓰기
	public String reg(BoardEntity p, HttpSession hs) {
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		service.insBoard(p);
		
		return "redirect:/board/detail?i_board=" + p.getI_board();
	}
	
	@GetMapping("/detail") // 글읽기
	public void detail(BoardDTO p, HttpSession hs, Model model) {
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		model.addAttribute(Const.KEY_DATA, service.selBoard(p));
	}
	
	@GetMapping("/mod") // 글수정 페이지 표시
	public String mod(BoardDTO p, Model model) {
		model.addAttribute(Const.KEY_DATA, service.selBoard(p));
		
		return "board/regmod";
	}
	
	@PostMapping("/mod") // 글수정
	public String mod(BoardEntity p, HttpSession hs) {
//		보안을 위해 설정
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		service.updBoard(p);
		
		return "redirect:/board/detail?i_board=" + p.getI_board();
	}
	
	@ResponseBody // -> JSON형태로 변환함
	@DeleteMapping("/del/{i_board}")         // 글삭제
	public Map<String, Object> del(BoardDTO p, HttpSession hs) {		
		System.out.println("i_board : " + p.getI_board());
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		
		Map<String, Object> map = new HashMap<String, Object>();
		
//				 key 값       value 값 
		map.put("result", service.delBoard(p));
		
		return map;
	}
	
//	---------------------------- 댓글 --------------------
	
	@ResponseBody
	@PostMapping("/insCmt") // 댓글쓰기
	public Map<String, Object> insCmt(@RequestBody BoardCmtEntity p
			, HttpSession hs) {
		
		System.out.println("i_board : " + p.getI_board());
		System.out.println("ctnt : " + p.getCtnt());
		
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		
		Map<String, Object> returnValue = new HashMap<String, Object>();
		returnValue.put(Const.KEY_RESULT, service.insCmt(p));
		
		return returnValue;
	}
	
	@ResponseBody
	@GetMapping("/cmtList") // 댓글 읽기
	public List<BoardCmtDomain> selCmtList(BoardCmtEntity p, HttpSession hs) {
		System.out.println("게시글댓글 : " + p.getI_board());
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		
		return service.selCmtList(p);
	}
	
	@ResponseBody
	@PutMapping("/updCmt") // 댓글 수정
	public Map<String, Object> updCmt(@RequestBody BoardCmtEntity p, HttpSession hs) {
		System.out.println("수정댓글 = " + p.getCtnt());

		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		
		Map<String, Object> returnValue = new HashMap<String, Object>();
		returnValue.put(Const.KEY_RESULT, service.updcmt(p));

		return returnValue;
	}
	
	@ResponseBody
	@DeleteMapping("/delCmt") // 댓글 삭제
	public Map<String, Object> delCmt(BoardCmtEntity p, HttpSession hs) {
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		
		Map<String, Object> returnValue = new HashMap<String, Object>();
		returnValue.put(Const.KEY_RESULT, service.delCmt(p));
		
		return returnValue;
	}
}
