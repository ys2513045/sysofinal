<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
   <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



   
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="resources/css/chat.css" rel="stylesheet" type="text/css">


<title>시소톡</title>
    
<script src="resources/js/jquery-3.1.1.min.js"></script>


</head>
    
<body style="overflow-y:hidden;">

	<div id = "chatContainer">
	<div class="left">
    <ul>
        <li>
        <img src="resources/images/default_shop_img.svg" width="30" height="30">
        </li>
        
        <li>
        <img src="resources/images/free-icon-chat-oval-filled-speech-bubbles-59358.svg" width="30" height="30">
        </li>
        
        <li>
        <img src="resources/images/free-icon-menu-149021.svg" width="25" height="25">
        </li>
        
        </ul>
	</div>
        
        <div class="right">
        <div class="message_area">
            
            <div class="message_container">
                <div class="time_line">
            <h1 class="title">${sessionScope.id } 님의 채팅</h1>
                </div>
            <div class="message_list" style="overflow-y:scroll; height: 380px;">
                <ul>
                	<c:forEach items="${chatList}" var="chatList">
              
                <li id="chatList">
                    <div class="content" ondblclick="showMessage(${chatList.room})">
                        <div class="img_cont">
                        <img src="resources/images/default_shop_img.svg">
                        </div>
                   
                        <div class="txt_cont">
                        <c:choose>
                        <c:when test="${sessionScope.id eq chatList.recv_nick}">
                        <span class="name">${chatList.send_nick}</span>
                        </c:when>
                        <c:otherwise>
                         <span class="name">${chatList.recv_nick}</span>
                        
                        </c:otherwise>
                        </c:choose>
                       	                        <span class="time">${chatList.message_date_format}</span>
<%--                        	                        ${chatList.message_date_format } --%>
<%-- <fmt:formatDate value="${chatList.send_time}" pattern="MM-dd HH:ss" /> --%>
                        </div>
                        
               		<input type="hidden" value="${chatList.room }" id="room_val">
                    </div>
                    </li>
           			<script>
					
           			var list = new Array();

           			list.push("${chatList.room}");
           			</script>
                    </c:forEach>
                   
                </ul>
                
                
                </div>    
                
            </div>
            
            
            
            </div>
        
        
        </div>
        
    </div>

<script>


    $(function(){
        
    
$('.content').click(function(){
  $('.content').removeClass("active");
  $(this).addClass("active");
});



        });
function showMessage(room){

		window.open("<c:url value='chattingFromList?room="+room+"'/>", "Message", "width=360, height=575, resizable=no, scrollbars=no" );
		window.resizeTo(353, 575);
	
	

}


</script>


</body>
</html>