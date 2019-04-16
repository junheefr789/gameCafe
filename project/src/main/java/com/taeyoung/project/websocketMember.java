package com.taeyoung.project;

import org.springframework.web.socket.WebSocketSession;

public class websocketMember {
	private WebSocketSession websocketSession;
	private String member_id;
	
	public websocketMember() {
	}

	public WebSocketSession getWebsocketSession() {
		return websocketSession;
	}

	public void setWebsocketSession(WebSocketSession websocketSession) {
		this.websocketSession = websocketSession;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	
}
