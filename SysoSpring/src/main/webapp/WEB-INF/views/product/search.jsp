<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시소마켓</title>
<link rel="icon" href="resources/images/symbol.svg">
<link href="resources/css/common.css" rel="stylesheet" type="text/css">
<link href="resources/css/header.css" rel="stylesheet" type="text/css">
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<script src="resources/js/jquery-3.1.1.min.js"></script>
<script src="resources/js/toggle.js"></script>
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


<section id="sec2" style="padding-top:140px;">
<div id="wrap">
<h1><span class="sig-color">${keyword}</span> 검색 결과</h1>


<article id="content_area" style="margin-bottom:100px;">

				<c:choose>
				<c:when test="${empty searchList}">
				<h1 style="text-align: center; font-size: 2rem; margin-top:10px; margin-bottom: 40px;"><span class="sig-color">${keyword}</span>에 대한 검색결과가 없습니다.</h1>
				<p style="text-align: center; margin-bottom: 10px;">-단어의 철자가 정확한지 확인해 보세요</p>
				<p style="text-align: center; margin-bottom: 10px; ">-보다 일반적인 검색어로 다시 검색해보세요</p>
				<p style="text-align: center; margin-bottom: 10px;">-검색어의 띄어쓰기를 다르게 해보세요</p>
				<p style="text-align: center;">-유해/금지어가 아닌지 확인해주세요</p>
				
				</c:when>
				
				<c:otherwise>
				<c:forEach items="${searchList}" var="list">

					<div class="content" style="cursor: pointer;" onclick="location.href='itemDetail?item_idx=${list.item_idx}'">
						<div class="content_top">
							<img src="resources/itemUpload/${list.item_img}" width="246" height="200">
						</div>

						<div class="content_bottom">
							<div class="con_title">${list.item_subject}</div>
							<div class="con_text">
								<div class="con_price">${list.item_price}원</div>
								<div class="con_time">
									<span id="innerhtml">				
								${list.item_date_format}		
									</span>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
				</c:otherwise>
				</c:choose>
				

			</article>
			
			<footer style="text-align: center; margin-top:100px;">
			    <p class="copy">ⓒ 2021 <span class="sig-color">SYSO</span> All rights reserved.</p>
			
			</footer>
	
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