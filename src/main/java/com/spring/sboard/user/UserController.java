package com.spring.sboard.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.sboard.common.Const;
import com.spring.sboard.common.SecurityUtils;
import com.spring.sboard.model.AuthDTO;
import com.spring.sboard.model.AuthEntity;
import com.spring.sboard.model.UserEntity;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/login") // 로그인 페이지 표시
	public void login() {}
	
	@PostMapping("/login") // 로그인
	public String loginProc(UserEntity param, HttpSession hs) {
		int result = service.login(param, hs);
		
		if(result == 1) {
			return "redirect:/board/home";
		}
		return null;
	}
	
	@GetMapping("/logout") // 로그아웃
	public String logout(HttpSession hs) {
		hs.invalidate(); 
		
		return "redirect:/user/login";
	}
	
	@GetMapping("/join") // 회원가입 페이지 표시
	public void join() {}
	
	@PostMapping("/join") // 회원가입
	public String join(UserEntity param) {	 
		service.insUser(param);
		return "redirect:/user/login";
	}
	
	@GetMapping("/findPw") // 비밀번호찾기 페이지 표시
	public void findPw() {}
	
	@GetMapping("/findPwProc") // 비밀번호찾기
	public String findPwProc(AuthEntity p) {
		System.out.println("userId = " + p.getUser_id());
		
		service.findPwProc(p);		
		return "user/findPw";
	}
	
	@GetMapping("/findPwAuth")
	public void findPwAuth() {} // 비밀번호 인증 코드 입력 페이지 표시
	
	@ResponseBody
	@PostMapping("/findPwAuth") // 비밀번호 변경
	public Map findPwAuth(@RequestBody AuthDTO p) {
		Map<String, Object> returnVal = new HashMap();
		returnVal.put(Const.KEY_RESULT, service.findPwAuthProc(p));
		
		return returnVal;
	}
}
