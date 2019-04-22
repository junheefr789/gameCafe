<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		var id = "${sessionScope.Member.member_id}";
		var turn = false;
		var firstId = "${firstId}";
		var defendCard = new Array();
		var floorCard = 0;
		var selectList = new Array();
		if (id==firstId) {
			turn = true;
		}
		$(window).on("beforeunload",function(){
			return "정말로 나가겠습니까?";				
		});
		getCard();
		function getCard(){
			$.ajax({
				url:"cardData.go",
				success:function(data){
					if (data) {
						if (data.ready[0]) {
							td = $("<td></td>").text("상대방을 기다리고 있습니다");
							$("#myTr").append(td);
							return;
						}else if(data.winner[0]){
							if (data.winner[0]==id) {
								alert("당신은 이겼습니다");
							}else{
								alert("당신은 졌습니다");
							}
							setTimeout(function(){
								location.href="onecardEnd.go";
							},1000);
						}else{
							floorCard = data.floorCard[0];
							$("#myTr").empty();
							$.each(data[id],function(key,value){
								var img = $("<img>").attr("class","card").attr("src","resources/img/card/"+value+".png").data("cardNumber",value).css("margin-top",30+"px");
								var td = $("<td></td>");
								$("#myTr").append(td.append(img));
							});
							$("#floorCard").attr("src","resources/img/card/"+floorCard+".png");
							if (turn) {
								if (data.attack[0]&&data.defend[0]) {
									defendCard=data.defend;
									alert("당신은 공격받았습니다 반드시 방어하셔야 합니다!!");
									$("#pushButton").css("display","block");
									$(".card").attr("class","myCard").css("cursor","pointer");
								}else if (data.attack[0]&&!data.defend[0]) {
									alert("당신은 공격받았습니다");
									$("#pushButton").css("display","block");
									$("#receiveButton").css("display","block");
									$(".card").attr("class","myCard").css("cursor","pointer");
								}else{
									alert("당신의 턴입니다");
									$("#pushButton").css("display","block");
									$("#receiveButton").css("display","block");
									$(".card").attr("class","myCard").css("cursor","pointer");
								}
							}else{
								turn=true;
							}
						}
					}
				},
				complete:setTimeout(function(){
					getCard();
				},1000)
			});
		}
		$("#receiveButton").click(function(){
			$.ajax({
				url:"receiveCard.go",
				success:function(data){
					var img = $("<img>").attr("class","myCard").attr("src","resources/img/card/"+data.receiveCard[0]+".png").data("cardNumber",data.receiveCard[0]).css("cursor","pointer").css("margin-top",30+"px");
					var td = $("<td></td>");
					$("#myTr").append(td.append(img));
				}
			});
		});
		$("#pushButton").click(function(){
			if (selectList.length==0) {
				alert("카드를 선택해주세요");
				return;
			}else{
				$.ajax({
					url:"pushCard.go",
					traditional:true,
					data : {"pushCard" : selectList},
					success:function(data){
						turn = false;
						selectList = new Array();
						defendCard = new Array();
						$("#pushButton").css("display","None");
						$("#receiveButton").css("display","None");
					}
				});
			}
		});
		$(document).on("click",".myCard",function(){
			var select = $(this).data("cardNumber");
			if (defendCard.length==0) {
				if ($(this).css("margin-top")==0+"px") {
					for (var i = 0; i < selectList.length; i++) {
						if (selectList[i]==select) {
							selectList.splice(i,1);
						}
					}
					$(this).css("margin-top",30+"px");
					return;
				}else{
					if (selectList.length>=1) {
						if (selectList[0]%100==select%100&&selectList[0]!=501&&selectList[0]!=502&&select!=501&&select!=502) {
							$(this).css("margin-top",0+"px");
							selectList.push(select);
							return;
						}else{
							alert("선택하실수 없는 카드입니다");
							return;
						}
					}else{
						if (floorCard==501||floorCard==502) {
							$(this).css("margin-top",0+"px");
							selectList.push(select);
							return;
						}else{ 
							if(floorCard%100==select%100||parseInt(floorCard/100)==parseInt(select/100)||select==501||select==502) {
								$(this).css("margin-top",0+"px");
								selectList.push(select);
								return;
							}else{
								alert("선택하실수 없는 카드 입니다");
								return;
							}
						}
					}
				}
			}else{
				if ($(this).css("margin-top")==0+"px") {
					for (var i = 0; i < selectList.length; i++) {
						if (selectList[i]==select) {
							selectList.splice(i,1);
						}
					}
					$(this).css("margin-top",30+"px");
					return;
				}else{
					if (selectList.length>=1) {
						if (select%100==1&&selectList[0]!=501&&selectList[0]!=502&&select!=501&&select!=502) {
							$(this).css("margin-top",0+"px");
							selectList.push(select);
							return;
						}else{
							alert("선택하실수 없는 카드입니다");
							return;
						}
					}else{
						for (var i = 0; i < defendCard.length; i++) {
							if (defendCard[i]==select) {
								$(this).css("margin-top",0+"px");
								selectList.push(select);
								return;
							}
						}
						alert("선택하실수 없는 카드 입니다");
						return;
					}
				}
			}
		});
	});
</script>
<style type="text/css">
#oneCardBox{
	width:80%;
	height:600px;
	left:10%;
	background-color:green;
	top:20px;
	padding:0;
	margin:0;
	position:absolute;
}
#enemyTable{
	margin-top:0px;
	margin-left:auto;
	margin-right:auto;
}
#receiveButton{
	width:113px;
	height:168px;
	background-color:red;
	font-size:13pt;
	font-weight:bold;
	color:white;
	display:None;
}
#pushButton{
	width:113px;
	height:168px;
	background-color:blue;
	font-size:13pt;
	font-weight:bold;
	color:white;
	display:None;
}
#floorCard{
	position:absolute;
	top:200px;
	left:43%;
}
#myTable{
	margin-left:auto;
	margin-right:auto;
	margin-top:210px;
}
#myTr{
	height:200px;
}
</style>
</head>
<body>
	<div id="oneCardBox">
		<table id="enemyTable">
			<tr>
				<td><button id="receiveButton">카드 받기</button></td>
				<td><img class="backCard" src="resources/img/card/backcard.png"></td>
				<td><img class="backCard" src="resources/img/card/backcard.png"></td>
				<td><img class="backCard" src="resources/img/card/backcard.png"></td>
				<td><img class="backCard" src="resources/img/card/backcard.png"></td>
				<td><img class="backCard" src="resources/img/card/backcard.png"></td>
				<td><button id="pushButton">카드 내기</button></td>
			</tr>
		</table>
		<img id="floorCard" src="resources/img/card/backcard.png">
		<table id="myTable">
			<tr id="myTr">
			</tr>
		</table>
	</div>
</body>
</html>