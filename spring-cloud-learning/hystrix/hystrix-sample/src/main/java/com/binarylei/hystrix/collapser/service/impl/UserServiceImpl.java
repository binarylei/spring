package com.binarylei.hystrix.collapser.service.impl;

import com.binarylei.hystrix.collapser.service.User;
import com.binarylei.hystrix.collapser.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 非注解实现：本质是将调 find 的请求拦截下来，调用 findAll 方法
 *
 * @author binarylei
 * @version 2019-09-23
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User find(Long id) {
        return restTemplate.getForObject("http://USER-SERVICE/users/{1}", User.class, id);
    }

    @Override
    public List<User> findAll(List<Long> ids) {
        return restTemplate.getForObject("http://USER-SERVICE/users?ids={1}",
                List.class, StringUtils.join(ids, ","));
    }
}
