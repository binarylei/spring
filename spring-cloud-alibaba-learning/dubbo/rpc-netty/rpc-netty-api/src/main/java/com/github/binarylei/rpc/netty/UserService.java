package com.github.binarylei.rpc.netty;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author: leigang
 * @version: 2018-08-27
 */
public interface UserService extends Remote {

    String sayHello() throws RemoteException;

    User getUser() throws RemoteException;
}
