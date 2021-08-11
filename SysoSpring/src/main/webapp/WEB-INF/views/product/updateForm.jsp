<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />

<title>시소마켓 | 상품관리</title>
  <!--[if lt IE 9]>
      <script src="js/html5.js"></script>
   <![endif]-->

<link href="resources/css/common.css" rel="stylesheet" type="text/css">
<link href="resources/css/header.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="resources/css/upload.css">

<script src="resources/js/jquery-3.1.1.min.js"></script>
<script src="resources/js/jquery-ui.min.js"></script>
<script src="resources/js/toggle.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script type="text/javascript">
   function findAddr(){
		new daum.Postcode({
        	oncomplete: function(data) {
        	
      		  	console.log(data);
        	
      	     	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
        	    // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
        	    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
        	    var roadAddr = data.roadAddress; // 도로명 주소 변수
        	    var jibunAddr = data.jibunAddress; // 지번 주소 변수
        	    // 우편번호와 주소 정보를 해당 필드에 넣는다.
        	    document.getElementById('member_post').value = data.zonecode;
            	if(roadAddr !== ''){
	                document.getElementById("member_addr").value = roadAddr;
    	        } 
        	    else if(jibunAddr !== ''){
            	    document.getElementById("member_addr").value = jibunAddr;
           		}
        	}
    	}).open();
	}
	
<!-- ========================== EndDate 추가한부분 =============================              -->

	window.onload = function() {
		var today = document.getElementById('item_endDate').value = new Date().toISOString().substring(0, 10);
		document.getElementById('item_endDate').setAttribute('min', today);
		
		
		var maxDate = new Date();
		var dd = maxDate.getDate();
		var mm = maxDate.getMonth() + 2; // 1월부터 시작하지만 default가 0임 (1달후 => +2)
		var yyyy = maxDate.getFullYear();
		
		if(dd<10){
		   dd='0'+dd
		} 
		if(mm<10){
		   mm='0'+mm
		} 
		   
		maxDate = yyyy+'-'+mm+'-'+dd;
		document.getElementById('item_endDate').setAttribute('max', maxDate);
	}
	
$(document).ready(function() {
	var category_idx = ${item.category_idx};
	
	switch(category_idx) {
	case 1: 
		$('#category1').attr('selected', 'selected'); break;
	case 2:
		$('#category2').attr('selected', 'selected'); break;
	case 3:
		$('#category2').attr('selected', 'selected'); break;
	case 4:
		$('#category2').attr('selected', 'selected'); break;
	case 5:
		$('#category2').attr('selected', 'selected'); break;
	case 6:
		$('#category2').attr('selected', 'selected'); break;
	case 7:
		$('#category2').attr('selected', 'selected'); break;
	case 8:
		$('#category2').attr('selected', 'selected'); break;
	case 9:
		$('#category2').attr('selected', 'selected'); break;
	case 10:
		$('#category2').attr('selected', 'selected'); break;
	case 11:
		$('#category2').attr('selected', 'selected'); break;
	case 12:
		$('#category2').attr('selected', 'selected'); break;
	}
	
// 	$('input[name="simple_endDate"]').change(function() {
// 		var value = $('input[name="simple_endDate"]:checked').val();
		
		
// 		var date = new Date();
// 		alert(date.getDate()+value);
// 		date.setDate(date.getDate() + value);
// 		alert(date);
// 	});
});

</script>


