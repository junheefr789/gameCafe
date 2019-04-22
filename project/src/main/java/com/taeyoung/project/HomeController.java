package com.taeyoung.project;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taeyoung.project.member.memberInfo;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		request.setAttribute("logInBox", "member/logBefore.jsp");
		request.setAttribute("contentPage", "mainContent.jsp");
		return "index";
	}
	@RequestMapping(value = "/home.go", method = RequestMethod.GET)
	public String goHome(HttpServletRequest request) {
		if ((memberInfo) request.getSession().getAttribute("Member")==null) {
			request.setAttribute("logInBox", "member/logBefore.jsp");
			request.setAttribute("contentPage", "mainContent.jsp");
		}else{
			request.setAttribute("logInBox", "member/logAfter.jsp");
			request.setAttribute("contentPage", "mainContent.jsp");
		}
		return "index";
	}
	
}
