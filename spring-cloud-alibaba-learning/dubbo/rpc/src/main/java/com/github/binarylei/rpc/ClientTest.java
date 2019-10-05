package com.github.binarylei.rpc;

import com.github.binarylei.rpc.service.User;
import com.github.binarylei.rpc.service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: leigang
 * @version: 2018-08-28
 */
public class ClientTest {

    private final static int PORT=5555;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        UserService userService = RpcProxy.createProxy(UserService.class);
        User user = userService.getUser();
        System.out.println(user);
    }
}
