package com.taeyoung.project.member;

import javax.servlet.http.HttpSession;

public class memberInfo {
	private String member_id;
	private String member_pw;
	private String member_name;
	private String member_gender;
	private String member_birth;
	private String member_mail;
	private String member_ip;
	private HttpSession member_session;
	
	public memberInfo() {
	}
	
	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_pw() {
		return member_pw;
	}

	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_gender() {
		return member_gender;
	}

	public void setMember_gender(String member_gender) {
		this.member_gender = member_gender;
	}

	public String getMember_birth() {
		return member_birth;
	}

	public void setMember_birth(String member_birth) {
		this.member_birth = member_birth;
	}

	public String getMember_mail() {
		return member_mail;
	}

	public void setMember_mail(String member_mail) {
		this.member_mail = member_mail;
	}

	public String getMember_ip() {
		return member_ip;
	}

	public void setMember_ip(String member_ip) {
		this.member_ip = member_ip;
	}

	public HttpSession getMember_session() {
		return member_session;
	}

	public void setMember_session(HttpSession member_session) {
		this.member_session = member_session;
	}
	
	
}
