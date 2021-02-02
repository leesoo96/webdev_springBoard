package com.spring.sboard.common;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.spring.sboard.model.UserEntity;

public class SecurityUtils {

//	true: 로그인 성공, false: 로그인 실패
	public static boolean isLogin(HttpSession hs) {
		return getLoginUser(hs) != null;
	}
	
	public static UserEntity getLoginUser(HttpSession hs) {		
		return (UserEntity) hs.getAttribute(Const.KEY_LOGINUSER);
	}
	
	public static int getLoingUserPk(HttpSession hs) {
		UserEntity loginUser = getLoginUser(hs);
		return loginUser == null ? -1 : loginUser.getI_user();
	}
	
	public static String gensalt() {
		return BCrypt.gensalt();
	}

	public static String hashPassword(String pw, String salt) {
		return BCrypt.hashpw(pw, salt);
	}
	
	// 인증을 위한 랜덤숫자코드 생성
	public static String getPrivateCode(int len) {
		String str = "";
		
		for (int i = 0; i < len; i++) {
			str += (int)Math.random() * 10;
		}
		
		return str;
	}
}
