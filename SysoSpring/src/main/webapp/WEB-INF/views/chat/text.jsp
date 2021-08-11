<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-3.1.1.min.js"></script>

</head>
<body>
<textarea id="test"></textarea>
<input type="text" id="test2">

<button  onclick="ex()">클릭 </button>

<script type="text/javascript">
var ex = $('#test').val();
document.getElementById('test2').value;

function ex(){
alert(ex);
alert(ex2);
}
</script>

</body>
</html>