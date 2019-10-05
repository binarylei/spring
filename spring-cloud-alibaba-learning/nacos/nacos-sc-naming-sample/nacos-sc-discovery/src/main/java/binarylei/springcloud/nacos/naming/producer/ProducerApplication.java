package binarylei.springcloud.nacos.naming.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;

/**
 * @author binarylei
 * @version 2019-09-01
 * @since 2.0.0
 */
@EnableDiscoveryClient
@SpringBootApplication
@PropertySource("classpath:provider.properties")
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class);
    }
}
