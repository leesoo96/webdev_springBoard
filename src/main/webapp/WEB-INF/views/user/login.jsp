<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<div id="centerContainer">		
	<div>
		<form action="/user/login" method="post">
			<div><input type="text" name="user_id" placeholder="id"></div>
			<div><input type="password" name="user_pw" placeholder="password"></div>
			<div><input type="submit" value="LOGIN"></div>
		</form>
		<div style="color:red;">${msg}</div>
		<a href="/user/join">회원가입</a> 
		<a href="/user/findPw">비밀번호찾기</a>
	</div>		
</div>
<div>
	<c:forEach items="${list}" var="item">
		<div>${item.i_user}, ${item.nm}</div>
	</c:forEach>
</div>