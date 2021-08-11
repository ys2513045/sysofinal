<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="resources/css/message.css" rel="stylesheet" type="text/css">
<link href="resources/css/common.css">
<script src="resources/js/jquery-3.1.1.min.js"></script>
<script src="resources/js/moment.js"></script>


<title>시소톡</title>



</head>

<body>

	


		<div class="container">
			<div class="content">
				<header id="header">

					<h1 id="recv_name">시소톡 </h1>
				</header>

				<div id="scrollarea">
	<c:forEach items="${msgList}" var="msgList">
					<c:choose>
						<c:when test="${sessionScope.id eq msgList.send_nick}">
							<div class="mymsg common">

								<div class="msgcontent">
									<div class="msg">${msgList.content}</div>
								</div>

								<div class="timezone">
								
									<div class="time"><fmt:formatDate value="${msgList.send_time}" pattern="HH:ss" /></div>
								</div>

							</div>
						</c:when>
						<c:otherwise>

							<div class="othermsg">

								<div class="othercontent">

									<div class="other_img" style="position:relative;">
										<img src="resources/images/default_shop_img.svg" width="36"
											height="36" style="margin:0">
											
									</div>


									<div class="msgcontent" style="position:relative;">
									<span style="position:absolute;">${msgList.send_nick}</span>
									
										<div class="msg_other" style="margin-top:30px">${msgList.content}</div>
									</div>

									<div class="timezone">
										<div class="time"><fmt:formatDate value="${msgList.send_time}" pattern="HH:ss" /></div>
									</div>
<!-- timefmt(this.send_time) -->

								</div>
							</div>
						</c:otherwise>

					</c:choose>
								<script>
		var room = "<c:out value='${msgList.room}' />";
		var recv_nick = "<c:out value='${msgList.recv_nick}' />";
		var send_nick = "${sessionScope.id}";
// 			"<c:out value='${msgList.send_nick}' />";

</script>
						</c:forEach>
					
					
				</div> <!-- scrollarea -->  
				

				<div id="formarea">
					
						<textarea rows="4" id="my_message" ></textarea>

						<button type="button" class="msg_send_btn" onclick="insertMsg()">
							<img src="resources/images/sendmsg.svg" width="25" height="25">
						</button>


				</div>

			</div>


		</div>

	
	
	<script>
	$(function(){
		$("#scrollarea").scrollTop($("#scrollarea")[0].scrollHeight);

	});
	
	
	
	function insertMsg(){
	
		
		var content = document.getElementById('my_message');
		
		if(content.value == ""){
			alert("메세지를 입력하세요 !");
		}else{
		$.ajax({
			url:'insertMsg',
			type:'post',
			contentType:'application/json',
			dataType : 'json',
			data: JSON.stringify({
				"room":room,"recv_nick":recv_nick,"send_nick":send_nick,"content":content.value
			}),
			success:function(data){
			
				var str = "";
				if(data != ""){
				$(data).each(function(){
				str +=  "<div class=" + "'mymsg common'" +">"
					  +   "<div class=" + "'msgcontent'" + ">"
					  +  	"<div class=" + "'msg'" + ">"+ this.content + "</div></div>"
					  +   "<div class=" + "'timezone'" +">"
					  +  "<div class=" + "'time'" +">" + timefmt(this.send_time) + "</div>"
					  +  "</div></div>";
				});
				$("#scrollarea").append(str);
				$("#scrollarea").scrollTop($("#scrollarea")[0].scrollHeight);
				$("#my_message").val("");
				$("#my_message").focus();

				} // data 있
				
			},  // success
			error:function(){
				alert('서버오류');
			}
		
		
		}); //ajax
			
		}

}
	function timefmt(value){
		var result = moment(value).format('HH:mm');
		return result;
	}
	
	</script>
	

	
	
			
				
		
	

</body>
</html>