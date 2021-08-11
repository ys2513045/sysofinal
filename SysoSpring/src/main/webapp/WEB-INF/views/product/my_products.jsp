<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />

<title>시소마켓 | 내상점</title>
<!--[if lt IE 9]>
      <script src="js/html5.js"></script>
   <![endif]-->

<link href="resources/css/common.css" rel="stylesheet" type="text/css">
<link href="resources/css/header.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="resources/css/myshop.css">




<script src="resources/js/jquery-3.1.1.min.js"></script>
<script src="resources/js/jquery-ui.min.js"></script>
<script src="resources/js/toggle.js"></script>

<script type="text/javascript">
$(document).ready(function() {
   var member_id = '${sessionScope.id}';
      $(document).on('click', '#updateIntro', function(){
      var shop_intro = $('.shop_intro').text();
       shop_intro = shop_intro.replace('<br>', '\n');
      
      var x = '<textarea class="updateIntro" placeholder="500자 내외로 작성해주세요.">' + shop_intro + '</textarea><button class="updateBtn">확인</button>';
      $('.area3').html(x).trigger("create");
      $('#updateIntro').hide();
    	

     
      $('.updateBtn').click(function() {
         var intro = $('.updateIntro').val();
         $.ajax({
              url: "shop/updateIntro",
              type: "POST",
              data: {"intro": intro, "member_id": member_id},
              success: function() {
                 var updateData = '<div class="shop_intro">' + intro + '</div>';
                 var updateData2 = '<button class="update" id="updateIntro">소개글 수정</button>';
                $('.area3').html(updateData);
                $('.area4').html(updateData2);
              }
           });
        });
     });
     
   $(document).on('click', '#updateNickname', function() {
      var shop_name = '${nickname}';
      var x = '<input type="text" value="' + shop_name + '" class="updateNickname"> <button class="update" id="checkNickname">확인</button>';
      $('.area1_txt').html(x);
      
      $('#checkNickname').click(function() {
         var nickname = $('.updateNickname').val();
            $.ajax({
            url: "member/updateNickname",
            type: "POST",
            data: {"nickname": nickname, "member_id": member_id},
            success: function(rdata) {
               if(rdata == "") {
                  alert("이미 사용중인 상점명 입니다.");
               } else {
                  window.location.replace('shop?nickname='+nickname);
               }
//                   var updateData = rdata + '<button class="update" id="updateNickname">상점명 수정</button>';
//                   $('.area1_txt').html(updateData);
         }
      });
      });
   });
      
   $( "#tabs" ).tabs(); 
   
   $('.me').click(function(){
       $('.me').removeClass('on');
       $(this).addClass('on');
   });
		   


	   $("#report").click(function(){
	       $("#popup").fadeIn();
	   });
	   
	   
	   $(".exit").click(function(){
	       $("#popup").fadeOut();
	   });   
	   
	   
	    <c:forEach var="shopReview" items="${shopRivewList}">
        $("#reportshopReview${shopReview.shopReview_idx}").click(function(){
            $(".shopReview${shopReview.shopReview_idx}").fadeIn();
        });
        $(".exit2").click(function(){
            $(".shopReview${shopReview.shopReview_idx}").fadeOut();
        }); 
    </c:forEach>

   
});


</script>

<style type="text/css">
.updateIntro {
   width: 550px;
   height: 80px;
    resize: none; 
      position: relative;
   float: left;
   border-radius: 0;
}
.updateBtn {
   width: 70px;
   height: 100px;
     position:inherit;  
    top:0; left: 0;
    border: none;
    background: #396afc;
    color: #fff;
    cursor: pointer;
}

#popup {
	display: none; /*숨기기*/
	position: fixed;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.5);
	left: 0;
	top: 0;
	z-index: 999999999;
}
#popup2 {
	display: none; /*숨기기*/
	position: fixed;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.5);
	left: 0;
	top: 0;
	z-index: 999999999;
}

