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

public class socketHandler extends TextWebSocketHandler{
	
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	public void afterConnectionEstablished(WebSocketSession session){
		sessionList.add(session);
		Map<String,Object> map = session.getAttributes();
		String userId = (String)map.get("userId");
	}
	
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException{
		for (WebSocketSession sess : sessionList) {
			sess.sendMessage(new TextMessage(session.getId()+""+message.getPayload()));
		}
	}
	
	public void afterConnectionClosed(WebSocketSession session, CloseStatus close){
		sessionList.remove(session);
	}
}
