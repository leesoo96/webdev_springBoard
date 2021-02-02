<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<div id="centerContainer">		
	<div>
			<div><input id="findPw_userId" type="text" name="user_id" placeholder="id"></div>
			<div><input type="button" value="인증메일받기" onclick="clkFindPwBtn()"></div>
			<div style="color:red;" id="findPwMsg"></div>
			<a href="/user/login">로그인</a>
	</div>		
</div>