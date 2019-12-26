package binarylei.dubbo.nacos.provider;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

/**
 * EnableDubbo 默认会扫描DubboServerApplication路径下的文件
 *
 * @author binarylei
 * @version 2019-08-25
 * @since 2.0.0
 */
@EnableDubbo(scanBasePackages = "com.github.binarylei.dubbo.nacos.server")
@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "${nacos.server-addr}"))
@NacosPropertySource(dataId = "producer.properties")
@PropertySource("classpath:/producer_nacos.properties")
public class NacosProviderBootStrap {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(NacosProviderBootStrap.class);
        context.refresh();

        System.in.read();
    }
}
