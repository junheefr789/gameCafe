package com.taeyoung.project.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class memberController {

	@Autowired
	private memberDAO md;

	@RequestMapping(value = "/newMember.go", method = RequestMethod.GET)
	public String newMember(HttpServletRequest request) {
		request.setAttribute("logInBox", "member/logBefore.jsp");
		request.setAttribute("contentPage", "member/newMember.jsp");
		return "index";
	}

	@RequestMapping(value = "/newMemberCheck.go", method = RequestMethod.POST)
	public String newMemberCheck(memberInfo m, HttpServletRequest request) {
		if (md.insertMember(m, request)) {
			request.setAttribute("logInBox", "member/logAfter.jsp");
			request.setAttribute("contentPage", "mainContent.jsp");
			return "index";
		} else {
			request.setAttribute("logInBox", "member/logBefore.jsp");
			request.setAttribute("contentPage", "member/newMember.jsp");
			return "index";
		}
	}

	@RequestMapping(value = "/logInCheck.go", method = RequestMethod.POST)
	public String logInCheck(memberInfo m, HttpServletRequest request) {
		if (md.getMember(m, request)) {
			request.setAttribute("logInBox", "member/logAfter.jsp");
			request.setAttribute("contentPage", "mainContent.jsp");
			return "index";
		} else {
			request.setAttribute("logInBox", "member/logBefore.jsp");
			request.setAttribute("contentPage", "mainContent.jsp");
			return "index";
		}
	}

	@RequestMapping(value = "/updateMember.go", method = RequestMethod.GET)
	public String updateMember(memberInfo m, HttpServletRequest request) {
		request.setAttribute("logInBox", "member/logAfter.jsp");
		request.setAttribute("contentPage", "member/updateMember.jsp");
		return "index";
	}

	@RequestMapping(value = "/updateMemberCheck.go", method = RequestMethod.POST)
	public String updateCheck(memberInfo m, HttpServletRequest request) {
		if (md.updateMember(m, request)) {
			request.setAttribute("logInBox", "member/logAfter.jsp");
			request.setAttribute("contentPage", "mainContent.jsp");
			return "index";
		} else {
			request.setAttribute("logInBox", "member/logAfter.jsp");
			request.setAttribute("contentPage", "member/updateMember.jsp");
			return "index";
		}
	}

	@RequestMapping(value = "/deleteMember.go", method = RequestMethod.GET)
	public String deleteMember(HttpServletRequest request) {
		if (md.deleteMember(request)) {
			request.setAttribute("logInBox", "member/logBefore.jsp");
			request.setAttribute("contentPage", "mainContent.jsp");
			return "index";
		} else {
			request.setAttribute("logInBox", "member/logAfter.jsp");
			request.setAttribute("contentPage", "mainContent.jsp");
			return "index";
		}
	}

	@RequestMapping(value = "/logOut.go", method = RequestMethod.GET)
	public String logOut(HttpServletRequest request) {
		request.getSession().removeAttribute("member");
		request.getSession().removeAttribute("Member");
		request.setAttribute("logInBox", "member/logBefore.jsp");
		request.setAttribute("contentPage", "mainContent.jsp");
		return "index";
	}
}
