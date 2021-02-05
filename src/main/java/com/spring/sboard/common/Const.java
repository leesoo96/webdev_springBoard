package com.spring.sboard.common;

import java.util.List;
import com.spring.sboard.model.ManageBoardEntity;

public class Const {

//	각 게시판의 목록을 읽어오기위함
	public static List<ManageBoardEntity> menuList = null;
	
	public static final String KEY_LOGINUSER = "loginUser";
	public static final String KEY_LIST = "list";
	public static final String KEY_DATA = "data";
	public static final String KEY_RESULT = "result";
	
	// 인증 제한 시간 
	public static final int AUTH_REST_SEC = 300;
}
