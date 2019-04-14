<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		setInterval(function(){
			var ran = Math.floor(Math.random()*8)+1;
			$("#mainImg").attr("src","resources/img/"+ran+".jpg");
		},3000);
	});
</script>
<style type="text/css">
#mainImg{
	width:60%;
	margin-left:20%;
	height:auto;
}
</style>
</head>
<body>
	<img id="mainImg" src="resources/img/3.jpg">
</body>
</html>