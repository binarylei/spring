package binarylei.sc.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author binarylei
 * @version 2019-09-24
 */
@FeignClient(value = "echo-server",url = "http://127.0.0.1:10010")
public interface EchoService {

    @GetMapping("/echo/{msg}")
    String echo(@PathVariable String msg);
}
