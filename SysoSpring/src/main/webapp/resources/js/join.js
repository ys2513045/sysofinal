



var id = document.querySelector('#id');

var pw1 = document.querySelector('#pswd1');
var pwMsg = document.querySelector('#alertTxt');
var pwImg1 = document.querySelector('#pswd1_img1');

var pw2 = document.querySelector('#pswd2');
var pwImg2 = document.querySelector('#pswd2_img1');
var pwMsgArea = document.querySelector('.int_pass');


var userName = document.querySelector('#name');
var nickName = document.querySelector('#nickName');

var addrD = document.querySelector('#addrD');

var email = document.querySelector('#email');

var mobile = document.querySelector('#mobile');

var error = document.querySelectorAll('.error_next_box');




/*이벤트 핸들러 연결*/




id.addEventListener("focusout", checkId);
pw1.addEventListener("focusout", checkPw);
pw2.addEventListener("focusout", comparePw);
userName.addEventListener("focusout", checkName);
nickName.addEventListener("focusout", checkNickName);
mobile.addEventListener("focusout", checkPhoneNum);
email.addEventListener("focusout", isEmailCorrect);


	

function checkId() {
	var idPattern = /[a-zA-Z0-9_-]{5,20}/;
	if (id.value === "") {
		error[0].innerHTML = "필수 정보입니다.";
		error[0].style.color = "#ff0000";
		error[0].style.display = "block";
		return false;

	} else if (!idPattern.test(id.value)) {
		error[0].innerHTML = "5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.";
		error[0].style.color = "#ff0000";
		error[0].style.display = "block";
		return false;
	} else{
		error[0].innerHTML = "멋진 아이디네요!";
		error[0].style.color = "#08A600";
		error[0].style.display = "block";
		var result = checkdup();
		if(result){
			return true;
		}else{
			return false;
		}
	}
	}
	

