package com.github.binarylei.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author: leigang
 * @version: 2018-08-27
 */
public interface DemoService extends Remote {

    User service() throws RemoteException;

}
