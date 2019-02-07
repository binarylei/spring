package com.github.binarylei.spring.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 聊天室 {@link ServerEndpoint}
 */
//@Component
@ServerEndpoint("/chat-room/{username}")
public class ChatWebSocket {

    private static Map<String, Session> sessionMap = new ConcurrentHashMap<String, Session>();

    @OnOpen
    public void openSession(@PathParam("username") String username, Session session) {
        sessionMap.put(session.getId(), session);
        sendTextAll("欢迎用户[" + username + "] 来到聊天室！");
    }

    @OnMessage
    public void onMessage(@PathParam("username") String username, Session session, String message) {
        sendTextAll("用户[" + username + "] : " + message);
    }

    @OnClose
    public void onClose(@PathParam("username") String username, Session session) {
        sessionMap.remove(session.getId());
        sendTextAll("用户[" + username + "] 已经离开聊天室了！");
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    private void sendTextAll(String message) {
        sessionMap.forEach((sessionId, session) -> {
            sendText(session, message);
        });
    }

    private void sendText(Session session, String message) {
        RemoteEndpoint.Basic basic = session.getBasicRemote();
        try {
            basic.sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
