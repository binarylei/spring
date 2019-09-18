package com.github.binarylei.springcloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author leigang
 * @version 2019-03-19
 */
@SpringBootApplication
@EnableEurekaClient
public class ConsumerApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ConsumerApplication.class);

        List<String> list = Arrays.asList("1.3.2.1", "1.8.2.1", "1.5.2.1", "1.7.2.1");
        Collections.sort(list);

        System.out.println(list);
        System.out.println(list.indexOf("1.5.2.1"));
        System.out.println(list.lastIndexOf("1.5.2.1"));
    }
}
