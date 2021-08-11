<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="resources/css/common.css" rel="stylesheet" type="text/css">
<link href="resources/css/header.css" rel="stylesheet" type="text/css">
<script src="resources/js/jquery-3.1.1.min.js"></script>
<style type="text/css">
#container {
	width: 600px;
	margin: 0 auto;
	background: #fff;
	border-radius: 20px;
	box-shadow: 0 2px 8px rgb(0 0 0/ 20%), 0 8px 20px rgb(0 0 0/ 20%);
}

.ch-head, .ch-body, .ch-foot {
	padding: 20px;
}
.ch-head h1{
color: #396afc;}

.ch-body {
	height: 360px;
	overflow-y: scroll;
}

.ch-foot input {
	border: none;
	outline: none;
	background: none;
	margin-bottom: 20px;
	padding: 10px 20px;
}

.ch-foot input.insertbox {
	box-shadow: 0 2px 8px rgb(0 0 0/ 10%), 0 8px 20px rgb(0 0 0/ 10%);
	width: 80%;
	height: 50px;
	border-radius: 10px;
}

.ch-foot input.insertbox:focus {
	border: 1px solid #396afc;
}
.ch-foot ul{
position: relative;}
.ch-foot input[type="button"] {
	border: none;
	outline: none;
	background: none;
	position:absolute;
	top:55%;
	right:16%;
	width: 25px;
	height: 25px;
	background-image: url("resources/images/sendmsg.svg");
	background-repeat: no-repeat;
	background-position: center;
}

.ch-foot ul:after {
	cotent: '';
	display: block;
	clear: both;
}

#chat_area {
	padding-top: 140px;
}

#output p {
	padding: 10px;
}
</style>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		// Socket 열기
		myWebSocket.openSocket();

		// Send 버튼 Click Event
		$("#sendBtn").click(function() {
			sendMessage();
			$('#message').val('');
		});

		// 메시지 입력시
		$("#message").keydown(function(key) {
			if (key.keyCode == 13) {// 엔터
				sendMessage();
				$('#message').val('');
			}
		});
		

		
	});

	var wsUri = "ws://localhost:8080/syso/websok";
	var webSocket;
	var myWebSocket = {
		openSocket : function() {
			webSocket = new WebSocket(wsUri);
			webSocket.onopen = function(evt) {
				// Socket Open
				writeToScreen("<span style="+"'color:#396afc'" +">시소 익명채팅방에 오신것을 환영합니다 !</span>");
			};
			webSocket.onmessage = function(evt) {
				// 서버로 부터 메시지 수신
				myWebSocket.handleMessage(evt.data);
			};

			webSocket.onerror = function(evt) {
				// Socket Error 발생
				myWebSocket.onError(evt)
			};

			webSocket.onclose = function(event) {
				// Socket 닫힘
				writeToScreen("서버와의 연결이 끊겼습니다.");
			};
		},
		doSend : function(message) {
			// 서버로 메시지 전송
			webSocket.send(message);
		},
		handleMessage : function(data) {
			// 메시지 처리
			if (data != null) {
				$("#serverMessage").val(data);
				writeToScreen(data);
			}
		}
	}

	function writeToScreen(message) {
		// 서버로 부터 수신된 메시지를 출력한다.
		var pre = document.createElement("p");
		pre.style.wordWrap = "break-word";
		pre.innerHTML = message;
		output.appendChild(pre);
		output.apend(pre);
		$(".ch-body").scrollTop($(".ch-body")[0].scrollHeight);

		

	}

	function sendMessage() {
		if ($('#message').val() == '' || $('#message').val() == null)
			return;
		myWebSocket.doSend($("#message").val());
	}
</script>
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
	<section id="chat_area">
		<div id="container">
			<div class="ch-head">
				<h1 style="text-align: center; font-size: 2rem;">Anonymous Chat</h1>
				<br>
			</div>

			<div class="ch-body">
				<div id="output"></div>
			</div>


			<div class="ch-foot">
				<form action="">
					<ul>
						<li><input id="serverMessage" name="serverMessage"
							type="text" style="color: red" readonly></li>
						<li><input id="message" name="message"
							placeholder="메세지를 입력하세요" type="text" class="insertbox"></li>
						<li><input type="button" id="sendBtn"></li>

					</ul>
				</form>
			</div>


		</div>
	</section>
	
	<script type="text/javascript">
	function openTalk() {
		window.open("chatList", "PopupWin",
				"width=408,height=500,resizable=no,scrollbars=no");
		window.resizeTo(405, 500);
	}
	
	</script>
	
	
</body>


</html>

