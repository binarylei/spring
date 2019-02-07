package binarylei.springboot.web.controller;

import binarylei.springboot.web.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: leigang
 * @version: 2018-09-01
 */
@RestController
public class PojoController {

    @GetMapping("/v2/{user_id}")
    public User user(@PathVariable("user_id") String userId) {
        return new User(userId, "com/github/binarylei", "123456");
    }

    @GetMapping(value = "/v3/properties/to/json",
            consumes = "application/properties",
            produces = "application/json")
    public User propertiesToHJson(@RequestBody User user) {
        return new User("1", "com/github/binarylei", "123456");
    }

    @GetMapping(value = "/v3/json/to/properties",
            consumes = "application/json",
            produces = "application/properties")
    public User jsonToProperties(@RequestBody User user) {
        return new User("1", "com/github/binarylei", "123456");
    }
}
