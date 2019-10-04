package binarylei.sc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

/**
 * @author leigang
 * @version 2019-03-23
 */
@SpringBootApplication
//@EnableScheduling
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @Autowired
    private ContextRefresher contextRefresher;

    // 定时从 config server 中刷新配置文件
    @Scheduled(fixedRate = 1000L)
    public void refresh() {
        Set<String> keys = contextRefresher.refresh();
        if (!keys.isEmpty()) {
            System.out.println(keys);
        }
    }
}
