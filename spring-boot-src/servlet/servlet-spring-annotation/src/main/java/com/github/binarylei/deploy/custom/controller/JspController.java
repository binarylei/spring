package com.github.binarylei.deploy.custom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: leigang
 * @version: 2018-10-19
 */
@Controller
public class JspController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
