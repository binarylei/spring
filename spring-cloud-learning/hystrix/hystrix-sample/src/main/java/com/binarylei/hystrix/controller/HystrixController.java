package com.binarylei.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
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
            fallbackMethod = "fallback")
    public String hystrix(@RequestParam int costTime) throws InterruptedException {
        Thread.sleep(costTime);
        logger.info("hystrix info");
        return "sleep: " + costTime + " ms";
    }

    public String fallback(@RequestParam int costTime) {
        logger.error("hystrix error");
        return "timeout";
    }

    /**
     * 配置单独的线程，则所有的业务都在默认的线程池中运行
     * 线程池配置：HystrixThreadPoolProperties.Setter
     */
    @GetMapping("hystrix2")
    @HystrixCommand(
            threadPoolKey = "hystrix2-thread-pool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "2"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            },
            commandProperties = {
                    @HystrixProperty(
                            name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")},
            fallbackMethod = "fallback")
    public String hystrix2(@RequestParam int costTime) throws InterruptedException {
        Thread.sleep(costTime);
        logger.info("hystrix2 info");
        return "sleep: " + costTime + " ms";
    }


    // 注意 fallback 必须参数相同
    // 多线程并发时 SEMAPHORE 才生效??? 100(线程)*10
    @GetMapping("hystrix3")
    @HystrixCommand(fallbackMethod = "fallback",
            commandProperties = {
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "SEMAPHORE"),
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS, value = "2")})
    public String hystrix3() {
        logger.info("hystrix3 info");
        return "hystrix3";
    }

    public String fallback() {
        logger.error("hystrix error");
        return "timeout";
    }

}
