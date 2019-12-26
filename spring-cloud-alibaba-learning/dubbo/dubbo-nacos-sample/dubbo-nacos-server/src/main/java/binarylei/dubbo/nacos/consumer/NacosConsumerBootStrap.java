package binarylei.dubbo.nacos.consumer;

import binarylei.dubbo.api.EchoService;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * EnableDubbo 默认会扫描DubboServerApplication路径下的文件
 *
 * @author binarylei
 * @version 2019-08-25
 * @since 2.0.0
 */
@EnableDubbo
@EnableNacosConfig
@NacosPropertySource(dataId = "nacos-consumer.properties")
public class NacosConsumerBootStrap {

    static {
        System.setProperty("nacos.server-addr", "192.168.139.101:8848");
    }

    @Reference
    private EchoService echoService;

    // 静态注解
    @Value("${dubbo.application.name}")
    private String applicationName;

    // 动态注解
    @NacosValue(value = "${dubbo.application.name}", autoRefreshed = true)
    private String ancosApplicationName;

    @NacosConfigListener(dataId = "nacos-consumer.properties")
    private void onChange(Properties properties) {
        System.out.println("onChange(Properties): " + properties);
    }

    @NacosConfigListener(dataId = "nacos-consumer.properties")
    private void onChange(String properties) {
        System.out.println("onChange(String): " + properties);
    }

    @PostConstruct
    private void run() {
        while (true) {
            System.out.println("==============begin=============");
            System.out.println("referce: " + echoService.echo("hello, binarylei"));
            System.out.println("applicationName(@Value): " + applicationName);
            System.out.println("ancosApplicationName(@NacosValue): " + ancosApplicationName);
            System.out.println("==============end=============");

            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(NacosConsumerBootStrap.class);
        context.refresh();

        System.in.read();
    }
}
