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
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />

<title>시소마켓 | 내상점</title>
  <!--[if lt IE 9]>
      <script src="js/html5.js"></script>
   <![endif]-->


<!-- minsu  minsu  minsu 파일 경로 변경 minsu  minsu  minsu  -->
<link href="resources/css/common.css" rel="stylesheet" type="text/css">
<link href="resources/css/header.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="resources/css/center.css">
<script src="resources/js/jquery-3.1.1.min.js"></script>
    <script src="resources/js/jquery-ui.min.js"></script>
<script src="resources/js/toggle.js"></script>
<!-- minsu  minsu  minsu 파일 경로 변경 minsu  minsu  minsu  -->


    <!--   reources/  --> 
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
    <div id="container">
        
           <div id="tabs">
      <ul class="menu">
          <li class="me on"><a  href="#tabs-1">공지사항</a></li>
          <li class="me"><a href="#tabs-2">운영정책</a></li>
          <li class="me"><a id="tab3" href="#tabs-3" >질문하기</a></li>

           </ul>
               
               
               <div id="tabs-1">
                   <button class="accordion">[공지] 대리구매 형태의 거래 진행 시 주의 안내</button>
            <div class="panel">
                <p>안녕하세요? <span class="sig-color">시소마켓</span>입니다.

 

대리구매 방식으로 거래가 진행될 경우, 실물이 확보된 일반거래보다 사기 피해에 노출될 위험이 크므로 고객님들께서는 각별히 주의하여 거래 해 주시기 바랍니다.<br>

 

                    <span style="color: red">대리구매란?</span><br>

판매자가 물품이 확보되지 않은 상태에서 정상가보다 저렴하게 대신 결제 해 주는 방식으로,<br>

티켓 대리 예매, 배송지 변경 방식을 이용한 타 사이트 대리 결제 등이 이에 해당 됩니다.<br>

                    <span style="color: red">※판매자가 임의 취소가 가능하기에 사기 피해 주의 필요</span><br>

 

 물품을 받기 전 판매자의 계좌로 직접 입금하는 방식이 아닌, 

                    구매확정 후 정산이 진행되는 '<span class="sig-color">시소페이</span>'로 거래하시어 피해를 예방 하시길 권고 드립니다.<br>

 

깨끗한 거래 환경이 유지될 수 있도록 최선을 다 하겠습니다.<br>

 

감사합니다.</p>
            </div>
                   
                   

            <button class="accordion">[공지] 온라인 게임 및 개인정보를 활용한 각종 거래에 대한 금지 안내</button>
            <div class="panel">
              <p>안녕하세요? 시소마켓입니다.

 

온라인 게임과 관련된 각종 거래 및 개인정보 활용이 동반되는 이용권 거래는 금지품목으로 지정하여 운영되고 있습니다.<br>

그러나 지속적으로 거래가 발생되고 있어 다시 한 번 안내 드리오니, 고객님들의 적극적인 협조 부탁 드립니다.<br><br>

 

                  <span style="font-weight: bold;">[온라인 게임과 관련된 각종 거래]</span>
<br>
게임 산업 진흥에 관한 법률 제32조 불법게임물 등의 유통 금지 조항에 기반하여,<br>

                  <span style="color: red">계정 및 아이템 거래, 대리 육성, 게임코드 및 쿠폰거래 등 온라인 게임과 관련된 각종 거래</span>를 금지하고 있습니다.<br>

 

                  <span style="font-weight: bold;">[개인정보를 활용한 이용권 거래]</span> 
                  <br>

개인 정보를 활용하여 이용 가능한 상품들의 각종 거래를 금지하고 있습니다.<br> 

                  <span style="color: red">동영상 콘텐츠 서비스와 관련된 각종 이용권 및 쿠폰, 음원 사이트 이용권 및 쿠폰, 각종 커뮤니티 계정, 온라인 강의 계정, 인증번호 공유</span> 등이 이에 해당됩니다.

