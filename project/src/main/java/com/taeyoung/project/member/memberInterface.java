package com.taeyoung.project.member;

public interface memberInterface {
	public abstract int insertMember(memberInfo m);
	public abstract memberInfo getMember(memberInfo m);
	public abstract int updateMember(memberInfo m);
	public abstract int deleteMember(memberInfo m);
}
