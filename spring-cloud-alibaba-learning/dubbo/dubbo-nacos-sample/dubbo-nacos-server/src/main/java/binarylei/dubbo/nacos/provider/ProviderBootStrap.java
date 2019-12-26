package binarylei.dubbo.nacos.provider;

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
@PropertySource("classpath:/producer.properties")
public class ProviderBootStrap {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ProviderBootStrap.class);
        context.refresh();

        System.in.read();
    }
}
