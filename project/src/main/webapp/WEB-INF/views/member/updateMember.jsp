<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		$("#newMemberForm").submit(function(){
			var pw = $("#member_pw").val();
			var pwCheck = $("#pwCheck").val();
			var name = $("#member_name").val();
			var year = $("#member_year").val();
			var month = $("#member_month").val();
			var day = $("#member_day").val();
			var mail = $("#member_mail").val();
			var str = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM!@#%$&*1234567890"
			if (!pw||!pwCheck||!name||!year||!month||!day||!mail) {
				alert("빈칸이 존재하면 안됩니다");
				return false;
			}
			if (pw.length<6) {
				alert("비밀번호는 6자 이상");
				return false;
			}
			for (var i = 0; i < pw.length; i++) {
				if (str.indexOf(pw[i])==-1) {
					alert("비밀번호에 사용할수 없는 글자가 존재합니다!!");
					return false;
				}
			}
			if (pw!=pwCheck) {
				alert("비밀번호체크 확인");
				return false;
			}
			if (mail.indexOf("@")==-1) {
				alert("메일형식이 아닙니다");
				return false;
			}
			return true;
		});
	});
</script>
<style type="text/css">
#newMemberTable{
	width:50%;
	margin-left:auto;
	margin-right:auto;
}
.newMemberTr{
	height:70px;
}
#member_id{
	width:80%;
	height:40px;
	text-align:center;
	font-size:15pt;
}
#member_pw{
	width:80%;
	height:40px;
	text-align:center;
	font-size:15pt;
}
#pwCheck{
	width:80%;
	height:40px;
	text-align:center;
	font-size:15pt;
}
#member_name{
	width:80%;
	height:40px;
	text-align:center;
	font-size:15pt;
}
#member_gender{
	width:80px;
	height:30px;
	text-align:center;
	font-size:15pt;
}
#member_year{
	width:25%;
	height:40px;
	text-align:center;
	font-size:15pt;
}
#member_month{
	width:25%;
	height:40px;
	text-align:center;
	font-size:15pt;
}
#member_day{
	width:25%;
	height:40px;
	text-align:center;
	font-size:15pt;
}
#member_mail{
	width:80%;
	height:40px;
	text-align:center;
	font-size:15pt;
}
#newMemberGo{
	width:80%;
	height:40px;
	font-size:15pt;
}
</style>
</head>
<body>
<form id="newMemberForm" action="updateMemberCheck.go" method="post">
	<table id="newMemberTable">
		<tr class="newMemberTr">
			<td class="newMemberTd" align="center">
				<input id="member_pw" name="member_pw" type="password" autocomplete="off" maxlength="10" placeholder="비밀번호를 써주세요">
			</td>
		</tr>
		<tr class="newMemberTr">
			<td class="newMemberTd" align="center">
				<input id="pwCheck" name="pwCheck" type="password" autocomplete="off" maxlength="10" placeholder="비밀번호를 한번 더 써주세요">
			</td>
		</tr>
		<tr class="newMemberTr">
			<td class="newMemberTd" align="center">
				<input id="member_name" name="member_name" autocomplete="off" maxlength="5" placeholder="성함을 써주세요">
			</td>
		</tr>
		<tr class="newMemberTr">
			<td class="newMemberTd" align="center">
				<select id="member_gender" name="member_gender">
					<option value="m">남</option>
					<option value="w">여</option>
				</select>
			</td>
		</tr>
		<tr class="newMemberTr">
			<td class="newMemberTd" align="center">
				<input id="member_year" name="member_year" autocomplete="off" maxlength="4">년
				<input id="member_month" name="member_month" autocomplete="off" maxlength="2">월
				<input id="member_day" name="member_day" autocomplete="off" maxlength="2">일
			</td>
		</tr>
		<tr class="newMemberTr">
			<td class="newMemberTd" align="center">
				<input id="member_mail" name="member_mail" autocomplete="off" maxlength="30" placeholder="메일을 써주세요">
			</td>
		</tr>
		<tr>
			<td align="center">
				<button id="newMemberGo">정보수정</button>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>