※ 서로 개인정보를 공유하지 않더라도 각 운영사에서 공식적으로 허용하지 않은 방법으로 위와 관련된 상품들을 거래할 경우에도 금지대상에 포함됩니다.<br>

 

깨끗한 거래 환경이 유지될 수 있도록 최선을 다 하겠습니다.<br>

 

감사합니다.</p>
            </div>

            <button class="accordion">시소마켓 개인정보처리방침 안내</button>
            <div class="panel">
                <p><span class="bold">1. 개인정보 처리방침이란?</span><br>
                    
주식회사 시소마켓(이하"회사")는 이용자의 ‘동의를 기반으로 개인정보를 수집·이용 및 제공’하고 있으며, ‘이용자의 권리 (개인정보 자기결정권)를 적극적으로 보장’합니다.<br>
                    
회사는 정보통신서비스제공자가 준수하여야 하는 대한민국의 관계 법령 및 개인정보보호 규정, 가이드라인을 준수하고 있습니다.<br>
                    
“개인정보처리방침”이란 이용자의 소중한 개인정보를 보호함으로써 이용자가 안심하고 서비스를 이용할 수 있도록 회사가 준수해야 할 지침을 의미합니다.<br><br>

본 개인정보처리방침은 회사가 제공하는 시소계정 기반의 서비스(이하 ‘서비스'라 함)에 적용됩니다.<br><br>
                      <span class="bold">2. 개인정보 수집</span><br>
                    
                    서비스 제공을 위한 필요 최소한의 개인정보를 수집하고 있습니다.<br>
회원 가입 시 또는 서비스 이용 과정에서 홈페이지 또는 개별 어플리케이션이나 프로그램 등을 통해 아래와 같은 서비스 제공을 위해 필요한 최소한의 개인정보를 수집하고 있습니다.<br><br>

[시소계정]<br>
필수<br>
이메일, 비밀번호, 이름(닉네임), 프로필사진, 친구목록, 전화번호, 연락처, 서비스 이용내역, 서비스 내 구매 및 결제 내역
<br>
선택<br>
생년월일, 성별, 배송지정보(수령인명, 배송지 주소, 전화번호)<br><br>

[본인인증 시]<br>
이름, 성별, 생년월일, 휴대폰번호, 통신사업자, 내/외국인 여부, 암호화된 이용자 확인값(CI), 중복가입확인정보(DI))<br><br>

[법정대리인 동의 시]<br>
법정대리인 정보(이름, 성별, 생년월일, 휴대폰번호, 통신사업자, 내/외국인 여부, 암호화된 이용자 확인값(CI), 중복가입확인정보(DI))<br><br>

[유료서비스 이용 시]<br>
신용카드 결제 시: 카드번호(일부), 카드사명 등<br>
휴대전화번호 결제 시: 휴대전화번호, 결제승인번호 등<br>
계좌이체 시: 예금주명, 계좌번호, 계좌은행 등<br>
상품권 이용 시: 상품권 번호, 해당 사이트 아이디<br><br>

[환불처리 시]<br>
계좌은행, 계좌번호, 예금주명, 이메일<br><br>