#popmenu {
	position: absolute;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	width: 600px;
	height: 500px;
	padding: 60px;
	text-align: center;
	background: #fff;
	border-radius: 20px;
	box-shadow: 0 2px 8px rgb(0 0 0/ 20%), 0 8px 20px rgb(0 0 0/ 20%);
}

.pop_cont {
	display: flex;
	-webkit-box-pack: justify;
	justify-content: space-between;
	align-items: flex-start;
}

.pop_title {
	letter-spacing: 5.2px;
	font-size: 26px;
	padding-bottom: 20px;
	border-bottom: 1px solid #396afc;
	margin-bottom: 30px;
}

.pop_cont button {
	background: none;
	outline: none;
	border: none;
}

#popmenu .textarea textarea {
	width: 100%;
	height: 300px;
	resize: none;
	font-sizle: 20px;
}

#popmenu .textarea {
	margin-bottom: 20px;
}

placeholder:: {
	font-size: 20px;
}

#popmenu .report_btn {
	background: #396afc;
	border: none;
	padding: 10px 20px;
	color: #fff;
	cursor: pointer;
	font-weight: bold;
}

.on2 {
	display: none;
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
	<div id="wrap">
		<section id="sec1">
			<div class="myprofile">
				<div class="profileimg">
					<div size="310" class="img_box">
						<div class="img_back">
							<div class="back_img"></div>
						</div>
						<div class="img_over">
							<a href=""> <img src="resources/images/default_shop_img.svg"
								width="100" height="100">

							</a>
							<div class="pro_txt1">${nickname}</div>

							<div class="pro_txt2">
								<a href="sell#tabs-2" class="goto"> 상점 관리 </a>
							</div>

						</div>
					</div>


				</div>

				<div class="profiletxt">
					<div class="area1">
						<div class="area1_txt">
							${nickname }
                       <c:if test="${shop.member_id eq sessionScope.id}">
                           <button class="update" id="updateNickname">상점명 수정</button>
                       </c:if>
						</div>

					</div>
					<div class="area2">
						<div class="area_2_">
						<span>상점오픈일</span>
						<div class="data">
						<span class="sig-color">${shop.shop_date_format }</span>
						</div>
						</div>
						
<!-- 						<div class="area_2_"> -->
<!-- 							<span style="margin-top: 3px;">상점방문수</span> -->
<!-- 							<div class="data"> -->
<!-- 								<span class="sig-color">434456</span> 명 -->
<!-- 							</div> -->
<!-- 						</div> -->

						<div class="area_2_">
							<span>상품판매</span>
                     <div class="data">
                        <span class="sig-color">${shop.shop_saleCount }회</span> 
                     </div>
						</div>


					</div>
					<div class="area3" style="overflow-y:hidden;"><div class="shop_intro">${shop.shop_intro }</div></div>
					
					
					
					<div class="area4">
					  <c:choose>
                      <c:when test="${shop.member_id eq sessionScope.id}">
                         <button class="update" id="updateIntro">소개글 수정</button>
                      </c:when>
                      <c:otherwise>
                            <a  id="report" style="color:#ccc; font-size:13px; cursor: pointer;">
                         <img src="resources/images/siren.svg" width="14" height="14"> 신고하기
                     </a>
                         </c:otherwise>
                  </c:choose>


					</div>
					
					
					<div id="popup">
                    <div id="popmenu">
                        <div class="pop_cont">
                            <div class="pop_title">신고하기</div>
                            <button class="exit" style="cursor: pointer;">
                                <img src="resources/images/exit.svg" width="24" height="24">
                            </button>
                        </div>
                        <form action="report_shop" method="post">
                            <div class="textarea">
                                <textarea
                                    placeholder="신고 내용을 직접 작성해주세요.자세하게 적어주시면 신고처리에 큰 도움이 됩니다."
                                    required name="report_content"></textarea>
                                    <input type="hidden" name="member_id" value="<c:out value="${shop.member_id}"/>">
                            </div>
                                <button type="submit" class="report_btn">등록</button>
                        </form>
                    </div>
                </div>
				
					
					
				
					


				</div>


			</div>


		</section>




		<section id="sec2">

			<div id="tabs">
<!-- 				<ul> -->
<!-- 					<li class="me on"><a href="#tabs-1">상품 </a></li> -->
<!-- 					<li class="me"><a href="#tabs-2">상점문의</a></li> -->

<!-- 					<li class="me"><a href="#tabs-3">찜</a></li> -->
<!-- 					<li class="me"><a href="#tabs-4">상점후기</a></li> -->


<!-- 				</ul> -->
				<ul>
            <li class="me on"><a href="#tabs-1">상품 ${shop.shop_itemCount } </a></li>
            <li class="me"><a href="#tabs-3">찜 ${shop.shop_likesCount }</a></li>
            <li class="me"><a href="#tabs-4">상점후기 ${shop.shop_shopReviewCount }</a></li>
         </ul>


				<div id="tabs-1">
					<div class="product_count">
						<div>
							상품<span class="sig-color"> ${shop.shop_itemCount }</span>
						</div>
					</div>

					<div class="product_area">
						<c:forEach var="item" items="${shopItemList }">
                  <div class="content" onclick="location.href='itemDetail?item_idx=${item.item_idx }'" style="cursor: pointer;">
                     <div class="content_top">
                        <img src="resources/itemUpload/${item.item_img }" width="246" height="200">
                     </div>
   
                     <div class="content_bottom">
                        <div class="con_title">
                           ${item.item_subject }
                        </div>
                        <div class="con_text">
                           <div class="con_price">
                              ${item.item_price }원
                           </div>
                           <div class="con_time">
                              <span>${item.item_date_format }</span>
                           </div>
                        </div>
                     </div>
                  </div>
               </c:forEach>

						<!-- content -->

					</div>

				</div>
				
				






<!-- 				<div id="tabs-2"> -->
<!-- 					<p class="info2">상품문의</p> -->

<!-- 					<div class="replyform"> -->
<!-- 						<form action="" method="post"> -->
<!-- 							<textarea placeholder="상품문의 입력 (100글자 내외)" name="reply"></textarea> -->

<!-- 							<input type="submit" value="등록"> -->
<!-- 						</form> -->
<!-- 					</div> -->

<!-- 					<div class="replyArea"> -->
<!-- 						<a href=""><img src="resources/images/symbol.svg" width="48" -->
<!-- 							height="48"></a> -->

<!-- 						<div class="replyText"> -->
<!-- 							<div class="set"> -->
<!-- 								<div class="txt1">시소알림센터</div> -->

<!-- 								<div class="txt1-2">0초전</div> -->
<!-- 							</div> -->

<!-- 							<div class="txt2">[공지글] 이 상품은 청소년 보호법으로 제재되었습니다.</div> -->


<!-- 							<div class="txt3"> -->
<!-- 								댓글달기 -->
<!-- 								<div class="report"> -->
<!-- 									<img src="resources/images/siren.svg" width="14" height="14"><span -->
<!-- 										class="report_txt">신고하기</span> -->
<!-- 								</div> -->
<!-- 							</div> -->

<!-- 						</div> -->

<!-- 					</div> -->
<!-- 				</div> -->
				
					
			
				
				<div id="tabs-3">
					<div class="like_title">
						<div class="like_count">
							찜 <span class="sig-color">${shop.shop_likesCount }</span>
						</div>
					</div>

					<div class="like_list">
							
				<c:forEach var="likesItem" items="${likesList }">
            <div class="like_box">
               

                   
               <a href="itemDetail?item_idx=${likesItem.item_idx }" class="like_content">
                <div class="lc1" style="width: 50px; height: 50px;" onclick="likeDelete(${likesItem.item_idx})">
<!-- 									<img src="resources/images/menu.svg" width="24" height="24" -->
<!-- 										id="delete_like"> -->
								</div>
                        
                  <div class="lc2">
                             <img src="resources/itemUpload/${likesItem.item_img }" width="138">
                           <div class="over_img">
                          <c:choose>
                                 <c:when test="${likesItem.item_isDeliveryFree == true}">
                                    배송비 포함
                                 </c:when>
                                 <c:otherwise>
                                    배송비 미포함
                                 </c:otherwise>
                             </c:choose>
                           </div>
                           <div class="absol"></div>
                  </div>
                           
                  <div class="lc3">
                  <div class="like_pro_info1">
                     <div class="info1_1" style="text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;">
                        ${likesItem.item_subject }
                     </div>
                     <div class="info1_2">
                     <div class="info1_2_data">
                        ${likesItem.item_price }원
                     </div>
                     </div>
                     <div class="info1_3">
                        ${likesItem.item_date_format }
                            </div>
                  </div>
                  <div class="like_pro_info2">
                     <img src="./resources/images/location.svg" width="15" height="17">
                        ${likesItem.item_region }
                  </div>
                  </div>
            </a>
         </div>
         </c:forEach>
			


					</div>   
<!-- 					likeList -->

				</div>








				<div id="tabs-4">
					<div class="review_title">
						<div class="title_txt">
							상점후기 <span class="sig-color">${shop.shop_shopReviewCount }</span>
						</div>
					</div>


					<div class="review_content">
						<c:forEach var="shopReview" items="${shopRivewList }">
            <div class="review_box">
               <a class="review_img"  onclick="location.href='shop?nickname=${shopReview.shopReview_nickname }'" style="cursor: pointer;">
                  <img src="./resources/images/default_shop_img.svg" width="60" height="60">
               </a>
                       
               <div class="review_txt" style="flex:1 1 0%">
               <div class="rv_txt1">
                  <div class="rv_title" onclick="location.href='shop?nickname=${shopReview.shopReview_nickname }'" style="cursor: pointer;">
                     ${shopReview.shopReview_nickname}
                     <div class="con_time">
                        <span>${shopReview.shopReview_date_format }</span>
                     </div>
                  </div>
                  <a class="rv_star">
                      <div class="star_img">
                         <c:forEach begin="1" end="${shopReview.shopReview_star }" step="1">
                           <img src="./resources/images/star.svg" width="15" height="14">
                         </c:forEach>
                     </div>
                  </a>
               </div>
               
               <div style="color:#396afc; display: inline-block; padding: 2px 5px; cursor: pointer;" onclick="location.href='itemDetail?item_idx=${shopReview.item_idx }'">
                           ${shopReview.item_subject } >
               </div>
                           
               <div class="rv_txt2">
                           ${shopReview.shopReview_content }
               </div>
                           
               <div class="rv_txt3">
                        <c:if test="${shopReview.shopReview_id ne sessionScope.id}">
                            <a id="reportshopReview${shopReview.shopReview_idx}" style="color:#ccc; font-size:13px; cursor: pointer;">
                                 <img src="./resources/images/siren.svg" width="14" height="14"> 신고하기
                            </a>
                        </c:if>
                    </div>

                    <div id="popup2" class="shopReview${shopReview.shopReview_idx }">
                        <div id="popmenu">
                            <div class="pop_cont">
                                <div class="pop_title">신고하기</div> 
                                <button class="exit2" style="cursor: pointer;">
                                    <img src="resources/images/exit.svg" width="24" height="24">
                                </button>
                            </div>
                            <form action="report_shopReview" method="post">
                                <div class="textarea">
                                    <textarea
                                        placeholder="신고 내용을 직접 작성해주세요.자세하게 적어주시면 신고처리에 큰 도움이 됩니다."
                                        required name="report_content"></textarea>
                                        <input type="hidden" name="shopReview_idx" value="<c:out value="${shopReview.shopReview_idx}"/>">
                                </div>
                                    <button type="submit" class="report_btn">등록</button>
                            </form>
                        </div>
                    </div>
               </div>
            </div>
         </c:forEach>


					</div>


				</div>




			</div>





		</section>
	</div>


	<script type="text/javascript">
	function openTalk() {
		window.open("chatList", "PopupWin",
				"width=408,height=500,resizable=no,scrollbars=no");
		window.resizeTo(405, 500);
	}

	function likeDelete(item_idx){
		alert(item_idx);
	}
	
	</script>

</body>
</html>