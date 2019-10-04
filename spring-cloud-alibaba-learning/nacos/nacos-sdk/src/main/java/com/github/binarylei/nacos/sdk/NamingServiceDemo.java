package com.github.binarylei.nacos.sdk;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;
import java.util.Properties;

/**
 * @author binarylei
 * @version 2019-08-27
 * @since 2.0.0
 */
public class NamingServiceDemo {

    private static String serverAddr = "192.168.139.101:8848";
    private static String group = "DEFAULT-GROUP";

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);

        // 1. 创建ConfigService
        NamingService namingService = NacosFactory.createNamingService(properties);

        // 2. 设置配置
        namingService.registerInstance("server_test_1", "1.1.1.1", 80);
        List<Instance> allInstances = namingService.getAllInstances("server_test_1");
        System.out.println(allInstances.size());

        // 阻塞线程
        System.in.read();
    }
}
