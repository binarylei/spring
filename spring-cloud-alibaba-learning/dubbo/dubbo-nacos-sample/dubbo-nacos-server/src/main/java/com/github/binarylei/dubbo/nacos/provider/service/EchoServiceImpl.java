package com.github.binarylei.dubbo.nacos.provider.service;

import com.github.binarylei.dubbo.demo.api.EchoService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author binarylei
 * @version 2019-08-25
 * @since 2.0.0
 */
@Service
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String message) {
        RpcContext rpcContext = RpcContext.getContext();
        return String.format("port=%d, message=%s", rpcContext.getLocalPort(), message);
    }
}
