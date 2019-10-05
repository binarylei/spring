package com.github.binarylei.server;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Protocol;
import org.junit.Test;

/**
 * @author leigang
 * @version 2019-07-31
 * @since 2.0.0
 */
public class DubboTest {

    @Test
    public void test() {
        ExtensionLoader<Protocol> extensionLoader =
                ExtensionLoader.getExtensionLoader(Protocol.class);
        Protocol protocol = extensionLoader.getExtension("dubbo");
        System.out.println(protocol.getClass().getName());
    }
}
