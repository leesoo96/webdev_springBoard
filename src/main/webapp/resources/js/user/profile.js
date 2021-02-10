var inputImg = document.querySelector('#inputImg')
function upload() {
	if (inputImg.files.length === 0) {
		alert('이미지를 선택해주세요!')
		return 
	}
	
	ajax()
	
	function ajax() {
		var formData = new FormData()
		for(var i = 0; i < inputImg.files.length; i++) {
			formData.append('imgs', inputImg.files[i]) 
			// -> 키 값이름이 MultipartFile[] imgs 와 같아야한다 
		}
		
		fetch('/user/profileUpload', {
			method : 'POST', 
			body : formData,
		})
	}
}

var centerCont = document.querySelector('.centerCont')
function getData() {
	fetch('/user/profileData')
	.then(res => res.json())
	.then(myJson => {
		console.log(myJson)
	})
	
	function proc(myJson) {
		var div = document.createElement('div')
		var imgSrc = '/res/img/basic_profile.jpg'
		var gender = '여성'
		var delProfileHTML = ''
		console.log(myJson.user_id)
		if(myJson.gender === 1) {
			gender = '남성'
		}
		
		if(myJson.profile_img) {
			imgSrc = `/res/img/user/${myJson.i_user}/${myJson.profile_img}`
			delProfileHTML = `
				<div id="delProfileBtnContainer">
					<button onclick="delProfileImg();">기본이미지 사용</button>
				</div>
			`
		}
		
		div.classList.add('profileBox')
		div.innerHTML = 
		` 
			<div class="circular--landscape circular--size200">
				<img id="profileImg" src="/res/img/${imgSrc}" alt="기본이미지"">
			</div>
			<div>
				<div>아이디 : ${myJson.user_id}</div>
				<div>이름 : ${myJson.nm}</div>
				<div>성별 : ${gender}</div>
				<div>연락처 : ${myJson.phone}</div>
			</div>
			${delProfileHTML}
		`
		centerCont.innerHTML = ''
		centerCont.append(div)
	}
}

getData()