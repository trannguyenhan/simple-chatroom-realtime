package controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@ServerEndpoint("/chatServerEndpoint")
public class ChatServerContreller {
	static Set<Session> listUser = (Set<Session>) Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void handleOpen(Session session) {
		listUser.add(session);
		System.out.println("connect with client " + session.getId());
	}
	
	@OnClose
	public void hanldeClose(Session session) {
		listUser.remove(session);
		System.out.println("disconnect...");
	}
	
	@OnMessage
	public void handleMessage(String message, Session session) throws IOException {
		JsonElement element = JsonParser.parseString(message);
		JsonObject obj = element.getAsJsonObject();
		
		String username = obj.get("username").toString();
		username = username.substring(1, username.length() - 1);
		
		String content = obj.get("message").toString();
		content = content.substring(1, content.length() - 1);
		
		System.out.println(message);
		System.out.println(username);
		System.out.println(content);
		
		String s = username + " : " + content;
		Iterator<Session> iterator = listUser.iterator();
		while(iterator.hasNext()) {
			iterator.next().getBasicRemote().sendText(s);
		}
	}
}
