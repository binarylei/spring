package binarylei.sc.feign.controller;

import binarylei.sc.feign.service.EchoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author binarylei
 * @version 2019-09-24
 */
@RestController
public class EchoController {

    private EchoService echoService;

    public EchoController(EchoService echoService) {
        this.echoService = echoService;
    }

    @GetMapping("/echo/{msg}")
    public String echo(@PathVariable String msg) {
        return echoService.echo(msg);
    }
}
