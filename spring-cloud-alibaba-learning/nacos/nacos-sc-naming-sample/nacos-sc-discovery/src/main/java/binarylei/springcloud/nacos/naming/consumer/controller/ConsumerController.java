package binarylei.springcloud.nacos.naming.consumer.controller;

import binarylei.springcloud.nacos.naming.consumer.ConsumerApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    private RestTemplate restTemplate;

    @Autowired
    private RestTemplate restTemplate1;

    @Autowired
    private ConsumerApplication.EchoService echoService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/echo-rest/{str}", method = RequestMethod.GET)
    public String rest(@PathVariable String str) {
        return restTemplate.getForObject("http://nacos-provider/echo/" + str,
                String.class);
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return restTemplate1.getForObject("http://nacos-provider", String.class);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return restTemplate1.getForObject("http://nacos-provider/test", String.class);
    }

    @RequestMapping(value = "/sleep", method = RequestMethod.GET)
    public String sleep() {
        return restTemplate1.getForObject("http://nacos-provider/sleep", String.class);
    }

    @RequestMapping(value = "/notFound-feign", method = RequestMethod.GET)
    public String notFound() {
        return echoService.notFound();
    }

    @RequestMapping(value = "/divide-feign", method = RequestMethod.GET)
    public String divide(@RequestParam Integer a, @RequestParam Integer b) {
        return echoService.divide(a, b);
    }

    @RequestMapping(value = "/divide-feign2", method = RequestMethod.GET)
    public String divide(@RequestParam Integer a) {
        return echoService.divide(a);
    }

    @RequestMapping(value = "/echo-feign/{str}", method = RequestMethod.GET)
    public String feign(@PathVariable String str) {
        return echoService.echo(str);
    }

    @RequestMapping(value = "/services/{service}", method = RequestMethod.GET)
    public Object client(@PathVariable String service) {
        return discoveryClient.getInstances(service);
    }

    @RequestMapping(value = "/services", method = RequestMethod.GET)
    public Object services() {
        return discoveryClient.getServices();
    }
}
