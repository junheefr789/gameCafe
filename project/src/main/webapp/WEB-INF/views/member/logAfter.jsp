<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		$("#logOutButton").click(function(){
			var con = confirm("정말 로그아웃 하실건가요?");
			if (con) {
				location.href="logOut.go";
			}else{
				return;
			}
		});
		$("#updateMemberButton").click(function(){
			location.href="updateMember.go";
		});
		$("#deleteMemberButton").click(function(){
			var pt = prompt("비밀번호를 입력해주세요");
			var pw = "${sessionScope.member.member_pw}"
			if (pw==pt) {
				location.href="deleteMember.go";
			}else{
				return;
			}
		});
	});
</script>
<style type="text/css">
#logAfterBox{
	width:100%;
	height:100%;
}
#memberName{
	float:left;
	width:25%;
	height:100%;
	font-size:20pt;
	font-weight:bold;
	text-align:center;
}
#logOutDiv{
	float:left;
	width:25%;
	height:100%;
}
#updateDiv{
	float:left;
	width:25%;
	height:100%;
}
#deleteDiv{
	float:left;
	width:24%;
	height:100%;
}
#logOutButton{
	margin-top:20px;
	width:80%;
	height:60px;
}
#updateMemberButton{
	margin-top:20px;
	width:80%;
	height:60px;
}
#deleteMemberButton{
	margin-top:20px;
	width:80%;
	height:60px;
}
</style>
</head>
<body>
	<div id="logAfterBox">
		<div id="memberName">
		<p>
			${sessionScope.member.member_name}님
		</div>
		<div id="logOutDiv">
			<button id="logOutButton">로그아웃</button>
		</div>
		<div id="updateDiv">
			<button id="updateMemberButton">정보수정</button>
		</div>
		<div id="deleteDiv">
			<button id="deleteMemberButton">회원탈퇴</button>
		</div>
	</div>
</body>
</html>