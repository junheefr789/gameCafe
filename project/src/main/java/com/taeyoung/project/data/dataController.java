package com.taeyoung.project.data;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class dataController {
	
	@RequestMapping(value = "/data.go", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		request.setAttribute("logInBox", "member/logAfter.jsp");
		request.setAttribute("contentPage", "mainContent.jsp");
		return "index";
	}
}
