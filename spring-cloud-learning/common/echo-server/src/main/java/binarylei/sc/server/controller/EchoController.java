package binarylei.sc.server.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author binarylei
 * @version 2019-09-24
 */
@RestController
public class EchoController {

    private String serverIp;
    private Environment environment;

    public EchoController(Environment environment) {
        this.environment = environment;
        try {
            serverIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            // ingore
        }
    }

    @GetMapping("/echo/{msg}")
    public String echo(@PathVariable String msg) {
        return String.format("%s:%s -> %s",
                serverIp, environment.getProperty("local.server.port"), msg);
    }
}
