<%@page import="java.util.Date"%>
<%@page import="org.springframework.ui.Model"%>
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

<title>시소마켓 | 상품상세</title>
<!--[if lt IE 9]>
      <script src="js/html5.js"></script>
   <![endif]-->

<link href="resources/css/common.css" rel="stylesheet" type="text/css">
<link href="resources/css/header.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="resources/css/product.css">




<script src="resources/js/jquery-3.1.1.min.js"></script>
<script src="resources/js/toggle.js"></script>
<style>
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

#popmenu2 {
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

[class* = popup2_]{
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

.pop_cont2 {
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

.pop_title2 {
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

.pop_cont2 button {
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

#popmenu2 .textarea textarea {
   width: 100%;
   height: 300px;
   resize: none;
   font-sizle: 20px;
}

#popmenu .textarea {
   margin-bottom: 20px;
}

#popmenu2 .textarea {
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

#popmenu2 .report_btn2 {
   background: #396afc;
   border: none;
   padding: 10px 20px;
   color: #fff;
   cursor: pointer;
   font-weight: bold;
}

.on {
   display: none;
}
</style>

<script type="text/javascript">
$(function(){
   

$("#report").click(function(){
    $("#popup").fadeIn();
});
$(".exit").click(function(){
    $("#popup").fadeOut();
});   

// $('.txt3 .report2').click(function(){
//    $('#popup2').fadeIn();
// });

$(".exit2").click(function(){
    $("#popup2").fadeOut();
});   


});


   

   
   


$(document).ready(function () {
   
      
      
      var member_id = '<%=(String) session.getAttribute("id")%>';
         var item_idx = '<%=(String) request.getParameter("item_idx")%>';
      var isLike = '${isLike}';
      
      if(isLike == 'like'){
         console.log('검정배경')
         $('.btn1').css('background', 'black');
      }
      
      console.log('아이디 : ' + member_id);
      console.log('isLike - ' + isLike);
      
      $('.btn1').click(function(){
         
         if(member_id == "null") {
            alert("로그인 후 사용가능한 기능입니다!"); 
            
         } else  {
            
            if(isLike == "notLike") {
               isLike = 'like';
               jQuery('#likesImg').attr("src", "resources/images/redheart.svg");
               $.ajax({
                  url: "item/changeToRed",
                  type: "POST",
                  data: {"item_idx": item_idx, "member_id": member_id},
                  success: function(rdata){
                     console.log('받아온 likesCount 값' + rdata);
                     $('#likesCount1').html(' ' + rdata + ' ');
                     $('#likesCount2').html(' ' + rdata + ' ');
                     $('.btn1').css('background', 'black');
                    jQuery('#likesimg').attr("src","resources/images/redheart.svg");
                  }
               });
            } else {
               isLike = 'notLike';
               jQuery('#likesImg').attr("src", "resources/images/whiteheart.svg");
               $.ajax({
                  url: "item/changeToGray",
                  type: "POST",
                  data: {"item_idx": item_idx, "member_id": member_id},
                  success: function(rdata){
                    console.log('받아온 likesCount 값' + rdata);
                     $('#likesCount1').html(' ' + rdata + ' ');
                     $('#likesCount2').html(' ' + rdata + ' ');
                     $('.btn1').css('background', '#CCCCCC');
                     jQuery('#likesimg').attr("src","resources/images/whiteheart.svg");
                  }
               });
            }
         }
      });
      
      // 댓글등록
      // member_id => 세션에서 가져온 id
      // textarea 내용 받아와 지는지 확인
      
       $('#commentForm').submit(function(event){
          // 확인
          event.preventDefault(); // 자동 submit 막음
          // 전송 여부 boolean 값 
          // 초기값은 false로 셋팅을 한다. 
          var isSubmit = false;

          var replyVal = jQuery('textarea#reply').val();
          console.log(replyVal);
          console.log(jQuery('textarea#reply').val());
//           alert('동작함');
          
          if(member_id == "null") {
               alert("로그인 후 사용가능한 기능입니다!"); 
               return false;
          }
          
          if(replyVal == ""){
             alert('내용을 입력해주세요!');
             return false;
          }
          
            $.ajax({
                  url: "item/insertComment",
                  type: "POST",
                  dataType: 'json',
                  contentType : 'application/json',
                  data: JSON.stringify({
                     "item_idx": item_idx, "member_id": member_id, "comment_content":replyVal
               }),
                  success: function(rdata){
                     var str = "";
                     
                     if(rdata != ""){
                        $(rdata).each(function(){
                        console.log(this)
                        str += "<div class=" + "'replyArea'" + ">"
                        + "<a href=" + "'shop?nickname=" + this.member_nickname + "'>" + "<img src=" + "'resources/images/default_shop_img.svg'" + "width=" + "'45'" +  "height=" + "'45'" + ">" + "</a>"
                        + "<div class=" + "'replyText'" + ">"
                        + "<div class=" + "'set'" + ">"
                        + "<div class=" + "'txt1'" + ">" + this.member_nickname + "</div>"
                        + "<div class=" + "'txt1-2'" + ">" + "방금전" + "</div>"
                        + "</div>"
                        + "<div class=" + "'txt2'" + ">" + this.comment_content + "</div>"
                        + "<div class=" + "'txt3'" + ">"
                        + "<div class=" + "'report'" + "onclick=" + "'reportComment(" + this.comment_idx + ")'" + ">"
                        + "<img src=" + "'resources/images/siren.svg'" + "width=" + "'14'" + "height=" + "'14'" + ">"
                        + "<span class=" + "'report_txt'" + ">" + "신고하기" + "</span>"
                        + "</div>"
                        + "<div class=" + "'report'" +  "id=" + "'delComment'" + "onclick=" + "'deleteComment(" + this.comment_idx + ")'" + ">"
                        + "<img src=" + "'resources/images/trash-can.svg'" + "width=" + "'14'" + "height=" + "'14'" + ">"
                        + "<span class=" + "'report_txt'" + ">" + "삭제하기" + "</span>"
                        + "<input type=" + "'hidden'" + "id=" + "'cMember_id" + this.comment_idx  + "'   value=" + this.member_id + ">"
                        + "<input type=" + "'hidden'" + "id=" + "'comment_idx" + this.comment_idx + "'   value=" + this.comment_idx + ">"
                        + "</div>"
                        + "</div>"
                        + "<div id=" + "'popup2'" + "class=" + "'popup2_" + this.comment_idx + "'  >"
                        + "<div id=" + "'popmenu2'" + ">"
                        + "<div class=" + "'pop_cont2'" + ">"
                        + "<div class=" + "'pop_title2'" + ">" + "신고하기" + "</div>"
                        + "<button class=" + "'exit2'" +  "style=" + "'cursor: pointer;'" + "onclick=" + "'closeReportComment(" + this.comment_idx + ")'" + ">"
                        + "<img src=" + "'resources/images/exit.svg'" + " width=" + "'24'" + " height=" + "'24'" + ">"
                        + "</button>"
                        + "</div>"
                        + "<form action=" + "'report_comment'" + " method=" + "'post'" + ">"
                        + "<div class=" + "'textarea'" + ">"
                        + "<textarea placeholder=" + "'신고 내용을 직접 작성해주세요.자세하게 적어주시면 신고처리에 큰 도움이 됩니다.'"
                  + "   required name=" + "'report_content'" + ">" + "</textarea>"
                  + "<input type=" + "'hidden'" + " name=" + "'comment_idx'" + "id=" + this.comment_idx + " value=" + this.comment_idx + ">"
                  + "<input type=" + "'hidden'" + " name=" + "'item_idx'" + " value=" + item_idx + ">"
                  + "</div>"
                  + "<button type=" + "'submit'" + "class=" + "'report_btn2'" + ">" + "등록" + "</button>"
                  + "</form>"
                  + "</div>"
                  + "</div>"
                        + "</div>"
                        + "</div>"
                        
                        });
                        $(".replyContent").prepend(str);
                        
                     }
                     
                  }
               });
          return false;
       });
      
      
      // ===========================================================================================
         
   }); // jquery
   
      
      
   function deleteComment(comment_idx){
      $(document).ready(function () {
//       alert(strr.indexOf('t'));
//       alert(strr.substring(10));
//       alert("mid : " + mid);
//       alert(comment_idx_id);
//       alert(comment_idx + "<<-- 댓글번호임");
//       alert(idx); // 확인됨
       var item_idx = '<%=(String) request.getParameter("item_idx")%>';
       var member_id = '<%=(String) session.getAttribute("id")%>';
      var cMember_id_id = "cMember_id" + comment_idx;
      var comment_idx_id = "comment_idx" + comment_idx;
      var mid = $("#" + cMember_id_id).val(); // 글쓴이 ID
      
      if(member_id != mid){
         alert('삭제 권한이 없습니다.');
         return false;
      }else{
         $.ajax({
               url: "item/deleteComment",
               type: "POST",
               dataType: 'json',
               contentType : 'application/json',
               data: JSON.stringify({
                  "item_idx": item_idx, "member_id": member_id, "comment_idx":comment_idx
            }),
            success: function(rdata){
                    var str = "";
                    
                    if(rdata != ""){
                       $(rdata).each(function(){
                       console.log(this)
                       str += "<div class=" + "'replyArea'" + ">"
                       + "<a href=" + "'shop?nickname=" + this.member_nickname + "'>" + "<img src=" + "'resources/images/default_shop_img.svg'" + "width=" + "'45'" +  "height=" + "'45'" + ">" + "</a>"
                       + "<div class=" + "'replyText'" + ">"
                       + "<div class=" + "'set'" + ">"
                       + "<div class=" + "'txt1'" + ">" + this.member_nickname + "</div>"
                       + "<div class=" + "'txt1-2'" + ">" + "방금전" + "</div>"
                       + "</div>"
                       + "<div class=" + "'txt2'" + ">" + this.comment_content + "</div>"
                       + "<div class=" + "'txt3'" + ">"
                       + "<div class=" + "'report'" + "onclick=" + "'reportComment(" + this.comment_idx + ")'" + ">"
                       + "<img src=" + "'resources/images/siren.svg'" + "width=" + "'14'" + "height=" + "'14'" + ">"
                       + "<span class=" + "'report_txt'" + ">" + "신고하기" + "</span>"
                       + "</div>"
                       + "<div class=" + "'report'" +  "id=" + "'delComment'" + "onclick=" + "'deleteComment(" + this.comment_idx + ")'" + ">"
                       + "<img src=" + "'resources/images/trash-can.svg'" + "width=" + "'14'" + "height=" + "'14'" + ">"
                       + "<span class=" + "'report_txt'" + ">" + "삭제하기" + "</span>"
                       + "<input type=" + "'hidden'" + "id=" + "'cMember_id" + this.comment_idx  + "'   value=" + this.member_id + ">"
                       + "<input type=" + "'hidden'" + "id=" + "'comment_idx" + this.comment_idx + "'   value=" + this.comment_idx + ">"
                       + "</div>"
                       + "</div>"
                       + "<div id=" + "'popup2'" + "class=" + "'popup2_" + this.comment_idx + "'  >"
                       + "<div id=" + "'popmenu2'" + ">"
                       + "<div class=" + "'pop_cont2'" + ">"
                       + "<div class=" + "'pop_title2'" + ">" + "신고하기" + "</div>"
                       + "<button class=" + "'exit2'" +  "style=" + "'cursor: pointer;'" + "onclick=" + "'closeReportComment(" + this.comment_idx + ")'" + ">"
                       + "<img src=" + "'resources/images/exit.svg'" + " width=" + "'24'" + " height=" + "'24'" + ">"
                       + "</button>"
                       + "</div>"
                       + "<form action=" + "'report_comment'" + " method=" + "'post'" + ">"
                       + "<div class=" + "'textarea'" + ">"
                       + "<textarea placeholder=" + "'신고 내용을 직접 작성해주세요.자세하게 적어주시면 신고처리에 큰 도움이 됩니다.'"
                  + "   required name=" + "'report_content'" + ">" + "</textarea>"
                  + "<input type=" + "'hidden'" + " name=" + "'comment_idx'" + "id=" + this.comment_idx + " value=" + this.comment_idx + ">"
                  + "<input type=" + "'hidden'" + " name=" + "'item_idx'" + " value=" + item_idx + ">"

                  + "</div>"
                  + "<button type=" + "'submit'" + "class=" + "'report_btn2'" + ">" + "등록" + "</button>"
                  + "</form>"
                  + "</div>"
                  + "</div>"
                       + "</div>"
                       + "</div>"
                       });
                       $(".replyContent").html(str);
                       
                       }else{
                          $(".replyContent").html("");   
                          }
                    }
                    
                 });//ajax
            
            
      } // ID 일치시 
      
         
         
         
      }); // jquery
   } // function
   
   
   

   $(document).ready(function () {
      var item_idx = '<%=(String) request.getParameter("item_idx")%>';
      var item_state = $('#item_state').val();
      $('.btn3').click(function(){
//          alert('클릭');
//          alert(item_state);
         if(item_state == 1){
            alert('안돼 돌아가');
            return false;
         }else if(${empty sessionScope}){
        	 alert('로그인 후 이용해주세요.');
        	 return false;
         }else{
            location.href="order?item_idx=" + "" + item_idx + "";
         }
      });
      
      
      
      
      
      
          
      
   });
   
   // 댓글 신고 창 띄우기
   function reportComment(comment_idx){
      
      var member_id = '<%=(String) session.getAttribute("id")%>';
      
      if(member_id == "null"){
         alert('돌아가세요');
         return false;
      }
      
//       alert(comment_idx);
      $(".popup2_" + comment_idx).fadeIn();
   }
   
   // 댓글 신고 창 닫기
   function closeReportComment(comment_idx){
//       alert(comment_idx);
      $(".popup2_" + comment_idx).fadeOut();
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




   <section id="sec1">
      <div id="wrap">
         <c:forEach items="${list}" var="list">
            <article class="product_info">
               <div class="img_area">
                  <img src="resources/itemUpload/${list.item_img}" width="430"
                     height="430">

               </div>

            </article>


            <article class="product_detail">
               <div class="txt_area">

                  <h2 class="title">${list.item_subject}</h2>
                  <h1 class="price">${list.item_price}원</h1>


               </div>

               <ul class="db_area">
                  <li><img src="resources/images/grayheart.svg"><span
                     class="small" id="likesCount1"> ${likesCount} </span></li>
                  <li><img src="resources/images/eye.svg"><span
                     class="small"> ${list.item_readcount}</span></li>
                  <li><img src="resources/images/clock.svg"><span
                     class="small"> ${list.item_date_format} </span></li>
                                 <c:if test="${sessionScope.id ne null }">
                  <li id="report" style="cursor: pointer;"><img
                        src="resources/images/siren.svg"><span class="small">신고하기</span>
                  </li>
                  </c:if>
               </ul>
               <div id="popup">
                  <div id="popmenu">
                     <div class="pop_cont">
                        <div class="pop_title">신고하기</div>
                        <button class="exit" style="cursor: pointer;">
                           <img src="resources/images/exit.svg" width="24" height="24">
                        </button>
                     </div>
                     <form action="report_item" method="post">
                        <div class="textarea">
                           <textarea
                              placeholder="신고 내용을 직접 작성해주세요.자세하게 적어주시면 신고처리에 큰 도움이 됩니다."
                              required name="report_content"></textarea>
                              <input type="hidden" name="item_idx" value="<c:out value="${list.item_idx}"/>">
                              
                        </div>

                        <button type="submit" class="report_btn">등록</button>
                     </form>
                  </div>
               </div>


               <div class="status_area">
                  <ul class="status">
                     <li>*상품상태</li>
                     <li>*교환여부</li>
                     <li style="margin-bottom: 24px;">*배송비</li>
                     <li>*거래지역</li>
                  </ul>

                  <ul class="status_detail">
                     <c:choose>

                        <c:when test="${list.item_isUsed == true }">
                           <li>중고</li>
                        </c:when>
                        <c:otherwise>
                           <li>새상품</li>
                        </c:otherwise>
                     </c:choose>
                     <c:choose>
                        <c:when test="${list.item_isExchange == true }">
                           <li>교환가능</li>
                        </c:when>
                        <c:otherwise>
                           <li>교환불가</li>
                        </c:otherwise>
                     </c:choose>
                     <c:choose>
                        <c:when test="${list.item_isDeliveryFree == false }">
                           <li style="color: #396afc">배송비없음</li>
                        </c:when>
                        <c:otherwise>
                           <li style="color: #396afc">${list.item_charge }원</li>
                        </c:otherwise>
                     </c:choose>
                     <!--                   <li style="color: #396afc">배송비없음</li> -->
                     <li style="color: skyblue;">${list.item_region }</li>

                  </ul>

               </div>




               <c:choose>
                  <c:when test="${((empty sessionScope) || (sessionScope.id ne list.member_id)) && list.item_state ne 1}">

                     <div class="btn_area">
                        <div class="btn">
                           <button class="btn1">
                              <c:choose>
                                 <c:when test="${isLike == 'like' }">
                                    <img src="resources/images/redheart.svg" id="likesImg">
                                 </c:when>
                                 <c:otherwise>
                                    <img src="resources/images/whiteheart.svg" id="likesImg">
                                 </c:otherwise>
                              </c:choose>
                              &nbsp; <span>찜</span> <span id="likesCount2">${likesCount}</span>
                           </button>
                        </div>



                        <!-- 찜버튼끝        redheart.svg -->
						
                        <button class="btn2" id="contact"
                           onclick="openTalk2(${list.item_idx})">연락하기</button>
                        <button class="btn3">바로구매</button>
                        <input type="hidden" id="item_state" value="${list.item_state }">
                     </div>
                     
                     
                  </c:when>
                  
                  <c:when test="${list.item_state == 1}">
                  <div class="btn_area" style="background: #08A600;">
                      <p style="color: #fff">판매 완료된 상품입니다.</p>
                     </div>
                  </c:when>

                  <c:otherwise>
                  
<%--                   <c:choose> --%>

                     <div class="btn_area" style="background: #ff3399;">
                        <a href="sell#tabs-2"><p style="color: #fff">내상점관리</p></a>
                     </div>
<%--                      </c:choose> --%>
                  </c:otherwise>

               </c:choose>

               <!--  2번째 -->





            </article>
      </div>

   </section>

   <section id="sec2">
      <div id="wrap">
         <h2>연관상품</h2>
         <article class="simillar">
         <c:forEach var="rList" items="${rList }" >
         
            <div class="si_product">
               <a href="itemDetail?item_idx=${rList.item_idx }">
                  <div class="img_area2">
                     <img src="resources/itemUpload/${rList.item_img}" width="155" height="155">
                        
                  </div>

                  <div class="txt_area2">${rList.item_subject }</div>
               </a>
            </div>

            
         </c:forEach>
         </article>

      </div>

   </section>


   <section id="sec3">
      <div id="wrap">

         <div class="left_area">
            <div class="l1">
               <p class="info">상품정보</p>

               <p class="info_content" style="white-space:pre-line;"><c:out value="${list.item_detail}" /></p>

               <ul class="categoryand">
                  <li><p>
                        <img src="resources/images/category2.svg"><span>카테고리</span>
                     </p>
                     <p class="p2">${list.category_name } > </p></li>

                  <li><p>
                        <img src="resources/images/tag.svg"><span>상품태그</span>
                     </p>
                     <p class="p2">
                        <c:forEach var="tagNames" items="${tagNames}">
                        # ${tagNames}
                        
                     </c:forEach>

                     </p></li>

               </ul>

               <p class="info2">상품문의</p>

               <div class="replyform">
                  <!-- replyform -->
                  <form action="" method="post" id="commentForm">
                     <textarea placeholder="상품문의 입력 (100글자 내외)" name="reply"
                        style="resize: none;" id="reply"></textarea>
                     <input type="submit" value="등록">
                  </form>
               </div>
               <div class="replyArea">
                  <a href=""><img src="resources/images/symbol.svg" width="48"
                     height="48"></a>

                  <div class="replyText">
                     <div class="set">
                        <div class="txt1">시소알림센터</div>

                        <div class="txt1-2">0초전</div>
                     </div>

                     <div class="txt2">[공지글] 직거래는 사기 예방의 최선책.</div>


                



                  </div>

               </div>
               <div class="replyContent">



                  <!-- 여기부터 댓글 있으면 반복하기 -->
                  <c:forEach items="${cList}" var="cList">
                  
                     <div class="replyArea">
                        <a href="shop?nickname=${cList.member_nickname }"><img src="resources/images/default_shop_img.svg"
                           width="45" height="45"></a>

                        <div class="replyText">
                           <div class="set">
                              <div class="txt1">${cList.member_nickname }</div>

                              <div class="txt1-2">${cList.comment_date_format}</div>
                           </div>

                           <div class="txt2">${cList.comment_content }</div>


                           <div class="txt3">
<%--                            <c:if test="${sessionScope.id ne cList.member_id}"> --%>
                              <div class="report" onclick="reportComment('${cList.comment_idx}')">
                                 <img src="resources/images/siren.svg" width="14" height="14"><span
                                    class="report_txt">신고하기</span>
                              </div>
<%--                               </c:if> --%>
                              
                              <div class="report" id="delComment" onclick="deleteComment('${cList.comment_idx}')">
                                           <img src="resources/images/trash-can.svg" width="14"
                                           height="14"><span class="report_txt">삭제하기</span>
                                           <input type="hidden" id="cMember_id${cList.comment_idx }" value="${cList.member_id}" >
                                           <input type="hidden" id="comment_idx${cList.comment_idx }" value="${cList.comment_idx }">
                                      </div>
                              
                           </div>
                           <div id="popup2" class="popup2_${cList.comment_idx}">
                              <div id="popmenu2">
                                 <div class="pop_cont2">
                                    <div class="pop_title2">신고하기</div>
                                    <button class="exit2" style="cursor: pointer;" onclick="closeReportComment('${cList.comment_idx}')">
                                       <img src="resources/images/exit.svg" width="24" height="24" style="border-radius: 100%">
                                    </button>
                                 </div>
                                 <form action="report_comment" method="post">
                                   <div class="textarea">
                                       <textarea
                                          placeholder="신고 내용을 직접 작성해주세요.자세하게 적어주시면 신고처리에 큰 도움이 됩니다."
                                          required name="report_content"></textarea>
                                          <input type="hidden" name="comment_idx" id="${cList.comment_idx }" value="${cList.comment_idx}">
                                          <input type="hidden" name="item_idx" value="${param.item_idx }">
                           
                                    </div>

                                    <button type="submit" class="report_btn2">등록</button>
                                 </form>
                              </div>
                           </div>
                           
                           




                        </div>

                     </div>
                     
                  </c:forEach>
               </div>


            </div>
         </div>

         </c:forEach>


         <div class="right_area">
            <div class="r1">
               <div class="r_title">상점정보</div>

            </div>
            <c:forEach var="sList" items="${sList }">
            
            <div class="shop_area">
               <div class="shop_name">
               
                  <a class="mar" href="shop?nickname=${sList.member_nickName}"> <img
                     src="resources/images/default_shop_img.svg" width="48"
                     height="48">
                  </a>

                  <div class="shop_info">
                     <a href="shop?nickname=${sList.member_nickName}" class="shop_title">${sList.member_nickName}</a>
                  </div>
                  <div class="shop_text">
                     <a href="shop?nickname=${sList.member_nickName}">상품${sList.shop_itemCount }</a>
                  </div>


               </div>


               <div class="more">
                  <c:forEach var="pList" items="${pList }">
               
                  <div class="more_img">
                     <a href="itemDetail?item_idx=${pList.item_idx }"><img src="resources/itemUpload/${pList.item_img }" width="120" height="96">
                        <div class="more_img_txt">
                           <span>${pList.item_price} 원</span>
                        </div> </a>

                  </div>
                  </c:forEach>

               
               </div>
               <c:if test="${3 < sList.shop_itemCount}">
            
               <div class="more_link">
                  <a href="shop?nickname=${sList.member_nickName }" class="link_txt"> <span class="sig-color"
                     style="margin-right: 2px; margin-top: 5px;">${sList.shop_itemCount - 3 }개</span>상품 더보기 >
                  </a>
               </div>
               </c:if>

               <div class="review_area">
                  <div class="rev_title">
                     상점후기 <span class="sig-color">${sList.shop_reviewCount }</span>
                  </div>

                  <div class="rev_content">
               <c:forEach var="reviewList" items="${reviewList }">
                  
                     <div class="rev_box">
                        <a class="rev_img" href=""> <img
                           src="resources/images/default_shop_img.svg" width="32"
                           height="32">
                        </a>

                        <div class="rev_txt">
                           <div class="rvtxt1">
                              <a href="shop?nickname=${reviewList.shopReview_nickname}" class="goshop">${reviewList.shopReview_nickname}</a>

                              <div class="rev_date">${reviewList.shopReview_date_format}</div>


                           </div>

                           <div class="rvtxt2">
                           <div class="star_box">
                                 <c:forEach begin="1" end="${reviewList.shopReview_star }" step="1">
                                    <img src="resources/images/star.svg" width="15" height="14">
                                 </c:forEach>
                              </div>

                           </div>

                           <div class="rvtxt3">${reviewList.shopReview_content }</div>

                        </div>

                     </div>
                     </c:forEach>
                     <!-- 리뷰1개 -->

                     


                  </div>

                  <div class="rev_link">

                  <c:choose>
                  <c:when test="${sList.shop_reviewCount ne 0 }">
                  
                     <a href="shop?nickname=${sList.member_nickName}#tabs-4">상점후기 더보기 ></a>
                  
                  </c:when>
                  <c:otherwise>
                     등록된 후기가 없습니다.
                  </c:otherwise>
                  </c:choose>
                  </div>




               </div>


            </div>
            
            </c:forEach>
            
         </div>

      </div>


   </section>

   <footer id="footer">

      <div id="wrap">
         <div class="copy">
            <p>
               ⓒ2021 Team <span class="sig-color">SYSO</span>
            </p>
            <p>Designed by Kys</p>
         </div>
      </div>
   </footer>
   <script type="text/javascript">
function openTalk2(item_idx){
   window.open("<c:url value='chatting?item_idx="+item_idx+"'/>", "Message", "width=360, height=575, resizable=no, scrollbars=no" );
   window.resizeTo(353, 575);

   
}

function openTalk() {
	window.open("chatList", "PopupWin",
			"width=408,height=500,resizable=no,scrollbars=no");
	window.resizeTo(405, 500);
}
</script>

</body>
</html>