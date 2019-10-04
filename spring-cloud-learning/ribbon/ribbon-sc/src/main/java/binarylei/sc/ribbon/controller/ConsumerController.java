package binarylei.sc.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author leigang
 * @version 2019-03-20
 */
@RestController
public class ConsumerController {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("restTemplate")
    public String restTemplate() {
        return restTemplate.getForObject("http://localhost:10201/user/2", String.class);
    }

    // user-provider.ribbon.listOfServers=
    @GetMapping("ribbon")
    public String ribbon() {
        return restTemplate.getForObject("http://user-provider/user/2", String.class);
    }

    @GetMapping("ribbon/balancer")
    public String ribbonBalancer() {
        ServiceInstance instance = loadBalancerClient.choose("user-provider");
        return restTemplate.getForObject(instance.getUri() + "/user/2", String.class);
    }

    @GetMapping("ribbon/balancer2")
    public String ribbonBalancer2() throws IOException {
        return loadBalancerClient.execute("user-provider", instance -> {
            return restTemplate.getForObject(instance.getUri() + "/user/2", String.class);
        });
    }

    @GetMapping("test")
    public void test() {
        ServiceInstance instance = loadBalancerClient.choose("user-provider");
        System.err.println("user-provider: " + instance.getUri());
    }

}
