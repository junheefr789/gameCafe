<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/js/sockjs-0.3.4.js"></script>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		var result = "${result}";
		if (result) {
			alert(result);
		}
	});
</script>
<link rel="stylesheet" type="text/css" href="resources/css/index.css">
</head>
<body>
	<div id="title">
		<div id="siteName">
			Taeyoung's cafe
		</div>
		<div id="logInBox">
			<jsp:include page="${logInBox }" flush="true"></jsp:include>
		</div>
	</div>
	<div id="menu">
		<table id="menuTable">
			<tr>
				<td class="menuTd" align="center">원카드게임</td>
				<td class="menuTd" align="center">자료실</td>
			</tr>
		</table>
	</div>
	<div id="mainPage">
		<jsp:include page="${contentPage}" flush="true"></jsp:include>
	</div>
</body>
</html>