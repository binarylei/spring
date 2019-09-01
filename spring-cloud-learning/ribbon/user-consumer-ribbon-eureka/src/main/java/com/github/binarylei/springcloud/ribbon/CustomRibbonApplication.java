package com.github.binarylei.springcloud.ribbon;

import com.github.binarylei.springcloud.config.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * 自定义负载均衡算法 RibbonConfig，默认为轮询方法，修改为随机算法
 *
 * @author leigang
 * @version 2019-03-20
 */
@SpringBootApplication
@EnableEurekaClient
// 注意 configuration 对应的配置文件不能被 ComponentScan 扫描到
@RibbonClient(name = "user-provider2", configuration = RibbonConfig.class)
public class CustomRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomRibbonApplication.class);
    }
}