<!-- ========================== EndDate 추가한부분 =============================              -->

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
    <div id="wrap">
  <div id="tabs">
  
      
      
      <div id="tabs-1"> 
          <h1 class="title">기본정보&nbsp;&nbsp;
          <span class="sig-color">*필수항목</span>
          </h1>
          
          <form action='<c:url value="/updatePro" />' method="post" class="post_form" enctype="multipart/form-data">
      		<input type="text" name="item_idx" value="${item.item_idx }" hidden="" >
                <div class="title">
                    상품이미지
                </div>
                <div class="preview">
                    <div class="upload" style="background-image: url('resources/itemUpload/${item.item_img }');">
                     <label className="input-file-button" for="id_photo">

                       
                        </label>
                    </div>
                </div>
                <p>
                <label className="input-file-button" for="id_photo">
                <a class="pointer">
                    <img src="resources/images/pictures.svg" name="img" class="file_sel_img" >
                    </a>
                    <span class="info">상품 이미지는 640x640에 최적화 되어 있습니다.</span>

                </label>
                
                    <label className="title">
                        <p>
                    		<input type="text" name="item_subject" class="product_title" value="${item.item_subject }">
                        </p>
                    </label>
                </p>

                
                <p>
                    <textarea name="item_detail" id="text_field" cols="50" rows="5" style="resize: none;">${item.item_detail }</textarea>
                </p>
              
              <p class="now1">
                  <label class="sel_category">
              카테고리<span class="sig-color">*</span>
                  <select class="select_area" name="item_category" >
	                  <option value="">전체카테고리</option>
                  	  <option value="1" id="category1">여성의류</option>
                      <option value="2" id="category2">패션잡화</option>
                      <option value="3" id="category3">남성의류</option>
                      <option value="4" id="category4">디지털/가전</option>
                      <option value="5" id="category5">도서/티켓/취미/반려</option>
                      <option value="6" id="category6">스타굿즈</option>
                      <option value="7" id="category7">생활/문구/가구/식품</option>
                      <option value="8" id="category8">스포츠/레저</option>
                      <option value="9" id="category9">뷰티/미용</option>
                      <option value="10" id="category10">유아동/출산</option>
                      <option value="11" id="category11">차량/오토바이</option>
                      <option value="12" id="category12">기타</option>
                  
                  </select>
                  </label>
              </p>
              
              <div class="now2">
				<p class="add_title">
                   거래지역<span class="sig-color">*</span>
                </p>
                  
               <label>
               
		        <input id="member_post"  type="text" placeholder="우편번호" readonly onclick="findAddr()" >
		                  
		        <input id="member_addr" name="item_region" type="text" value="${item.item_region }"><br><br>

				<c:choose>
			        <c:when test="${item.item_isDirect }">
						<input type="checkbox" name="item_isDirect" checked="checked">&nbsp;직거래 가능
			        </c:when>
			        <c:otherwise>
			        	<input type="checkbox" name="item_isDirect">&nbsp;직거래 가능
			        </c:otherwise>
				</c:choose>
		        
                  <br>
