<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />



<title>프로젝트명</title>
  <!--[if lt IE 9]>
      <script src="js/html5.js"></script>
   <![endif]-->

<link href="resources/css/common.css" rel="stylesheet" type="text/css">
<link href="resources/css/header.css" rel="stylesheet" type="text/css">

<link rel="stylesheet" type="text/css" href="resources/css/member.css">
<script src="resources/js/jquery-3.1.1.min.js"></script>
<script src="resources/js/toggle.js"></script>


<style>
#popup {
    display: none; /*숨기기*/
    position: fixed;
    width: 100%;
    height: 100%;
    background: rgba(0,0,0,0.5);
    left:0;
    top:0;
    z-index:999999999;
}


#popmenu {
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-50%);
    width: 600px;
    height: 300px;
    padding:60px;
    text-align: center;
    background: #fff;
    border-radius: 20px;
      box-shadow: 0 2px 8px rgb(0 0 0 / 20%), 0 8px 20px rgb(0 0 0 / 20%);
    
}
#popmenu span{
display:none;
padding: 20px;
}


.pop_cont{
	display: flex;
    -webkit-box-pack: justify;
    justify-content: space-between;
    align-items: flex-start;
}
.pop_title{
letter-spacing: 5.2px;
    font-size: 26px;
    padding-bottom: 20px;
    border-bottom: 1px solid #396afc;
    margin-bottom: 30px;
}

.pop_cont button{
	background: none;
	outline: none;
	border:none;
}
#popmenu .textarea textarea{
	width:100%;
	height: 300px;
	resize: none;
	font-sizle:20px;
}
#popmenu .textarea textarea:focus{
	border:1px solid #396afc;
}

#popmenu .textarea{
margin-bottom: 20px;
}
placeholder::{
	font-size:20px;
}

#popmenu .report_btn{
	background:#396afc;
	border:none;
	padding:10px 20px;
	color:#fff;
	cursor: pointer;
	font-weight: bold;
	
}

</style>

</head>

<body>


<header id="header">
		<nav id="nav">
			<div id="wrap">
				<div class="login">

					<ul>
						<c:choose>
							<c:when test="${empty sessionScope}">
								<li><a href="login" class="sig-color">로그인</a></li>
								<li><a href="join" class="sig-color">회원가입</a></li>
							</c:when>

							<c:otherwise>
								<li><a href="member/logout" class="sig-color">로그아웃</a></li>
								<li><a href="qnaList" class="sig-color">고객센터</a></li>
							</c:otherwise>
						</c:choose>

					</ul>


				</div>
				<h1 id="logo">
					<a href="home"><img src="resources/images/main_logo.svg"></a>
				</h1>
			 
				<%
				String Context = request.getContextPath(); 
			%> 
				<form action="<%=Context%>/search" method="get">
					<div class="search_wrap">
						<div class="search_area">
							<input type="text" class="search" name="keyword"
								placeholder="상품명,지역명,@상점명 입력">
							<button type="submit"
								style="border: 0; background: none; cursor: pointer;" class="search_img">
								<img src="resources/images/home_search.svg">
							</button>
						</div>
					</div>
				</form>


			
			<div class="nav_right">
				<ul>
					<li><a href="<c:url value="/sell" />"> <img
							src="resources/images/money.svg"> 판매하기
					</a></li>
					<li><a href="<c:url value="/shop?nickname=${sessionScope.nickname}" />"> <img
							src="resources/images/shop.svg">내상점
					</a></li>

					<li style="cursor: pointer;" onclick="openTalk()"><img
						src="resources/images/talk.svg">시소톡</li>

					<li id="btn2"><img src="resources/images/hamburg.svg">카테고리

						<ul id="layer2">
							<li class="strong"><a href="#">전체 카테고리</a></li>

							
							<li><a href='<c:url value = "/itemCategory?item_category=1" />'>여성의류</a></li>
							<li><a href='<c:url value = "/itemCategory?item_category=2" />'>패션잡화</a></li>
							<li><a href='<c:url value = "/itemCategory?item_category=3" />'>남성의류</a></li>
							<li><a href='<c:url value = "/itemCategory?item_category=4" />'>디지털/가전</a></li>
							<li><a href='<c:url value = "/itemCategory?item_category=5" />'>도서/티켓/취미/반려</a></li>
							<li><a href='<c:url value = "/itemCategory?item_category=6" />'>스타굿즈</a></li>
							<li><a href='<c:url value = "/itemCategory?item_category=7" />'>생활/문구/가구/식품</a></li>
							<li><a href='<c:url value = "/itemCategory?item_category=8" />'>스포츠/레저</a></li>
							<li><a href='<c:url value = "/itemCategory?item_category=9" />'>뷰티/미용</a></li>
							<li><a href='<c:url value = "/itemCategory?item_category=10" />'>유아동/출산</a></li>
							<li><a href='<c:url value = "/itemCategory?item_category=11" />'>차량/오토바이</a></li>
							<li><a href='<c:url value = "/itemCategory?item_category=12" />'>기타</a></li>

						</ul></li>

				</ul>
			</div>

			</div>
			</div>
			

		</nav>


	</header>

    <section id="sec1">
        <div class="wrap2">
            <article class="go">
             <ul class="gotoJoin">
                <li class="normal">
                    계정이 없으신가요?
                </li>
                <li class="sig-color"><a href="join">회원가입</a></li>
            </ul>
        </article>


        <article class="loginform">
           

            <form action="loginPro" method="post">
               <div class="position">

                <label>
                    <img src="resources/images/id.svg">
                    <input type="text" name="member_id">
                </label>
            </div>
               <div class="position">
                    <img src="resources/images/key.svg">

                <label>

                    <input type="password" name="member_password">

                </label>
            </div>

                <input type="submit" value="로그인" style="margin-bottom: 20px; background: #396afc; color:#fff;">




                    


            </form>

