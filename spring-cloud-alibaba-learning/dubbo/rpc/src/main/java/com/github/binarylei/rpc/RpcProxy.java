package com.github.binarylei.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @author: leigang
 * @version: 2018-08-28
 */
public class RpcProxy {

    private final static String HOST = "127.0.0.1";
    private final static int PORT = 5555;

    public static <T> T createProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz},
                (Object proxy, Method method, Object[] args) -> {
                    Socket socket = null;
                    ObjectOutputStream ous = null;
                    ObjectInputStream ois = null;
                    Object ret = null;
                    try {
                        socket = new Socket(HOST, PORT);
                        ous = new ObjectOutputStream(socket.getOutputStream());

                        ous.writeUTF(clazz.getName());
                        ous.writeUTF(method.getName());
                        ous.writeObject(method.getParameterTypes());
                        ous.writeObject(args);
                        ous.flush();

                        ois = new ObjectInputStream(socket.getInputStream());
                        ret = ois.readObject();
                        socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        ois.close();
                        ous.close();
                        socket.close();
                    }
                    return ret;
                });
    }
}
