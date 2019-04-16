package com.taeyoung.project.member;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class memberDAO {
	
	@Autowired
	private SqlSession ss;
	
	public boolean getMember(memberInfo m,HttpServletRequest request){
		try {
			memberInfo mi = ss.getMapper(memberInterface.class).getMember(m);
			if (mi!=null) {
				if (m.getMember_pw().equals(mi.getMember_pw())) {
					request.setAttribute("result", mi.getMember_name()+"님 환영합니다");
					request.getSession().setAttribute("member", mi);
					request.getSession().setAttribute("Member", mi);
					request.getSession().setMaxInactiveInterval(60*60);
					return true;
				}else{
					request.setAttribute("result", "비밀번호가 맞지 않습니다");
					return false;
				}
			}else{
				request.setAttribute("result", "아이디가 없습니다");
				return false;
			}
		} catch (Exception e) {
			request.setAttribute("result", "데이터베이스에 문제가 생겼습니다");
			return false;
		}
	}
	
	public boolean insertMember(memberInfo m,HttpServletRequest request){
		m.setMember_birth(request.getParameter("member_year")+request.getParameter("member_month")+request.getParameter("member_day"));
		try {
			if (ss.getMapper(memberInterface.class).insertMember(m)==1) {
				request.setAttribute("result", "회원가입을 축하드립니다");
				request.getSession().setAttribute("member", m);
				request.getSession().setAttribute("Member", m);
				request.getSession().setMaxInactiveInterval(60*60);
				return true;
			}else{
				request.setAttribute("result", "회원가입에 실패했습니다. 다시 해주세요");
				return false;
			}
		} catch (Exception e) {
			request.setAttribute("result", "데이터베이스에 문제가 생겼습니다");
			return false;
		}
	}
	
	public boolean updateMember(memberInfo m,HttpServletRequest request){
		m.setMember_id(((memberInfo) request.getSession().getAttribute("member")).getMember_id());
		m.setMember_birth(request.getParameter("member_year")+request.getParameter("member_month")+request.getParameter("member_day"));
		try {
			if (ss.getMapper(memberInterface.class).updateMember(m)==1) {
				request.setAttribute("result", "수정 성공!!");
				request.getSession().setAttribute("member", m);
				request.getSession().setAttribute("Member", m);
				request.getSession().setMaxInactiveInterval(60*60);
				return true;
			}else{
				request.setAttribute("result", "수정 실패!!");
				return false;
			}
		} catch (Exception e) {
			request.setAttribute("result", "데이터 베이스에 문제가 생겼습니다");
			return false;
		}
	}
	
	public boolean deleteMember(HttpServletRequest request){
		memberInfo mi = (memberInfo) request.getSession().getAttribute("member");
		try {
			if (ss.getMapper(memberInterface.class).deleteMember(mi)==1) {
				request.setAttribute("result", "회원탈퇴가 되었습니다");
				request.getSession().removeAttribute("member");
				request.getSession().removeAttribute("Member");
				return true;
			}else{
				request.setAttribute("result", "탈퇴 실패!!");
				return false;
			}
		} catch (Exception e) {
			request.setAttribute("result", "데이터베이스에 문제가 생겼습니다");
			return false;
		}
	}
}