<!--                     <p class="detail_add"> -->
<!--                     <input type="text" placeholder="상세주소"  name="detail_addr"> 거래지역에 굳이 상세주소필요없을것 같음 -->
                  </label>
              
                  </div>
              
              
              
                 <div class="now3">
                     
					<div class="txt_area">
						상품상태<span class="sig-color">*</span>
                    </div>
                    
				<label>
					<div class="ckbox_area">
						<c:choose>
					        <c:when test="${item.item_isUsed }">
								<input type="radio" name="item_isUsed" value=<%=false%> checked="checked">&nbsp;중고상품&nbsp;
                          <input type="radio" name="item_isUsed" value=<%=true%>>&nbsp;새상품
					        </c:when>
					        <c:otherwise>
					        	<input type="radio" name="item_isUsed" value=<%=false%>>&nbsp;중고상품&nbsp;
                          		<input type="radio" name="item_isUsed" value=<%=true%> checked="checked">&nbsp;새상품
					        </c:otherwise>
						</c:choose>
                     </div>
                     </label>
              </div>
              
              
              
                 <div class="now4">
                   <div class="txt_area">
                   교환<span class="sig-color">*</span>
                    
                         
                     </div>
				<label>
					<div class="ckbox_area">
						<c:choose>
					        <c:when test="${item.item_isExchange }">
								<input type="radio" name="item_isExchange" value=<%=false%>>&nbsp;교환불가&nbsp;
                         		<input type="radio" name="item_isExchange" value=<%=true%> checked="checked">&nbsp;교환가능
					        </c:when>
					        <c:otherwise>
					        	<input type="radio" name="item_isExchange" value=<%=false%> checked="checked">&nbsp;교환불가&nbsp;
                         		<input type="radio" name="item_isExchange" value=<%=true%>>&nbsp;교환가능
					        </c:otherwise>
						</c:choose>
                     </div>
                     </label>
                     </div>
              
              
					<div class="now5">
						<div class="txt_area">
						가격<span class="sig-color">*</span>
						</div>
					
						<label>
							<input type="text" name="item_price" value="${item.item_price }">원&nbsp;&nbsp;
                  			
                  			<c:choose>
                  				<c:when test="${item.item_isDeliveryFree }">
                  					<input type="checkbox" name="item_isDeliveryFree" value=<%=true%> checked="checked">&nbsp;배송비 무료
                  				</c:when>
                  				<c:otherwise>
                  					<input type="checkbox" name="item_isDeliveryFree" value=<%=true%>>&nbsp;배송비 무료
                  				</c:otherwise>
                  			</c:choose>
							
                  
                  
						</label>
					</div>
              
              
              <div class="now6">
              <div class="txt2_area">
                  <p>연관태그</p>
                  </div>
                  
                  <div class="input_area">
                  <input type="text" name="hashtag_name" placeholder="연관태그를 입력해주세요.(최대 5개)" value="${item.hashtag_name }"><br>
                      <p class="tag_info">- 태그는 띄어 쓰기로 구분되며 최대 9자까지 입력할 수 있습니다.
                      </p>
                      
                    
                      
                  </div>
                  
                  
              </div>
              
              <div class="now7">
              <div class="txt2_area">
                  <p>수량</p>
                  </div>
                  
                  <div class="input_area">
                  <input type="text" name="item_count" value="${item.item_count }" >&nbsp;개
                  </div>
              </div>
              
              
              
              
			<!-- ========================== EndDate 추가한부분 =============================              -->
              <div class="now8">
              	<div class="txt2_area">
                  <p>판매 기간</p>
	                <div class="input_area">
	                	<input type="date" name="item_endDate" id="item_endDate" value="${item.item_endDate }">
	                </div>
	            </div>
              	<div class="txt2_area">
                  <p>간편 설정</p>
	                <div class="input_area">
	                	<input type="radio" name="simple_endDate" value="0" checked="checked">&nbsp;직접 설정&nbsp;
	                	<input type="radio" name="simple_endDate" value="7">&nbsp;7일&nbsp;
	                    <input type="radio" name="simple_endDate" value="14">&nbsp;14일&nbsp;
	                    <input type="radio" name="simple_endDate" value="30">&nbsp;30일&nbsp;
	                </div>
	            </div>
              	<p class="tag_info">- 기본등록일은 7일 이며, 최대 30일까지 등록 가능합니다.</p>
           		</div>
           		
           		<!-- ========================== EndDate 추가한부분 =============================              -->
                <input class="submit_btn" type="submit" value="수정하기">
            </form>
          
      </div>
      
      
      
      
      
<!--  ============================================================================================================================================      -->




	</div>
    

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
  

    </body>
    <script>
					$(function() {
						$("#tabs").tabs();

						$('.me').click(function() {
							$('.me').removeClass('on');
							$(this).addClass('on');
						});

						$("#tabs2").tabs();

						$('.me').click(function() {
							$('.me').removeClass('on');
							$(this).addClass('on');
						});

						var fileInput = document.querySelector("#id_photo"), button = document
								.querySelector(".input-file-trigger"), the_return = document
								.querySelector(".file-return");

						// Show image
						fileInput
								.addEventListener('change', handleImage, false);
						var canvas = document.getElementById('imageCanvas');
						var ctx = canvas.getContext('2d');

						function handleImage(e) {
							var reader = new FileReader();
							reader.onload = function(event) {
								var img = new Image();
								// var imgWidth =
								img.onload = function() {
									canvas.width = 300;
									canvas.height = 300;
									ctx.drawImage(img, 0, 0, 300, 300);
								};
								img.src = event.target.result;
								// img.width = img.width*0.5
								// canvas.height = img.height;
							};
							reader.readAsDataURL(e.target.files[0]);
						}

					});
					
					function openTalk() {
						window.open("chatList", "PopupWin",
								"width=408,height=500,resizable=no,scrollbars=no");
						window.resizeTo(405, 500);
					}
					
				</script>
    </html>
