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


<title>시소마켓 | 주문하기</title>

<link href="resources/css/common.css" rel="stylesheet" type="text/css">
<link href="resources/css/header.css" rel="stylesheet" type="text/css">
<link href="resources/css/order.css" rel="stylesheet" type="text/css">
<link href="resources/css/style.css" rel="stylesheet" type="text/css">

<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="resources/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	function findAddr() {
		new daum.Postcode({
			oncomplete : function(data) {

				console.log(data);

				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
				// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var roadAddr = data.roadAddress; // 도로명 주소 변수
				var jibunAddr = data.jibunAddress; // 지번 주소 변수
				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				document.getElementById('member_post').value = data.zonecode;
				if (roadAddr !== '') {
					document.getElementById("member_addr").value = roadAddr;
				} else if (jibunAddr !== '') {
					document.getElementById("member_addr").value = jibunAddr;
				}
			}

		}).open();
	}
</script>
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






	<section id="order_area">
		<div id="container">
			<h1 class="title">
				<span class="sig-color">주문정보 확인</span>
			</h1>
			<p class="sub_title">
				<span style="color: red">주문자 정보와 배송지가 다른 경우 직접 입력해주세요.</span>
			</p>
			<div class="order_form">
				<form action='<c:url value="/orderpro" />' method="post" id="frm">

					<c:forEach items="${olist}" var="olist">
						<table>
							<tr>
								<td class="tb_title">상품번호</td>
								<td><input type="text" value="${olist.item_idx}" readonly
									name="item_idx" id="item_idx"></td>
							</tr>

							<tr>
								<td class="tb_title">상품명</td>
								<td><input type="text" value="${olist.item_subject}"
									readonly name="item_subject" id="item_subject"></td>
							</tr>
						</table>

						<table>


							<tr>
								<td class="tb_title">ID</td>
								<td><input type="text" value="${sessionScope.id }" readonly
									name="member_id"></td>
							</tr>

							<tr>
								<td class="tb_title">NAME</td>
								<td><input type="text" value="${vo.member_name }"
									name="member_name" required></td>
							</tr>

							<tr>
								<td class="tb_title">ADRESS</td>
								<td><input id="member_post"
									style="cursor: pointer; background: #396afc; color: #fff;"
									type="text" placeholder="우편번호(클릭)" name="member_zipcode"
									onclick="findAddr()" readonly value="${vo.member_zipcode}"></td>
							</tr>

							<tr>
								<td class="tb_title"></td>
								<td><input id="member_addr" type="text"
									name="member_address" placeholder="주소" readonly required
									class="int" value="${vo.member_address}"></td>
							</tr>

							<tr>
								<td class="tb_title"></td>
								<td><input type="text" placeholder="상세주소"
									name="member_addressDetail" id="addrD" class="int"
									value="${vo.member_addressDetail}" name="member_addressDetail"
									required></td>
							</tr>
							<tr>
								<td class="tb_title">Phone</td>
								<td><input type="text" value="${vo.member_phone}"
									name="member_phone" required></td>

							</tr>
						</table>

						<h1 class="title">
							<span class="sig-color">결제수단 확인</span>
						</h1>
						<div class="buyby">
							<div class="submit_area">
								<table>


									<tr>
										<td class="tb_title">상품가격</td>
										<td><input type="text" name="item_price" id="item_price"
											readonly value="${olist.item_price}"></td>
									</tr>

									<tr>
										<td class="tb_title">배송비</td>
										<c:choose>
											<c:when test="${olist.item_isDeliveryFree == false }">
												<td><input type="text" readonly value="0"
													id="item_isDeliveryFree"></td>
											</c:when>
											<c:otherwise>
												<td><input type="text" readonly
													value="${olist.item_charge }" id="item_isDeliveryFree"></td>
											</c:otherwise>
										</c:choose>
									</tr>

									<tr>
										<td class="tb_title">보유포인트</td>
										<td><input type="number" name="have_Point" readonly
											value="${vo.member_point}"></td>
									</tr>

									<tr>
										<td class="tb_title">사용할 포인트</td>
										<td><input type="number" name="use_Point" id="use_Point"></td>
									</tr>
									<tr>
										<td class="tb_title"></td>
										<td class="payment_btn"><button type="button"
												name="usePoint" id="usePoint" class="usePoint">사용하기</button></td>
									</tr>


									<tr>
										<td class="tb_title">총 결제금액 :</td>
										<c:choose>
										<c:when test="${olist.item_isDeliveryFree == true }">
										<td><input type="text" readonly name="payment_price"
											id="payment_price"
											value="${olist.item_price + olist.item_charge}"
											style="color: green;"></td>
											</c:when>
											<c:otherwise>
											<td><input type="text" readonly name="payment_price"
											id="payment_price"
											value="${olist.item_price}"
											style="color: green;"></td>
											</c:otherwise>
											</c:choose>
											
									</tr>


								</table>

								<div class="btn_area">


									<button type="button" onclick="history.back()">돌아가기</button>
									<button type="button" id="iamport">결제하기</button>

								</div>

							</div>
						</div>


					</c:forEach>
				</form>

			</div>

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


	<script type="text/javascript"
		src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
	<script type="text/javascript">
		$(document).ready(
				function() {
					var item_price = $('#item_price').val(); // 상품 가격
					var have_Point = '${vo.member_point}' //    보유 포인트 
					var payment_price = $('#payment_price').val(); // 총 결제 금액
					var use_Point = 0;
					
					$('.usePoint:button').one('click',function() {
							use_Point = $('#use_Point').val();

								if (use_Point == "") {
									alert("사용할 포인트를 입력해주세요!")
									$('#use_Point').focus();
									location.reload(true);
									return false;
								}
	
								// 사용할 포인트가 보유 포인트보다 클 때
								if(Number(use_Point) > Number(have_Point)) {
									alert("보유 포인트를 확인해주세요!");
									location.reload(true);
									return false
								}
	
								// 사용할 포인트가 전체 결제금액보다 클 때
								if (Number(use_Point) > Number(payment_price)) {
									alert("상품가격보다 큽니다!")
									location.reload(true);
									return false
								}
	
								payment_price = payment_price - use_Point;
	
								$('#payment_price').attr('value', payment_price);
	
								var str = '<button type="button" name="usePoint" class="usePoint" style="background-color: black;">사용완료</button>';
								$('.payment_btn').html(str);
							});

						});

		$("#iamport").click(function() {
			var IMP = window.IMP;
			IMP.init('imp67131243');
			 var merchant_uid = $('#item_idx').val() + new Date().getTime();
			IMP.request_pay({
				// 주문자 정보
				pg : 'html5_inicis',
				pay_method : 'card',
				merchant_uid : merchant_uid, // 상품 번호
				name : $('#item_subject').val(), // 상품 이름
				amount : $('#payment_price').val(), // 주문 금액 
				buyer_email : '${vo.member_email}',
				buyer_name : '${vo.member_name}',
				buyer_tel : '${vo.member_name}',
				buyer_addr : '${vo.member_address}',
				buyer_postcode : '${vo.member_zipcode}',
			}, function(rsp) {
				console.log(rsp);
				if (rsp.success) {
					var msg = '결제가 완료되었습니다.';
					msg += '고유ID : ' + rsp.imp_uid;
					msg += '상점 거래ID : ' + rsp.merchant_uid;
					msg += '결제 금액 : ' + rsp.paid_amount;
					msg += '카드 승인번호 : ' + rsp.apply_num;
					//고객결제완료 후, 결제금액 검증
					$.ajax({
						url : "/syso/paymentComplete/" + rsp.imp_uid, // 충전 금액값을 보낼 url 설정
						type : "GET",
						success : function(data) {
							if (data.ok) {
								document.getElementById('frm').submit();
							} else {
								alert('안갑니다');
							}
						}
					});

				} else {
					var msg = '결제에 실패하였습니다.';
					msg += '에러내용 : ' + rsp.error_msg;
					//document.location.href="/home";
					alert(msg);
				}
			});
		});

		function openTalk() {
			window.open("chatList", "PopupWin",
					"width=408,height=500,resizable=no,scrollbars=no");
			window.resizeTo(405, 500);
		}
	</script>


</body>

</html>