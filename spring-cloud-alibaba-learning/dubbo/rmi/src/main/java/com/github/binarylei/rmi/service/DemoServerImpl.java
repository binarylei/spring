package com.github.binarylei.rmi.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author: leigang
 * @version: 2018-08-27
 */
public class DemoServerImpl extends UnicastRemoteObject implements DemoService{

    public DemoServerImpl() throws RemoteException {
        super();
    }

    @Override
    public User service() throws RemoteException {
        return new User("binarylei", "123456");
    }
}