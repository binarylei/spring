package binarylei.hystrix.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * @author leigang
 * @version 2019-03-20
 */
@RestController
public class HystrixApiController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("hystrix/api")
    public String hystrix(@RequestParam int costTime) throws InterruptedException {
        HystrixApiCommand command = new HystrixApiCommand("MyHystrixApiCommand", () -> {
            Thread.sleep(costTime);
            logger.info("hystrix info");
            return "sleep: " + costTime + " ms";
        });
        return command.execute();
    }

    // 超时时间为100ms
    public class HystrixApiCommand extends HystrixCommand<String> {
        private String name;
        private Callable<String> callable;

        public HystrixApiCommand(String name, Callable<String> callable) {
            super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"), 100);
            this.name = name;
            this.callable = callable;
        }

        @Override
        protected String run() {
            try {
                return callable.call();
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected String getFallback() {
            return "Fallback";
        }
    }
}
