package com.taeyoung.project.onecard;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class onecardController {
	
	@Autowired
	private onecardRoomMaster orm;

	@RequestMapping(value = "/onecardInvite.go", method = RequestMethod.GET)
	public String onecardInvite(HttpServletRequest request) {
		request.setAttribute("logInBox", "member/logAfter.jsp");
		request.setAttribute("contentPage", "onecard/gameInvite.jsp");
		return "index";
	}
	@RequestMapping(value = "/onecardGame.go", method = RequestMethod.GET)
	public String onecardStart(HttpServletRequest request) {
		orm.createRoom(request);
		request.setAttribute("logInBox", "member/logAfter.jsp");
		request.setAttribute("contentPage", "onecard/gameBox.jsp");
		return "index";
	}
	@RequestMapping(value = "/cardData.go", method = RequestMethod.GET, produces="application/json; charset=utf-8")
	public @ResponseBody JSONObject onecardData(HttpServletRequest request) {
		return orm.cardData(request);
	}
	@RequestMapping(value = "/receiveCard.go", method = RequestMethod.GET, produces="application/json; charset=utf-8")
	public @ResponseBody JSONObject receiveCard(HttpServletRequest request) {
		return orm.receiveCard(request);
	}
	@RequestMapping(value = "/pushCard.go", method = RequestMethod.GET, produces="application/json; charset=utf-8")
	public @ResponseBody JSONObject pushCard(HttpServletRequest request) {
		return orm.pushCard(request);
	}
	@RequestMapping(value = "/onecardEnd.go", method = RequestMethod.GET)
	public String onecardEnd(HttpServletRequest request) {
		orm.deleteRoom(request);
		request.setAttribute("logInBox", "member/logAfter.jsp");
		request.setAttribute("contentPage", "onecard/gameInvite.jsp");
		return "index";
	}
	@RequestMapping(value = "/gameGiveUp.go", method = RequestMethod.GET)
	public void giveUp(HttpServletRequest request) {
		orm.deleteRoom(request);
	}
	
}
