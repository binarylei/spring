package com.github.binarylei.rpc;

import com.github.binarylei.rpc.service.UserServerImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: leigang
 * @version: 2018-08-28
 */
public class ServiceTest {

    private final static int PORT = 5555;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket server = new ServerSocket(PORT);
        while (true) {
            Socket socket = server.accept();
            handler(socket);
        }

    }

    private static void handler(Socket socket) {

        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());) {

            String clazzName = ois.readUTF();
            String methodName = ois.readUTF();
            Class[] parameterTypes = (Class[]) ois.readObject();
            Object[] args = (Object[]) ois.readObject();

            Object ret = invoke(clazzName, methodName, parameterTypes, args);
            ous.writeObject(ret);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Object invoke(String clazzName, String methodName, Class[] parameterTypes, Object[] args) {
        Object ret = null;
        try {
            Class<?> clazz = Class.forName(clazzName);
            Method method = clazz.getMethod(methodName, parameterTypes);
            ret = method.invoke(getRpcProvider(clazzName), args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    // 获取对应的实现类
    public static Object getRpcProvider(String className) {
        return rpcProviders.get(className);
    }

    public static Map<String, Object> rpcProviders = new HashMap<>();
    static {
        registerRpcProvider("com.github.binarylei.rpc.service.UserService", new UserServerImpl());
    }

    public static void registerRpcProvider(String className, Object obj) {
        rpcProviders.put(className, obj);
    }

}
