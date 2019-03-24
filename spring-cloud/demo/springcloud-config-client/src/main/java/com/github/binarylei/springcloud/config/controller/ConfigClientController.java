package com.github.binarylei.springcloud.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leigang
 * @version 2019-03-22
 */
@RestController
public class ConfigClientController {

    @Value("${profiles}")
    private String profiles;

    @GetMapping("dev")
    public String dev() {
        return profiles;
    }
}
