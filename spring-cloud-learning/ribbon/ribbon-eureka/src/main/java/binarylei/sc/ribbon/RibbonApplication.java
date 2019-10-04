package binarylei.sc.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author leigang
 * @version 2019-03-20
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient("user-provider")
//@RibbonClients({@RibbonClient("..."), @RibbonClient("...")})
public class RibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonApplication.class);
    }

    @Bean("restTemplateRibbon")
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