function checkPw() {
	var pwPattern = /[a-zA-Z0-9~!@#$%^&*()_+|<>?:{}]{8,16}/;
	if (pw1.value === "") {
		error[1].innerHTML = "필수 정보입니다.";
		error[1].style.color = "#ff0000";
		error[1].style.display = "block";
		return false;
	} else if (!pwPattern.test(pw1.value)) {
		error[1].innerHTML = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
		pwMsg.innerHTML = "사용불가";
		pwMsgArea.style.paddingRight = "93px";
		error[1].style.display = "block";
		pwMsg.style.color = "#ff0000";
		pwMsg.style.display = "block";
		pwImg1.src = "resources/images/m_icon_not_use.png";
		return false;
	} else {
		error[1].style.display = "none";
		pwMsg.innerHTML = "안전";
		pwMsg.style.display = "block";
		pwMsg.style.color = "#03c75a";
		pwImg1.src = "resources/images/m_icon_safe.png";
		return true;
	}
}


function comparePw() {
	if (pw2.value === pw1.value && pw2.value != "") {
		pwImg2.src = "resources/images/m_icon_check_enable.png";
		error[2].style.display = "none";
		return true;

	} else if (pw2.value !== pw1.value) {
		pwImg2.src = "resources/images/m_icon_check_disable.png";
		error[2].innerHTML = "똑같은 비밀번호를 입력하셔야죠^^;";
		error[2].style.display = "block";
		return false;
	}

	if (pw2.value === "") {
		error[2].innerHTML = "필수 정보입니다.";
		error[2].style.display = "block";
		pwImg2.src = "resources/images/m_icon_check_disable.png";
		return false;
	}
}

function checkNickName() {
	if (nickName.value === "") {
		error[3].innerHTML = "필수 정보입니다.";
		error[3].style.color = "#ff0000";
		error[3].style.display = "block";
		return false;
	} else {
		error[3].style.display = "none";
		var result = checkNicknamedup();
		if(result){
			return true;
		}else{
			return false;
		}

	}

}


function checkName() {
	var namePattern = /[a-zA-Z가-힣]/;
	if (userName.value === "") {
		error[4].innerHTML = "필수 정보입니다.";
		error[4].style.display = "block";
		return false;
	} else if (!namePattern.test(userName.value) || userName.value.indexOf(" ") > -1) {
		error[4].innerHTML = "한글과 영문 대 소문자를 사용하세요. (특수기호, 공백 사용 불가)";
		error[4].style.display = "block";
		return false;
	} else {
		error[4].style.display = "none";
		return true;
	}
}

function checkAdressDetail() {
	if (addrD.value === "") {
		error[5].innerHTML = "필수 정보입니다.";
		error[5].style.display = "block";
		return false;
	} else {
		error[5].style.display = "none";
				return true;


	}
}


function isEmailCorrect() {
	var emailPattern = /[a-z0-9]{2,}@[a-z0-9-]{2,}\.[a-z0-9]{2,}/;

	if (email.value === "") {

		error[6].innerHTML = "필수 정보입니다.";
		error[6].style.color = "#ff0000";
		error[6].style.display = "block";
		return false;

	} else if (!emailPattern.test(email.value)) {
		error[6].innerHTML = "이메일 형식이 맞지않습니다.";
		error[6].style.color = "#ff0000";
		error[6].style.display = "block";
		
		return false;
	} else {
		error[6].style.display = "none";
		var result = checkEmaildup();
		if(result){
			return true;
		}else{
			return false;
		}
		


	}

}

function checkPhoneNum() {
	var isPhoneNum = /([01]{2})([01679]{1})([0-9]{3,4})([0-9]{4})/;

	if (mobile.value === "") {
		error[7].innerHTML = "휴대폰 번호를 입력해주세요.";
		error[7].style.display = "block";
		return false;

	} else if (!isPhoneNum.test(mobile.value)) {
		error[7].innerHTML = "형식에 맞지 않는 번호입니다.";
		error[7].style.display = "block";
		return false;

	} else {
		error[7].style.display = "none";
		return true;


	}

}

//var id2 = document.getElementByID('id');

function checkdup() {
		var dupResult = false;

		$.ajax({
			type: 'POST',
			url: 'checkDup',
			async : false,
			data: JSON.stringify({
			"member_id":id.value
		}),
			dateType: 'json',
			contentType: 'application/json; charset=UTF-8',
			success: function(data) {
				if (data > 0) {
					error[0].innerHTML = "아이디 중복입니다.";
					error[0].style.color = "#ff0000";
					error[0].style.display = "block";
					dupResult = false;
				} else {
					error[0].innerHTML = "사용 가능한 아이디입니다.";
					error[0].style.color = "#08A600";
					error[0].style.display = "block";
					dupResult = true;
					}
			},error:function(){
				dupResult = false;
			}	
			
		});
		return dupResult;
	}
	
	function checkEmaildup() {
		var dupResult = false;

		$.ajax({
			type: 'POST',
			url: 'checkEmailDup',
			async : false,
			data: JSON.stringify({
			"member_email":email.value
		}),
			dateType: 'json',
			contentType: 'application/json; charset=UTF-8',
			success: function(data) {
				if (data > 0) {
					error[6].innerHTML = "이메일 중복입니다.";
					error[6].style.color = "#ff0000";
					error[6].style.display = "block";
					dupResult = false;
				} else {
					error[6].innerHTML = "사용 가능한 이메일 입니다.";
					error[6].style.color = "#08A600";
					error[6].style.display = "block";
					dupResult = true;
					}
			},error:function(){
				dupResult = false;
			}	
			
		});
		return dupResult;
	}
	
		function checkNicknamedup() {
		var dupResult = false;

		$.ajax({
			type: 'POST',
			url: 'checkNicknameDup',
			async : false,
			data: JSON.stringify({
			"member_nickname":nickName.value
		}),
			dateType: 'json',
			contentType: 'application/json; charset=UTF-8',
			success: function(data) {
				if (data > 0) {
					error[3].innerHTML = "닉네임 중복입니다.";
					error[3].style.color = "#ff0000";
					error[3].style.display = "block";
					dupResult = false;
				} else {
					error[3].innerHTML = "사용 가능한 닉네임 입니다.";
					error[3].style.color = "#08A600";
					error[3].style.display = "block";
					dupResult = true;
					}
			},error:function(){
				dupResult = false;
			}	
			
		});
		return dupResult;
	}




function checkfield(){
		var result1 = checkId();
		var result2 = checkPw();
		var result3 = comparePw();
		var result4 = checkName();
		var result5 = checkNickName();
		var result6 = checkPhoneNum();
		var result7 = isEmailCorrect();
	
		if(result1 && result2 && result3 && result4 && result5 && result6 && result7){
			return true;
		}else{
			return false;
		}
		
}




