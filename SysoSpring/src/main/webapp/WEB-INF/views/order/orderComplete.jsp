<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />


<title>프로젝트명</title>
<!--[if lt IE 9]>
      <script src="js/html5.js"></script>
   <![endif]-->

<link href="resources/css/common.css" rel="stylesheet" type="text/css">
<link href="resources/css/header.css" rel="stylesheet" type="text/css">
<link href="resources/css/order.css" rel="stylesheet" type="text/css">
<link href="resources/css/style.css" rel="stylesheet" type="text/css">

<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">

</script>
<style type="text/css">
.star-rating {
  border:none;
  display:flex;
  flex-direction: row-reverse;
  font-size:1.5em;
  justify-content:space-around;
  padding:0 .2em;
  text-align:center;
  width:5em;
  margin:0 auto;
  margin-bottom:20px;
}

.star-rating input {
  display:none;
}

.star-rating label {
  color:#ccc;
  cursor:pointer;
}

.star-rating :checked ~ label {
  color:#f90;
}

.star-rating label:hover,
.star-rating label:hover ~ label {
  color:#fc0;
}
#sec textarea{
	border:1px solid #396afc;
	outline: none;
}
#form_area{
    box-shadow: 0 2px 8px rgb(0 0 0 / 10%), 0 8px 20px rgb(0 0 0 / 10%);
    background: #fff;
    border-radius: 10px;
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


	<section style="padding-top:140px; text-align: center;" id="sec">
	<div id="wrap">
	<h1 class="sig-color" style="font-size: 3rem; margin-bottom: 20px;">결제가 완료 되었습니다.</h1>
	<p style="font-size:1.5rem;margin-bottom: 20px;">오늘도 저희 시소마켓을 찾아주셔서 진심으로 감사드립니다.</p>
	
	
	<form action="shopReview/insert" method="post" id="form_area" style="padding:20px;">
	<p style="margin-bottom: 20px; color:brown">거래가 만족스러우셨다면 상점에 대한 리뷰를 남겨주세요.</p>
	<div class="star-rating">
  <input type="radio" id="5-stars" name="shopReview_star" value="5" />
  <label for="5-stars" class="star">&#9733;</label>
  <input type="radio" id="4-stars" name="shopReview_star" value="4" />
  <label for="4-stars" class="star">&#9733;</label>
  <input type="radio" id="3-stars" name="shopReview_star" value="3" />
  <label for="3-stars" class="star">&#9733;</label>
  <input type="radio" id="2-stars" name="shopReview_star" value="2" />
  <label for="2-stars" class="star">&#9733;</label>
  <input type="radio" id="1-star" name="shopReview_star" value="1" />
  <label for="1-star" class="star">&#9733;</label>
  
  
</div>
<input type="hidden" value="${param.item_idx }" name="item_idx">
  <textarea name="shopReview_content" placeholder="욕설 및 도배를 작성하실 경우 제재당하실수도 있습니다. 감사합니다." cols="50" rows="10" style="resize:none; padding: 20px; margin-bottom:20px;"></textarea>
	
	<button type="submit" style="display:block; margin:0 auto; border:none; padding:10px 20px; background:#396afc; color:#fff; border-radius:6px; cursor: pointer;">작성</button>
	
	</form>
	</div>
	
	</section>




	<footer class="footer">
		<div id="container">
			<p>
				ⓒ2021 Team <span class="sig-color">SYSO</span>
			</p>
			<p>Designed by Kys</p>
		</div>
	</footer>
	
	<script type="text/javascript">
	function openTalk() {
		window.open("chatList", "PopupWin",
				"width=408,height=500,resizable=no,scrollbars=no");
		window.resizeTo(405, 500);
	}
	
	</script>

</body>

</html>
