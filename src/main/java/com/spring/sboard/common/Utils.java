package com.spring.sboard.common;

public class Utils {

//	jsp 파일 읽어오는 메소드
	public static String myViewResolver(String fileNm) {
		return "/WEB-INF/views/" + fileNm + ".jsp";
	}
}