[현금영수증 발행 시]<br>
휴대폰번호, 현금영수증 카드번호
                    
                    
                </p>
            
                
            </div>

               </div>
               
               
               <div id="tabs-2">
                   
                <table class="table1">
                   <tr class="tr1">
                    <td class="t-title">이용 / 제재 3아웃 정책</td>
                       <td>
                       <ul class="ul1">
                           <li>금지 품목 거래시, 제재 받을 수 있어요</li>
                           <li>적절하지 않은 상품명, 이미지, 가격등록 시에 제재 받을 수 있어요</li>
                           <li>외부 채널로의 연락 유도, 타 사이트로의 결제 유도 시 제재 받을 수 있어요</li>
                           <li>욕설, 성희롱 등 비매너 행위는 타인을 불쾌하게 해요</li>
                           <li>상품, 댓글, 시소톡에 도배는 안돼요</li>
                           
                           </ul>
                       </td>
                    </tr>
                    
                    <tr class="tr2">
                    <td class="t-title">서비스 이용제한</td>
                       <td>
                        <ul class="ul2">
                           <li>
                            위의 약속을 위배하는 경우 3아웃 정책으로 이용을 제한합니다.
                            </li>
                            <li>
                            <table class="table2">
                                <tr style="border-top: 1px solid #000; border-bottom: 1px solid #000;">
                                  <td>
                                    3아웃 정책
                                    </td>
                                    <td>
                                    제재 내용
                                    </td>
                                </tr>
                                
                                <tr>
                                  <td>1차
                                    </td>
                                    <td>물품삭제 / 3일 이용제한</td>
                                </tr>
                                
                                <tr>
                                  <td>2차
                                    </td>
                                    <td>
                                    물품삭제 / 15일 이용제한</td>
                                </tr>
                                
                                <tr style="border-bottom: 1px solid #000;">
                                  <td>3차
                                    </td>
                                    <td>
                                    물품삭제 / 30일 이용제한
                                    </td>
                                </tr>
                                </table>
                            
                            </li>  
                            
                            <li style="color: brown">항목에 따라 사전 안내 없이 영구적으로 제한 될 수 있습니다.</li>
                           </ul>
                        </td>
                    </tr>
                   
                   </table>
               
               </div>
               
<!-- ================ 시작 =================== -->
               <!-- 여기서부터 프로그래밍-->
               <div id="tabs-3">
                   <h1>언제나 고객님의 목소리를 귀담아 듣겠습니다.</h1>
               <form action="qnaWritePro" method="post">
<!-- ===== qna 입력 ===== -->
				   <input type="hidden" name="member_id" value="${sessionScope.id }">
                   <textarea name="qna_content" placeholder="문의사항을 입력해주세요." rows="5" cols="50" name=""></textarea>
                   <button type="submit" class="subBtn">전송</button>
                   </form>
<!-- ===== qna 출력 ===== -->
                   <!-- 뿌려주기 -->
                  <ul class="qna" style="color: #396afc">
                   <li class="nick">작성자</li>
                      <li class="content">내용</li>
                   <li class="date">날짜</li>
                   </ul>
                   <!-- ul class="qna" 반복 -->
                   
	               	<c:forEach var="qna" items="${qnaList}">
                      <input type="hidden" name="qna_idx" value="${qna.qna_idx }">
                   <ul class="qna">
	                   <li class="nick">${qna.member_id}</li>
	                   <li class="content" id="qna_content${qna.qna_idx }">${qna.qna_content}
	                        <font id="font_text" class="qna_reply${qna.qna_idx }" style="display:block; padding: 20px; color: brown;">${qna.qna_reply }</font>
	                        </li>
	                   <li class="date"><fmt:formatDate value="${qna.qna_date }" pattern="yyy-MM-dd"/></li>
	 
