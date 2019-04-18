<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		var sock = new SockJS("<c:url value="/echo"/>");
		sock.onmessage = onMessage;
		sock.onclose = onClose;
	
		function sendMessage(message){
			sock.send(message);
		}
		function onMessage(d){
			var data = d.data;
			if (data.indexOf("%")!=-1) {
				data = data.substring(1);
				var button = $("<button></button>").attr("class","idButton").data("id_name",data).text(data);
				$("#inviteIdDiv").append(button);
			}else if(data.indexOf("#")!=-1){
				data = data.substring(1);
				var button = $("button").data("id_name",data);
				button.remove();
			}else if(data.indexOf("_")!=-1){
				data = data.substring(1);
				$("#chatingDiv").append(data+"<br>");
				$("#chatingDiv").scrollTop($("#chatingDiv")[0].scrollHeight);
			}else if(data.indexOf("&")!=-1){
				data = data.substring(1);
				var con = confirm(data+"님께서 게임을 신청하셨습니다. 수락하시겠습니까?");
				if (con) {
					sendMessage("%"+data);					
					location.href="onecardGame.go?invite="+data;
				}else{
					sendMessage("_"+data);					
				}
			}else if (data.indexOf("*")!=-1) {
				data = data.substring(1);
				if (data.indexOf("No")!=-1) {
					alert("거절하셨습니다");
					return;
				}else{
					location.href="onecardGame.go?invite="+data;
				}
			}
		}
		function onClose(evt){
			$("#chatingDiv").append("서버와 연결 끊김");
		}
		var str="%_&*#";
		$(document).keydown(function(e){
			if (e.keyCode==13) {
				$("#writeButton").trigger("click");
			}
		});
		$("#writeButton").bind("click",function(){
			var write = $("#writeText").val();
			if (!write) {
				alert("내용이 없습니다");
				$("#writeText").autofocus;
				return;
			}
			for (var i = 0; i < str.length; i++) {
				if (write.indexOf(str[i])!=-1) {
					alert("%^&*#은 쓸수 없습니다!");
					$("#writeText").autofocus;
					return;
				}
			}
			sendMessage(write);
			$("#writeText").val("");
			$("#writeText").autofocus;
		});
		$(document).on("click",".idButton",function(){
			var message = "&"+$(this).data("id_name");
			var con = confirm($(this).data("id_name")+"님께 게임신청하시겠습니까?");
			if (con) {
				sendMessage(message);
			}else{
				return;
			}
		});
	});
</script>
<style type="text/css">
#inviteMainDiv{
	top:50px;
	width:40%;
	position:absolute;
	height:550px;
	margin:0;
	padding:0;
	left:30%;
}
#chatingDiv{
	width:100%;
	height:90%;
	background-color:orange;
	color:black;
	font-size:13pt;
	font-weight:bold;
	display:block;
	overflow:auto;
}
#writeDiv{
	width:100%;
	height:10%;
	text-align:center;
}
#writeText{
	float:left;
	width:75%;
	height:100%;
}
#writeButton{
	float:left;
	width:24%;
	height:100%;
}
#inviteIdDiv{
	position:absolute;
	top:50px;
	magin:0;
	padding:0;
	float:right;
	width:150px;
	height:300px;
	right:50px;
	font-size:12pt;
	text-align:center;
	overflow:auto;
}
.idButton{
	width:100%;
	height:40px;
}
</style>
</head>
<body>
	<div id="inviteMainDiv">
		<div id="chatingDiv">
		</div>
		<div id="writeDiv">
			<input id="writeText" autocomplete="off" autofocus>
			<input type="button" id="writeButton" value="작성">
		</div>
	</div>
	<div id="inviteIdDiv">
		접속한 아이디
	</div>
</body>
</html>