package com.taeyoung.project;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.taeyoung.project.member.memberInfo;

public class socketHandler extends TextWebSocketHandler{
	
	private List<websocketMember> sessionList = new ArrayList<websocketMember>();
	
	public void afterConnectionEstablished(WebSocketSession session) throws IOException{
		for (int i = 0; i < sessionList.size(); i++) {
			session.sendMessage(new TextMessage("%"+sessionList.get(i).getMember_id()));
		}
		Map<String, Object> map = session.getAttributes();
		memberInfo mi = (memberInfo) map.get("member");
		String member_id = mi.getMember_id()+"("+mi.getMember_name()+")";
		for (int i = 0; i < sessionList.size(); i++) {
			sessionList.get(i).getWebsocketSession().sendMessage(new TextMessage("%"+member_id));
		}
		websocketMember wm = new websocketMember();
		wm.setMember_id(member_id);
		wm.setWebsocketSession(session);
		sessionList.add(wm);
	}
	
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException{
		if (message.getPayload().contains("&")) {
			String inviteId = message.getPayload().replace("&", "");
			String member_id = null;
			for (int i = 0; i < sessionList.size(); i++) {
				if (sessionList.get(i).getWebsocketSession()==session) {
					member_id = sessionList.get(i).getMember_id();
				}
			}
			for (int i = 0; i < sessionList.size(); i++) {
				if (sessionList.get(i).getMember_id().equals(inviteId)) {
					sessionList.get(i).getWebsocketSession().sendMessage(new TextMessage("&"+member_id));
				}
			}
		}else if (message.getPayload().contains("%")) {
			String member_id = message.getPayload().replace("%", "");
			for (int i = 0; i < sessionList.size(); i++) {
				if (sessionList.get(i).getMember_id().equals(member_id)) {
					sessionList.get(i).getWebsocketSession().sendMessage(new TextMessage("*"+member_id));
				}
			}
		}else if (message.getPayload().contains("_")) {
			String member_id = message.getPayload().replace("_", "");
			for (int i = 0; i < sessionList.size(); i++) {
				if (sessionList.get(i).getMember_id().equals(member_id)) {
					sessionList.get(i).getWebsocketSession().sendMessage(new TextMessage("*No"));
				}
			}
		}else{
			String member_id = null;
			for (int i = 0; i < sessionList.size(); i++) {
				if (sessionList.get(i).getWebsocketSession()==session) {
					member_id = sessionList.get(i).getMember_id();
				}
			}
			for (int i = 0; i < sessionList.size(); i++) {
				sessionList.get(i).getWebsocketSession().sendMessage(new TextMessage("_"+member_id+"님: "+message.getPayload()));
			}
		}
	}
	
	public void afterConnectionClosed(WebSocketSession session, CloseStatus close) throws IOException{
		String member_id = null;
		for (int i = 0; i < sessionList.size(); i++) {
			if (sessionList.get(i).getWebsocketSession()==session) {
				member_id = sessionList.get(i).getMember_id();
				sessionList.remove(i);
			}
		}
		for (int i = 0; i < sessionList.size(); i++) {
			sessionList.get(i).getWebsocketSession().sendMessage(new TextMessage("#"+member_id));
		}
	}
}
