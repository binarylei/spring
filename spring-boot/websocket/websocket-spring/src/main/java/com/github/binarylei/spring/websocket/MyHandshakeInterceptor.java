package com.github.binarylei.spring.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MyHandshakeInterceptor implements HandshakeInterceptor {

    // 初次握手访问前
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse serverHttpResponse,
                                   WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
            String username = (String) servletRequest.getSession().getAttribute("name");
            System.out.println("获取session里面的name-------------------" + username);
            map.put("WEBSOCKET_USERNAME", username);
            servletRequest.getSession().setAttribute("WEBSOCKET_USERNAME", username);
        }
        return true;
    }

    // 初次握手访问后
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                               WebSocketHandler webSocketHandler, Exception e) {
    }
}
