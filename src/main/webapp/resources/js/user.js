//기본 이미지 사용 
function delProfileImg() {
	axios.get('/user/delProfileImg.korea').then(function(res) {
		var basicProfileImg = '/res/img/basic_profile.jpg';22		
		if(res != null && res.status == 200) {
			if(res.data.result == 1) { // 프로필 이미지 삭제 완료
				var img = document.querySelector('#profileImg');
				var container = document.querySelector('#delProfileBtnContainer');
				
				img.src = basicProfileImg;
				container.remove();
			}
		}		
	}).catch(function(err) {
		console.err('err 발생 : ' + err);
	})
}

function chkPw() {
	var frm = document.querySelector('#frm');
	if(frm.current_pw.value == '') {
		alert('기존 비밀번호를 작성해 주세요.');
		frm.current_pw.focus();
		return false;
	} else if(frm.user_pw.value == '') {
		alert('변경 비밀번호를 작성해 주세요.');
		frm.user_pw.focus();
		return false;
	} else if(frm.user_pw.value != frm.chk_user_pw.value) {
		alert('변경/확인 비밀번호를 확인해 주세요.');
		frm.user_pw.focus();
		return false;
	}
}

// 인증메일받기
function clkFindPwBtn () {
	
	var user_id = document.querySelector('#findPwUserId').value

	ajax()
	
	function ajax () {
		fetch(`/user/findPwProc?user_id=${user_id}`)
		.then(res => res.json())
		.then(res => {
			console.log(res.result)
			if(res.result === 1) {
				// 타이머 시작
				startTimer()
			} else {
				alert('인증메일 발송 실패!')
			}
		})
	}
	
	// 타이머 함수
	function startTimer() {
		var countDownTime = document.querySelector('#countDownTime')
		var totalSec = 300
		
		var interval = setInterval(function () {
			var min = parseInt(totalSec / 60)
			var sec = totalSec - (min * 60)
			var result = `남은 시간  ${create2Seat(min)} : ${create2Seat(sec)}`
			
			countDownTime.innerText = result
			
			if(totalSec === 0) {
				clearInterval(interval)
			}
			totalSec-- 
		}, 1000)
	}
	
	// 2자리 숫자 만들기
	function create2Seat(p) {
		p = Number(p).toString()
		if(p.length == 1) {
			p = '0' + p
		}
		return p

//		var val = '0' + p
//		return val.substr(val.length - 2, 2)
		
//		return p.length == 1 ? `0${p}` : `${p}`
	}
}

// 비밀번호 변경
var findPwAuthFrm = document.querySelector('#findPwAuthFrm')
if(findPwAuthFrm) {
	var btnSendElem = findPwAuthFrm.btnSend
	var userPw = findPwAuthFrm.user_pw
	var userChkPw = findPwAuthFrm.chk_user_pw

	var user_idValue = findPwAuthFrm.user_id.value
	var cdValue = findPwAuthFrm.cd.value	
//	var user_PwValue = findPwAuthFrm.user_pw.value -> 빈 값을 사용하겠다는 의미가 된다
	
	btnSendElem.onclick = function() {
		if(userPw.value !== userChkPw.value) {
			alert('비밀번호가 다릅니다!')
			return
		}
		ajax()
	}
	
	function ajax() { // 버튼을 클릭하는 순간 실행된다
		var param = {
				user_id : user_idValue,
				cd: cdValue, 
				user_pw : userPw.value, // 외부에 선언되면 무조건 빈 값이 되므로 
													// 여기에 선언해준다 
		}
		
		fetch('/user/findPwAuth', {
			method: 'POST',
			headers: {
				'Content-type' : 'application/json'
			},
			body : JSON.stringify(param)
		})
		.then(res => res.json())
		.then(myJson => {
			proc(myJson)
		})
	}
	
	function proc(res) {
		switch (res.result) {
			case 0 : 
				alert('비밀번호 변경 실패!')
				return
			case 1 : 
				alert('비밀번호 변경 완료!') // 화면 로딩 안됨 - 확인필요
				location.href = '/user/login'
				return 
			case 2 :
				alert('인증 제한 시간이 초과되었습니다!') // 확인필요
				return 
		}
	}
}