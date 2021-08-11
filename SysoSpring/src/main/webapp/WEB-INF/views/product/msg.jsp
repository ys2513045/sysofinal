<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- member/msg.jsp -->
<script type="text/javascript">
   alert("${msg}");
   location.href="itemDetail?item_idx="+"${item_idx}";
</script>
</body>
</html>