package binarylei.springcloud.euraka.naming.consumer.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author binarylei
 * @version 2019-09-01
 * @since 2.0.0
 */
@RestController
public class ConsumerController {

    @Autowired
    private EurekaClient eurekaClient;

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("user-info/{id}")
    public String restTemplate(@PathVariable("id") String id) {
        String url = String.format("%s/user/%s", "http://localhost:8081/", id);
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("user/{id}")
    public String userId(@PathVariable("id") String id) {
        // 获取对应服务地址
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(
                "EURAKA-PRODUCER", false);
        String homePageUrl = instanceInfo.getHomePageUrl();
        String url = String.format("%s/user/%s", homePageUrl, id);
        return restTemplate.getForObject(url, String.class);
    }
}
