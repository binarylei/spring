package binarylei.hystrix.api;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author binarylei
 * @version 2019-09-22
 * @since 2.0.0
 */
public class SemaphoreHystrixCommand extends HystrixCommand<String> {
    private static final Logger logger = LoggerFactory.getLogger(SemaphoreHystrixCommand.class);

    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                String result = null;
                try {
                    result = new SemaphoreHystrixCommand().execute();
                } catch (Exception e) {
                    // ingore
                } finally {
                    logger.info(count.getAndIncrement() + ": " + result);
                }
            }).start();
        }
        countDownLatch.countDown();
    }

    public SemaphoreHystrixCommand() {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(
                "ThreadHystrixCommand"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("test"))
                // 1. Hystrix熔断规则配置
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        // fallback并发数：100 = 70(fallback) + 20(success) + 10(reject)
                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(70)
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(20)
                        .withExecutionTimeoutEnabled(false)));
    }

    @Override
    protected String run() {
        return "normal";
    }

    @Override
    protected String getFallback() {
        return "fallback";
    }
}
