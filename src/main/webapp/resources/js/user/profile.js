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
		.then(res => res.json())
		.then(myJson => {
			if(myJson === 1) {
				getData()
			} else {
				alert('이미지 업로드에 실패하였습니다!')
			}
		})
	}
}

var splide = null
var centerCont = document.querySelector('.centerCont')
function getData() {
	fetch('/user/profileData')
	.then(res => res.json())
	.then(myJson => {
		proc(myJson)
	})
	
	function proc(myJson) {
		const div = document.createElement('div')
		div.classList.add('profileBox')
		
		let delProfileHTML = ''
		
		const imgBasicSrc = '/res/img/basic_profile.jpg'
		const imgSrc = `/res/img/user/${myJson.i_user}/${myJson.profile_img}`
		
		let imgOption = ''
		
		if(myJson.profile_img) {
			imgOption = ` onclick="clkProfile()" class="pointer"`
			delProfileHTML = `
				<div id="delProfileBtnContainer">
					<button onclick="delProfileImg();">기본이미지 사용</button>
				</div>
			`
		}
		
		var gender = '여성'
		if(myJson.gender === 1) {
			gender = '남성'
		}
		
		div.innerHTML = 
		` 
			<div class="circular--landscape circular--size200">
				<img id="profileImg" src="/res/img/${imgSrc}" ${imgOption} alt="기본이미지" 
					   onerror="this.onerror=null; this.src='${imgBasicSrc}'">
			</div>
			<div>
				<div>아이디 : ${myJson.user_id}</div>
				<div>이름 : ${myJson.nm}</div>
				<div>성별 : ${gender}</div>
				<div>연락처 : ${myJson.phone}</div>
			</div>
			${delProfileHTML}
		`
		centerCont.innerHTML = null
		centerCont.append(div)
	}
}

getData()

// 프로필 Modal창
var modalContainerElem = document.querySelector('.modalContainer')
function clkProfile() {
	openModal()
	getProfileImgList()
}

// 모든 프로필 이미지 가져오기
function getProfileImgList() {
	fetch('/user/profileImgList') 
	.then(res => res.json())
	.then(myJson => {
		profileImgCarouselProc(myJson)
	})
}

// 프로필 이미지 삭제
function delProfileImg({i_img, img}) {
	console.log('i_img = ' + i_img + ' img = ' + img)
	// 오래 걸리기때문에 Promise를 리턴해줘야한다
	return new Promise(function(resolve) {
		fetch(`/user/profileImg?i_img=${i_img}&img=${img}`, {
			method: 'delete'
		})
		.then(res => res.json())
		.tehn(myJson => {
			resolve(myJson)
		})
	})
}

function profileImgCarouselProc(myJson) {
	console.log(myJson)
	var splideList = document.querySelector('.splide__list')
	splideList.innerHTML = null
	myJson.forEach(function (item) {
		const div = document.createElement('div')
		div.classList.add('splide__slide')
		
		const img = document.createElement('img')
		img.src = `/res/img/user/${item.i_user}/${item.img}`
		
		const span = document.createElement('span')
		span.classList.add('pointer')
		span.append('X')
		span.addEventListener('click', function() {
			console.log(item.i_img)
// 어느 프로필 이미지를 삭제할지 모르는 상태이기때문에 함수에 Promise를 리턴하도록 한다
			delProfileImg(item).then(myJson => {
				if(myJson === 1) {
					div.remove()
					splide.refresh()
				} else {
					alert('프로필 이미지 삭제를 실패하였습니다!')
				}
			})
		})
		
		div.append(img)
		div.append(span)
		splideList.append(div)
		
		if(splide != null) {
			splide.desotry(true)
		}
		
		spldie = new Splide('.splide').mount()
	})
	
	new Splide('.splide').mount()
}

function openModal() {
	modalContainerElem.classList.remove('hide')
}

function cloaseModal() {
	modalContainerElem.classList.add('hide')
}