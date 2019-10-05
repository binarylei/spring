package com.binarylei.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注意：Tomcat线程、执行线程、fallback线程隔离
 *
 * @author leigang
 * @version 2019-03-20
 */
@RestController
public class HystrixController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 不配置单独的线程，则所有的业务都在默认的线程池中运行
     * 熔断规则配置：HystrixCommandProperties.Setter
     */
    @GetMapping("hystrix")
    @HystrixCommand(
            commandProperties = {@HystrixProperty(
                    name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")},
            fallbackMethod = "error")

    public String hystrix(@RequestParam int costTime) throws InterruptedException {
        Thread.sleep(costTime);
        logger.info("hystrix info");
        return "sleep: " + costTime + " ms";
    }

    public String error(@RequestParam int costTime) {
        logger.error("hystrix error");
        return "timeout";
    }

    /**
     * 配置单独的线程，则所有的业务都在默认的线程池中运行
     * 线程池配置：HystrixThreadPoolProperties.Setter
     */
    @GetMapping("hystrix2")
    @HystrixCommand(
            threadPoolKey = "hystrix2",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "2"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            },
            commandProperties = {
                    @HystrixProperty(
                            name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")},
            fallbackMethod = "error")
    public String hystrix2(@RequestParam int costTime) throws InterruptedException {
        Thread.sleep(costTime);
        logger.info("hystrix info");
        return "sleep: " + costTime + " ms";
    }
}