<span style="cursor: pointer; display:block;"><a href="https://kauth.kakao.com/oauth/authorize?client_id=8a186887660b8f9049299861425b447a&redirect_uri=http://localhost:8080/syso/oauth&response_type=code"><img src="resources/images/kakao_login.png"></a></span>





            <p class="forget sig-color" style="cursor: pointer;" id="findBtn">비밀번호를 잊으셨나요?</p>
            
            			<div id="popup">
       <div id="popmenu">
       <div class="pop_cont">
           <div class="pop_title">비밀번호 찾기 </div>
           <button class="exit" style="cursor: pointer;"><img src="resources/images/exit.svg" width="24" height="24"></button>
       </div>
       <div class="textarea">
		<input type="text" placeholder="가입시 입력하신 아이디를 입력해주세요." id="id">
       </div>
       <button type="button" class="report_btn" onclick="findPw()">제출 </button>
       <span class="msgbox" style="color:#396afc"></span>
		<span class="msgbox" style="color:red"></span> 
      <span class="msgbox"  style="color:brown"></span>
       </div>
   </div>


        </article>
    </div>
    </section>

  <footer id="footer">
    
    <div id="wrap">
        <div class="copy">
            <p>ⓒ2021 Team <span class="sig-color">SYSO</span></p>
        <p>Designed by Kys</p>
        </div>
    </div>
    
    </footer>


 
<script type="text/javascript">

var id = document.getElementById('id');
// var member_id =  id.value;

// var msg1 = document.getElementsByClassName('success');
// var msg2 = document.getElementsByClassName('fail');

var error = document.querySelectorAll('.msgbox');



$(function(){
	

$("#findBtn").click(function(){
    $("#popup").fadeIn();
});
$(".exit").click(function(){
    $("#popup").fadeOut();
});	
});


function findPw() {
	
	if(id.value == ""){
		alert("아이디를 입력해주세요.");
		$('#id').focus();
	}else if(id.value == 'admin'){
		alert("어딜 감히 ^^*");
	}
		else{
	error[2].style.display = "block";
	error[2].innerHTML = "메일 발송중입니다. 잠시만 기다려주세요.";
		
	$.ajax({
		url:'findPw',
		type:'post',
		contentType:'application/json',
		dataType : 'json',
		data: JSON.stringify({
			"member_id":id.value
		}),
		success:function(data){
		error[2].style.display = "none";
		error[1].style.display = "none";
		error[0].innerHTML = data.member_name+"님이 가입시 입력하셨던 이메일로 임시비밀번호를 발급해드렸습니다.";
		error[0].style.display = "block";	
		


			
		},  // success
		error:function(){
			error[2].style.display = "none";
			error[0].style.display = "none";	
			error[1].innerHTML = "입력하신 ID와 일치하는 회원이 없습니다.";
			error[1].style.display = "block";	

			
		}
	
		
	}); //ajax
}
}

function openTalk() {
	window.open("chatList", "PopupWin",
			"width=408,height=500,resizable=no,scrollbars=no");
	window.resizeTo(405, 500);
}


</script>

    </body>
   
    </html>
