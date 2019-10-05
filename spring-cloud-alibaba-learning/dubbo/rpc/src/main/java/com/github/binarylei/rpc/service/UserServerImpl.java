package com.github.binarylei.rpc.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author: leigang
 * @version: 2018-08-27
 */
public class UserServerImpl implements UserService {

    @Override
    public String sayHello() throws RemoteException {
        return "Hello World";
    }

    @Override
    public User getUser() throws RemoteException {
        return new User("binarylei", "123456");
    }
}