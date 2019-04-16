package com.taeyoung.project.onecard;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class onecardController {

	@RequestMapping(value = "/onecardInvite.go", method = RequestMethod.GET)
	public String onecardInvite(HttpServletRequest request) {
		request.setAttribute("logInBox", "member/logAfter.jsp");
		request.setAttribute("contentPage", "onecard/gameInvite.jsp");
		return "index";
	}

}
