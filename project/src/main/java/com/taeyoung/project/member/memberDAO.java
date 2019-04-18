package com.taeyoung.project.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class memberDAO implements HttpSessionBindingListener{

	@Autowired
	private SqlSession ss;
	private ArrayList<memberInfo> memberList=new ArrayList<>();

	public boolean getMember(memberInfo m, HttpServletRequest request) {
		try {
			memberInfo mi = ss.getMapper(memberInterface.class).getMember(m);
			if (mi != null) {
				if (m.getMember_pw().equals(mi.getMember_pw())) {
					for (int i = 0; i < memberList.size(); i++) {
						if (memberList.get(i).getMember_id().equals(mi.getMember_id())) {
							request.setAttribute("result", "같은 아이디가 접속중입니다");
							return false;
						}else if (memberList.get(i).getMember_ip().equals(request.getRemoteAddr())) {
							request.setAttribute("result", "같은 아이피 주소에서 접속중입니다");
							return false;
						}
					}
					request.setAttribute("result", mi.getMember_name() + "님 환영합니다");
					request.getSession().setAttribute("member", mi);
					request.getSession().setAttribute("Member", mi);
					request.getSession().setAttribute(mi.getMember_id()+";"+request.getRemoteAddr(), this);
					request.getSession().setMaxInactiveInterval(60 * 60);
					return true;
				} else {
					request.setAttribute("result", "비밀번호가 맞지 않습니다");
					return false;
				}
			} else {
				request.setAttribute("result", "아이디가 없습니다");
				return false;
			}
		} catch (Exception e) {
			request.setAttribute("result", "데이터베이스에 문제가 생겼습니다");
			return false;
		}
	}

	public boolean insertMember(memberInfo m, HttpServletRequest request) {
		m.setMember_birth(request.getParameter("member_year") + request.getParameter("member_month")
				+ request.getParameter("member_day"));
		try {
			if (ss.getMapper(memberInterface.class).insertMember(m) == 1) {
				request.setAttribute("result", "회원가입을 축하드립니다");
				request.getSession().setAttribute("member", m);
				request.getSession().setAttribute("Member", m);
				request.getSession().setAttribute(m.getMember_id()+";"+request.getRemoteAddr(), this);
				request.getSession().setMaxInactiveInterval(60 * 60);
				return true;
			} else {
				request.setAttribute("result", "회원가입에 실패했습니다. 다시 해주세요");
				return false;
			}
		} catch (Exception e) {
			request.setAttribute("result", "데이터베이스에 문제가 생겼습니다");
			return false;
		}
	}

	public boolean updateMember(memberInfo m, HttpServletRequest request) {
		m.setMember_id(((memberInfo) request.getSession().getAttribute("member")).getMember_id());
		m.setMember_birth(request.getParameter("member_year") + request.getParameter("member_month")
				+ request.getParameter("member_day"));
		try {
			if (ss.getMapper(memberInterface.class).updateMember(m) == 1) {
				request.setAttribute("result", "수정 성공!!");
				request.getSession().setAttribute("member", m);
				request.getSession().setAttribute("Member", m);
				request.getSession().setMaxInactiveInterval(60 * 60);
				return true;
			} else {
				request.setAttribute("result", "수정 실패!!");
				return false;
			}
		} catch (Exception e) {
			request.setAttribute("result", "데이터 베이스에 문제가 생겼습니다");
			return false;
		}
	}

	public boolean deleteMember(HttpServletRequest request) {
		memberInfo mi = (memberInfo) request.getSession().getAttribute("member");
		try {
			if (ss.getMapper(memberInterface.class).deleteMember(mi) == 1) {
				request.setAttribute("result", "회원탈퇴가 되었습니다");
				request.getSession().removeAttribute("member");
				request.getSession().removeAttribute("Member");
				deleteSession(request);
				return true;
			} else {
				request.setAttribute("result", "탈퇴 실패!!");
				return false;
			}
		} catch (Exception e) {
			request.setAttribute("result", "데이터베이스에 문제가 생겼습니다");
			return false;
		}
	}
	public void deleteSession(HttpServletRequest request){
		request.getSession().invalidate();
	}

	@Override
	public void valueBound(HttpSessionBindingEvent e) {
		memberInfo m = new memberInfo();
		m.setMember_session(e.getSession());
		m.setMember_id(e.getName().split(";")[0]);
		m.setMember_ip(e.getName().split(";")[1]);
		memberList.add(m);
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent e) {
		for (int i = 0; i < memberList.size(); i++) {
			if (memberList.get(i).getMember_session()==e.getSession()) {
				memberList.remove(i);
			}
		}
	}
}
