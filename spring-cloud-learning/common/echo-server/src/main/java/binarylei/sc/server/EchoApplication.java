package binarylei.sc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author binarylei
 * @version 2019-09-24
 */
@SpringBootApplication
@EnableEurekaClient
public class EchoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EchoApplication.class);
    }
}
