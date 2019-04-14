<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	$(function(){
		$("#newMemberButton").click(function(){
			location.href="newMember.go";
		});
		$("#logInForm").submit(function(){
			var id = $("#inputId").val();
			var pw = $("#inputPw").val();
			var str = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM!@#$%^&*1234567890"
			if (!id||!pw) {
				alert("빈칸이 존재합니다");
				return false;
			}
			for (var i = 0; i < id.length; i++) {
				if (str.indexOf(id[i])==-1) {
					alert("아이디 글자 확인!!");
					return false;
				}
			}
			for (var i = 0; i < pw.length; i++) {
				if (str.indexOf(pw[i])==-1) {
					alert("비밀번호 확인!!");
					return false;
				}
			}
			return true;
		});
	});
</script>
<style type="text/css">
#logBefore{
	width:100%;
	height:100%;
	margin:0;
	height:0;
}
#inputDiv{
	float:left;
	width:59%;
	height:100%;
	padding:0;
	margin:0;
	text-align:center;
}
#logInDiv{
	float:left;
	width:20%;
	height:100%;
}
#newMemberDiv{
	float:left;
	width:20%;
	height:100%;
}
#inputId{
	width:80%;
	height:20px;
	text-align:center;
}
#inputPw{
	width:80%;
	height:20px;
	text-align:center;
}
#logInButton{
	margin-top:20px;
	width:60px;
	height:60px;
}
#newMemberButton{
	margin-top:20px;
	width:60px;
	height:60px;
}
</style>
<body>
	<div id="logBefore">
		<form id="logInForm" action="logInCheck.go" method="post">
		<div id="inputDiv">
			<p>
			<input id="inputId" name="member_id" autocomplete="off" maxlength="12" >
			<p>
			<input id="inputPw" name="member_pw" type="password" autocomplete="off" maxlength="10">
		</div>
		<div id="logInDiv">
			<button id="logInButton">로그인</button>
		</div>
		</form>
		<div id="newMemberDiv">
			<button id="newMemberButton">회원가입</button>
		</div>
	</div>
</body>
</html>