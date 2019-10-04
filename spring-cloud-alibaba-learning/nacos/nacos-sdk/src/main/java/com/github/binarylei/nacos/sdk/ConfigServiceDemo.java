package com.github.binarylei.nacos.sdk;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author binarylei
 * @version 2019-08-27
 * @since 2.0.0
 */
public class ConfigServiceDemo {

    private static String serverAddr = "192.168.139.101:8848";
    private static String dataId = "example";
    private static String group = "DEFAULT-GROUP";

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);

        // 1. 创建ConfigService
        ConfigService configService = NacosFactory.createConfigService(properties);

        // 2. 设置配置
        boolean succeed = configService.publishConfig(dataId, group, "name=binarylei");
        System.out.println("设置配置：" + succeed);

        // 设置配置后直接获取配置为 null
        // TimeUnit.SECONDS.sleep(1);
        // 3. 获取配置
        String content = configService.getConfig(dataId, group, 5000);
        System.out.println("获取配置：" + content);

        // 4. 监听配置
        configService.addListener(dataId, group, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("监听配置：" + configInfo);
            }
        });
        // 4.2 删除监听
        //configService.removeListener(dataId, group, listener);

        // 5. 修改配置
        succeed = configService.publishConfig(dataId, group, "name=binarylei2");
        System.out.println("修改配置：" + succeed);

        // 6. 删除配置
        succeed = configService.removeConfig(dataId, group);
        System.out.println("删除配置：" + succeed);

        // 阻塞线程
        System.in.read();
    }
}