<!-- ===== 관리자 답변 입력 ===== -->
	                        <!-- 관리자계정이라면 보이게끔 -->
	               <c:if test="${sessionScope.id eq 'admin' }">
	                   <li style="display:flex;">
	                   	<table style="display:flex; margin-right: 20px;">
							<tr><td><a class="btn_prompt${qna_idx}" id="btn_prompt" onclick="qnaReply(${qna.qna_idx});">답글 삽입/수정</a></td></tr>
							<tr><td><a class="btn_prompt${qna_idx}" id="btn_prompt" onclick="qnaReplyDelete(${qna.qna_idx});">답글 삭제</a></td></tr>	    	                   	
	                   		<tr><td><a class="btn_prompt${qna_idx}" id="btn_prompt" onclick="qnaDelete(${qna.qna_idx});">문의 삭제</a></td></tr>
	                   	</table>
	                   </li>
                   </c:if>
                   <c:if test="${sessionScope.id eq qna.member_id }">
	                   <li>
	                   	<table>
							<tr><td><a class="btn_prompt${qna_idx}" id="btn_prompt" onclick="qnaUpdate(${qna.qna_idx});">문의 수정</a></td></tr>
							<tr><td><a class="btn_prompt${qna_idx}" id="btn_prompt" onclick="qnaDelete(${qna.qna_idx});">문의 삭제</a></td></tr>	    	                   	
	                   	</table>
	                   </li>
                   </c:if>
                   </ul>
	               	</c:forEach>
                   
					<!-- =============== 페이징 처리 =============== -->
                   <c:if test="${pb.startPage > pb.pageBlock }">
					<a href='<c:url value="/qnaList?pageNum=${pb.startPage-pb.pageBlock }#tabs-3" />'>
					<이전></a>
					</c:if>
					
					<c:forEach var="i" begin="${pb.startPage }" end="${pb.endPage }" step="1">
						<a href='<c:url value="/qnaList?pageNum=${i}#tabs-3" />'>${i}</a>
					</c:forEach>
					
					<c:if test="${pb.endPage < pb.pageCount }">
					<a href='<c:url value="/qnaList?pageNum=${pb.startPage+pb.pageBlock }#tabs-3" />'>
					<다음></a>
					</c:if>
                   	<!-- =============== 페이징 처리 =============== -->
               </div>
               
        </div> 
        
    
    </div>
    </section>
    
    
    
    
    </body>
    
<script>
//=========== 문의 수정 ===========     
function qnaUpdate(qna_idx){
	var result = prompt("문의 내용 수정");
	var move = "qna_content" + qna_idx;
	if(result.length > 0){
		$.ajax({
			url: '<c:url value="/qnaUpdate" />',
			type: "POST",
			data: {"qna_content" : result, "member_id" : "${sessionScope.id}", "qna_idx" : qna_idx },
			success:function(rdata){
				$("#" + move).text(result);
			},
			error : function(){
				alert(result + '값 전달 실패!');
			}
		});
	         
	}else{
		alert("내용을 입력하세요!");
	}
}; 


//=========== 문의 삭제 ===========  
function qnaDelete(qna_idx){
	$.ajax({
		url: '<c:url value="/qnaDelete" />',
		type: "POST",
		data: {"member_id" : "${sessionScope.id}", "qna_idx" : qna_idx },
		success:function(rdata){
// 			alert("값 전달 성공!")
			$("#tabs-3").click();
		},
		error : function(){
			alert(result + '값 전달 실패!');
		}
	});
}; 	


// =========== 답글 달기/수정 ===========  
function qnaReply(qna_idx){
	var result = prompt("답글 삽입/수정");
	var move = "qna_reply" + qna_idx;
	
	if(result.length > 0){
		$.ajax({
			url: '<c:url value="/insertQnaReply" />',
			type: "POST",
			data: {"qna_reply" : result, "member_id" : "${sessionScope.id}", "qna_idx" : qna_idx },
			success:function(rdata){
				$("." + move).text(result);
			},
			error : function(){
				alert(result + '값 전달 실패!');
			}
		});
	         
	}else{
		alert("내용을 입력하세요!");
	}
}; 
    
	
// =========== 답글 삭제 ===========      
function qnaReplyDelete(qna_idx){
	alert("답글 삭제");	
	$.ajax({
		url: '<c:url value="/deleteQnaReply" />',
		type: "POST",
		data: {"member_id" : "${sessionScope.id}", "qna_idx" : qna_idx },
		success:function(rdata){
// 			alert("값 전달 성공!")
// 			$("." + move).text(result);
		},
		error : function(){
			alert('값 전달 실패!');
		}
	});
	
	
};        
      
// =============================
	
$(function(){ 

	$("#tabs").tabs(); 
	
	$('.me').click(function(){
	  
	    $('.me').removeClass('on');
	    $(this).addClass('on');
	});
        
});

        
var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
  acc[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var panel = this.nextElementSibling;
    if (panel.style.maxHeight) {
      panel.style.maxHeight = null;
    } else {
      panel.style.maxHeight = panel.scrollHeight + "px";
    } 
  });
}
        
</script>
    

</html>










