package binarylei.springcloud.euraka.naming.producer.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leigang
 * @version 2019-03-20
 */
@RestController
public class ProviderUserController {

    @Autowired
    private EurekaClient eurekaClient;
    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("user/{id}")
    public String user(@PathVariable("id") String id) {
        return id;
    }

    // 获取applicationName服务的真实地址url
    @GetMapping("url-info")
    public String info() {
        InstanceInfo instance = eurekaClient.getNextServerFromEureka(applicationName, false);
        return instance.getHomePageUrl();
    }
}
