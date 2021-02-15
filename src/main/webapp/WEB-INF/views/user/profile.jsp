<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@splidejs/splide@latest/dist/css/splide.min.css">
<link rel="stylesheet" href="/res/css/user/profile.css">
 
<div class="centerCont"></div>
<div>
	<input type="file" id="inputImg" multiple accept="image/*">						
	<input type="button" value="업로드" onclick="upload()">
</div>
<div class="modalContainer hide">
	<div class="modalContent">
		<span class="pointer" onclick="">닫기</span>
		<div class="splide">
			<div class="splide__track">
				<ul class="splide__list">
					<li class="splide__slide">Slide 01</li>
					<li class="splide__slide">Slide 02</li>
					<li class="splide__slide">Slide 03</li>
				</ul>
			</div>
		</div>
	</div>
</div>

<!-- SPLIDE lib 이용 -->
<script src="https://cdn.jsdelivr.net/npm/@splidejs/splide@latest/dist/js/splide.min.js"></script>		
<script src="/res/js/user/profile.js?ver=2"></script>
