package binarylei.hystrix.api;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author binarylei
 * @version 2019-09-22
 * @since 2.0.0
 */
public class ThreadHystrixCommand extends HystrixCommand<String> {
    private static final Logger logger = LoggerFactory.getLogger(ThreadHystrixCommand.class);

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String result = null;
                try {
                    result = new ThreadHystrixCommand().execute();
                } catch (Exception e) {
                    // ingore
                } finally {
                    logger.info(result);
                }
            }).start();
        }
        countDownLatch.countDown();
    }

    public ThreadHystrixCommand() {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(
                "ThreadHystrixCommand"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("test"))
                // 1. Hystrix熔断规则配置
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        //至少有10个请求，熔断器才进行错误率的计算
                        .withCircuitBreakerRequestVolumeThreshold(10)
                        //熔断器中断请求5秒后会进入半打开状态,放部分流量过去重试
                        .withCircuitBreakerSleepWindowInMilliseconds(100)
                        //错误率达到50开启熔断保护
                        .withCircuitBreakerErrorThresholdPercentage(50)
                        .withExecutionTimeoutEnabled(true))
                // 2. 线程池配置
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(10)));
    }

    @Override
    protected String run() {
        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(200));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "normal";
    }

    @Override
    protected String getFallback() {
        return "fallback";
    }
}
