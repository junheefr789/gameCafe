package com.taeyoung.project.onecard;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;

public class onecardRoom {
	
	private String inviteId;
	private String user1Id;
	private String user2Id;
	private String user1Ip;
	private String user2Ip;
	private ArrayList<Integer> card;
	private ArrayList<Integer> user1Card;
	private ArrayList<Integer> user2Card;
	private boolean[] visited;
	private boolean user1Attack;
	private boolean user2Attack;
	private ArrayList<Integer> attackCard;
	
	public onecardRoom() {
		user1Id="member1";
		user2Id="member2";
		card= new ArrayList<>();
		user1Card = new ArrayList<>();
		user2Card = new ArrayList<>();
		visited = new boolean[2];
		user1Attack = false;
		user2Attack = false;
		attackCard = new ArrayList<>();
		
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 13; j++) {
				card.add(i*100+j);
			}
		}
		card.add(501);
		card.add(502);
		int t;
		int w;
		Random r = new Random();
		for (int i = 0; i < card.size(); i++) {
			t = r.nextInt(card.size()-1);
			if (t!=i) {
				w = card.get(t);
				card.set(t, card.get(i));
				card.set(i, w);
			}
		}
		for (int i = 0; i < 7; i++) {
			user1Card.add(card.get(card.size()-1));
			card.remove(card.size()-1);
		}
		for (int i = 0; i < 7; i++) {
			user2Card.add(card.get(card.size()-1));
			card.remove(card.size()-1);
		}
		visited[0]=false;
		visited[1]=false;
	}

	public String getInviteId() {
		return inviteId;
	}

	public void setInviteId(String inviteId) {
		this.inviteId = inviteId;
	}

	public void setUser1Id(String user1Id) {
		this.user1Id = user1Id;
	}


	public void setUser2Id(String user2Id) {
		this.user2Id = user2Id;
	}

	public String getUser1Ip() {
		return user1Ip;
	}

	public void setUser1Ip(String user1Ip) {
		this.user1Ip = user1Ip;
	}

	public String getUser2Ip() {
		return user2Ip;
	}

	public void setUser2Ip(String user2Ip) {
		this.user2Ip = user2Ip;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject cardData(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		JSONArray ready = new JSONArray();
		if (user1Id.equals("member1")||user2Id.equals("member2")) {
			ready.add("ready");
			jo.put("ready", ready);
			return jo;
		}
		jo.put("ready", ready);
		JSONArray winner = new JSONArray();
		if (user1Card.size()>13||user2Card.size()==0) {
			winner.add(user2Id);
			jo.put("winner", winner);
			return jo;
		}else if (user2Card.size()>13||user1Card.size()==0) {
			winner.add(user1Id);
			jo.put("winner", winner);
			return jo;
		}else{
			jo.put("winner", winner);
		}
		if (visited[0]&&request.getRemoteAddr().equals(user1Ip)) {
			return null;
		}
		if (visited[1]&&request.getRemoteAddr().equals(user2Ip)) {
			return null;
		}
		JSONArray onecard = new JSONArray();
		if (user1Card.size()==1) {
			onecard.add(user1Id);
			jo.put("onecard", onecard);
		}else if (user2Card.size()==1) {
			onecard.add(user2Id);
			jo.put("onecard", onecard);
		}else{
			jo.put("onecard", onecard);
		}
		JSONArray floorCard = new JSONArray();
		floorCard.add(card.get(0));
		jo.put("floorCard", floorCard);
		JSONArray user1 = new JSONArray();
		for (int i = 0; i < user1Card.size(); i++) {
			user1.add(user1Card.get(i));
		}
		JSONArray user2 = new JSONArray();
		for (int i = 0; i < user2Card.size(); i++) {
			user2.add(user2Card.get(i));
		}
		JSONArray defend = new JSONArray();
		JSONArray attack = new JSONArray();
		if (user1Attack&&request.getRemoteAddr().equals(user2Ip)) {
			attack.add("attack");
			if (attackCard.get(attackCard.size()-1)>500) {
				for (int i = 0; i < user2Card.size(); i++) {
					if (user2Card.get(i)>500) {
						defend.add(user2Card.get(i));
					}
				}
				jo.put("attack", attack);
				jo.put("defend", defend);
				if (defend.size()==0) {
					for (int i = 0; i < attackCard.size(); i++) {
						if (attackCard.get(i)==502) {
							for (int j = 0; j < 5; j++) {
								user2Card.add(card.get(card.size()-1));
								card.remove(card.size()-1);
							}
						}else if (attackCard.get(i)==501) {
							for (int j = 0; j < 3; j++) {
								user2Card.add(card.get(card.size()-1));
								card.remove(card.size()-1);
							}
						}else{
							for (int j = 0; j < 2; j++) {
								user2Card.add(card.get(card.size()-1));
								card.remove(card.size()-1);
							}
						}
					}
					user2.clear();
					for (int i = 0; i < user2Card.size(); i++) {
						user2.add(user2Card.get(i));
					}
					attackCard.clear();
				}
				user1Attack = false;
			}else{
				for (int i = 0; i < user2Card.size(); i++) {
					if (user2Card.get(i)%100==1||user2Card.get(i)>500) {
						defend.add(user2Card.get(i));
					}
				}
				jo.put("attack", attack);
				jo.put("defend", defend);
				if (defend.size()==0) {
					for (int i = 0; i < attackCard.size(); i++) {
						if (attackCard.get(i)==502) {
							for (int j = 0; j < 5; j++) {
								user2Card.add(card.get(card.size()-1));
								card.remove(card.size()-1);
							}
						}else if (attackCard.get(i)==501) {
							for (int j = 0; j < 3; j++) {
								user2Card.add(card.get(card.size()-1));
								card.remove(card.size()-1);
							}
						}else{
							for (int j = 0; j < 2; j++) {
								user2Card.add(card.get(card.size()-1));
								card.remove(card.size()-1);
							}
						}
					}
					attackCard.clear();
				}
				user2.clear();
				for (int i = 0; i < user2Card.size(); i++) {
					user2.add(user2Card.get(i));
				}
				user1Attack = false;
			}
		}else if (user2Attack&&request.getRemoteAddr().equals(user1Ip)) {
			attack.add("attack");
			if (attackCard.get(attackCard.size()-1)>500) {
				for (int i = 0; i < user1Card.size(); i++) {
					if (user1Card.get(i)>500) {
						defend.add(user1Card.get(i));
					}
				}
				jo.put("attack", attack);
				jo.put("defend", defend);
				if (defend.size()==0) {
					for (int i = 0; i < attackCard.size(); i++) {
						if (attackCard.get(i)==502) {
							for (int j = 0; j < 5; j++) {
								user1Card.add(card.get(card.size()-1));
								card.remove(card.size()-1);
							}
						}else if (attackCard.get(i)==501) {
							for (int j = 0; j < 3; j++) {
								user1Card.add(card.get(card.size()-1));
								card.remove(card.size()-1);
							}
						}else{
							for (int j = 0; j < 2; j++) {
								user1Card.add(card.get(card.size()-1));
								card.remove(card.size()-1);
							}
						}
					}
					attackCard.clear();
				}
				user1.clear();
				for (int i = 0; i < user1Card.size(); i++) {
					user1.add(user1Card.get(i));
				}
				user2Attack = false;
			}else{
				for (int i = 0; i < user1Card.size(); i++) {
					if (user1Card.get(i)%100==1||user1Card.get(i)>500) {
						defend.add(user1Card.get(i));
					}
				}
				jo.put("attack", attack);
				jo.put("defend", defend);
				if (defend.size()==0) {
					attackCard.clear();
					for (int i = 0; i < attackCard.size(); i++) {
						if (attackCard.get(i)==502) {
							for (int j = 0; j < 5; j++) {
								user2Card.add(card.get(card.size()-1));
								card.remove(card.size()-1);
							}
						}else if (attackCard.get(i)==501) {
							for (int j = 0; j < 3; j++) {
								user2Card.add(card.get(card.size()-1));
								card.remove(card.size()-1);
							}
						}else{
							for (int j = 0; j < 2; j++) {
								user2Card.add(card.get(card.size()-1));
								card.remove(card.size()-1);
							}
						}
					}
				}
				for (int i = 0; i < user1Card.size(); i++) {
					user1.add(user1Card.get(i));
				}
				user2Attack = false;
			}
		}else{
			jo.put("attack", attack);
			jo.put("defend", defend);
		}
		jo.put(user1Id, user1);
		jo.put(user2Id, user2);
		if (request.getRemoteAddr().equals(user1Ip)) {
			visited[0]=true;
		}
		if (request.getRemoteAddr().equals(user2Ip)) {
			visited[1]=true;
		}
		return jo;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject receiveCard(HttpServletRequest request){
		if (request.getRemoteAddr().equals(user1Ip)) {
			user1Card.add(card.get(card.size()-1));
			card.remove(card.size()-1);
			JSONObject jo = new JSONObject();
			JSONArray ja = new JSONArray();
			ja.add(user1Card.get(user1Card.size()-1));
			jo.put("receiveCard", ja);
			return jo;
		}else{
			user2Card.add(card.get(card.size()-1));
			card.remove(card.size()-1);
			JSONObject jo = new JSONObject();
			JSONArray ja = new JSONArray();
			ja.add(user2Card.get(user2Card.size()-1));
			jo.put("receiveCard", ja);
			return jo;
		}
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject pushCard(HttpServletRequest request){
		if (request.getRemoteAddr().equals(user1Ip)) {
			String[] pushCard = request.getParameterValues("pushCard");
			for (int i = 0; i < pushCard.length; i++) {
				card.add(i,Integer.parseInt(pushCard[i]));
				if (Integer.parseInt(pushCard[i])%100==1||Integer.parseInt(pushCard[i])>500) {
					attackCard.add(Integer.parseInt(pushCard[i]));
					user1Attack = true;
				}
				for (int j = 0; j < user1Card.size(); j++) {
					if (Integer.parseInt(pushCard[i])==user1Card.get(j)) {
						user1Card.remove(j);
					}
				}
			}
			JSONObject jo = new JSONObject();
			JSONArray ja = new JSONArray();
			ja.add("success");
			jo.put("pushCard", ja);
			visited[0]=false;
			visited[1]=false;
			return jo;
		}else{
			String[] pushCard = request.getParameterValues("pushCard");
			for (int i = 0; i < pushCard.length; i++) {
				card.add(i,Integer.parseInt(pushCard[i]));
				if (Integer.parseInt(pushCard[i])%100==1||Integer.parseInt(pushCard[i])>500) {
					attackCard.add(Integer.parseInt(pushCard[i]));
					user2Attack = true;
				}
				for (int j = 0; j < user2Card.size(); j++) {
					if (Integer.parseInt(pushCard[i])==user2Card.get(j)) {
						user2Card.remove(j);
					}
				}
			}
			JSONObject jo = new JSONObject();
			JSONArray ja = new JSONArray();
			ja.add("success");
			jo.put("pushCard", ja);
			visited[0]=false;
			visited[1]=false;
			return jo;
		}
	}
}
