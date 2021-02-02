package com.spring.sboard.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.sboard.common.Const;
import com.spring.sboard.common.SecurityUtils;
import com.spring.sboard.model.UserEntity;

// dao, service는 세트
@Service
public class UserService {

//	new()없이 객체 접근 가능
	@Autowired
	private UserMapper mapper;
		
//	 로그인
	public int login(UserEntity param, HttpSession hs) {
		UserEntity dbData = mapper.selUser(param);
		if(dbData == null) { 
			return 2; // 아이디 없음
		}
		String cryptLoginPw = SecurityUtils.hashPassword(param.getUser_pw(), dbData.getSalt());
		
		if(!cryptLoginPw.equals(dbData.getUser_pw())) { 
			return 3; // 비밀번호 다름
		}
		
		// 보안을 위해 세션값 제거
		dbData.setSalt(null);
		dbData.setUser_pw(null);
		
		// 실수 방지를 위해서 final로 만듦
		hs.setAttribute(Const.KEY_LOGINUSER, dbData);
		
		return 1; // 로그인 성공
	}
	
//	회원가입
	public int insUser(UserEntity param) {
		String salt = SecurityUtils.gensalt();
		String encryptPw = SecurityUtils.hashPassword(param.getUser_pw(), salt);
		
		param.setSalt(salt);
		param.setUser_pw(encryptPw);
		
		return mapper.insUser(param);
	}
	
//	비밀번호찾기
	public int findPwProc(String user_id) {
		String code = SecurityUtils.getPrivateCode(5);
		System.out.println("code = " + code);
		
		return 0;
	}
}
