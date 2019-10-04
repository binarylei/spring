package com.github.binarylei.rpc.netty.consumer;

import com.github.binarylei.rpc.netty.InvokerMsg;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: leigang
 * @version: 2018-08-28
 */
public class RpcProxy {

    public static <T> T createProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz},
                new MethodProxy(clazz));
    }

    private static class MethodProxy implements InvocationHandler {
        private Class clazz;

        public MethodProxy(Class clazz) {
            this.clazz = clazz;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return rpcInvoke(proxy, method, args);
        }

        private Object rpcInvoke(Object proxy, Method method, Object[] args) {
            InvokerMsg msg = new InvokerMsg(clazz.getName(), method.getName(),
                    method.getParameterTypes(), args);

            final RpcProxyHandler consumerHandler = new RpcProxyHandler();
            EventLoopGroup workgroup = new NioEventLoopGroup();
            try {
                Bootstrap b = new Bootstrap();
                b.group(workgroup)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY, true)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel sc) throws Exception {
                                sc.pipeline()
//                                        .addLast("", new LengthFieldBasedFrameDecoder(1))
//                                        .addLast("", new LengthFieldPrepender(1))
//                                        .addLast("", new ObjectEncoder())
//                                        .addLast("", new ObjectDecoder(Integer.MAX_VALUE))
                                        .addLast(new RpcProxyHandler());
                            }
                        });

                ChannelFuture cf1 = b.connect("127.0.0.1", 8765).sync();

                // 发送代理消息给服务器
                cf1.channel().writeAndFlush(msg).sync();
                cf1.channel().closeFuture().sync();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                workgroup.shutdownGracefully();
            }
            return consumerHandler;
        }
    }
}
