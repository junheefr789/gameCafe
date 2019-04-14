<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function(){
		$("#writeButton").click(function(){
			sendMessage();
			$("#writeText").val("");
		});
		var sock = new SockJS("<c:url value="/echo"/>");
		sock.onmessage = onMessage;
		sock.onclose = onClose;
	
		function sendMessage(){
			sock.send($("#writeText").val());
		}
		function onMessage(msg){
			var data = msg.data;
			$("#chatingDiv").append(data+"<br>");
		}
		function onClose(evt){
			$("#chatingDiv").append("연결 끊김");
		}
	});
</script>
<style type="text/css">
#chatingDiv{
	width:60%;
	left:20%;
	height:500px;
	position:absolute;
	top:50px;
	border:black solid 1px;
}
#writeDiv{
	bottom:0;
	position:fixed;
	width:100%;
	height:130px;
}
#writeText{
	width:40%;
	height:90px;
	margin-left:30%;
	float:left;
}
#writeButton{
	float:left;
	width:70px;
	height:90px;
	margin-top:0;
	margin-left:30px;
}
</style>
</head>
<body>
	<div id="chatingDiv">
	</div>
	<div id="writeDiv">
		<input id="writeText">
		<button id="writeButton">작성</button>
	</div>
</body>
</html>