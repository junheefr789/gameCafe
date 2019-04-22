package com.taeyoung.project.onecard;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.taeyoung.project.member.memberInfo;

@Service
public class onecardRoomMaster {
	
	private ArrayList<onecardRoom> roomList = new ArrayList<>();
	
	public void createRoom(HttpServletRequest request){
		String inviteId = request.getParameter("invite");
		boolean first = true;
		memberInfo m = (memberInfo) request.getSession().getAttribute("Member");
		for (int i = 0; i < roomList.size(); i++) {
			if (roomList.get(i).getInviteId().equals(inviteId)) {
				roomList.get(i).setUser2Id(m.getMember_id());
				roomList.get(i).setUser2Ip(request.getRemoteAddr());
				first = false;
			}
		}
		if (first) {
			onecardRoom or = new onecardRoom();
			or.setInviteId(inviteId);
			or.setUser1Id(m.getMember_id());
			or.setUser1Ip(request.getRemoteAddr());
			request.setAttribute("firstId", m.getMember_id());
			roomList.add(or);
		}
	}
	
	public JSONObject cardData(HttpServletRequest request){
		for (int i = 0; i < roomList.size(); i++) {
			if (request.getRemoteAddr().equals(roomList.get(i).getUser1Ip())
					||request.getRemoteAddr().equals(roomList.get(i).getUser2Ip())) {
				return roomList.get(i).cardData(request);
			}
		}
		return null;
	}
	
	public JSONObject receiveCard(HttpServletRequest request){
		for (int i = 0; i < roomList.size(); i++) {
			if (request.getRemoteAddr().equals(roomList.get(i).getUser1Ip())
					|| request.getRemoteAddr().equals(roomList.get(i).getUser2Ip())) {
				return roomList.get(i).receiveCard(request);
			}
		}
		return null;
	}
	
	public JSONObject pushCard(HttpServletRequest request){
		for (int i = 0; i < roomList.size(); i++) {
			if (request.getRemoteAddr().equals(roomList.get(i).getUser1Ip())
					||request.getRemoteAddr().equals(roomList.get(i).getUser2Ip())) {
				return roomList.get(i).pushCard(request);
			}
		}
		return null;
	}
	
	public void deleteRoom(HttpServletRequest request){
		for (int i = 0; i < roomList.size(); i++) {
			if (request.getRemoteAddr().equals(roomList.get(i).getUser1Ip())||request.getRemoteAddr().equals(roomList.get(i).getUser2Ip())) {
				roomList.remove(i);
			}
		}
	}
	public void giveUp(HttpServletRequest request){
		for (int i = 0; i < roomList.size(); i++) {
			if (request.getRemoteAddr().equals(roomList.get(i).getUser1Ip())||request.getRemoteAddr().equals(roomList.get(i).getUser2Ip())) {
			
			}
		}
	}
}
