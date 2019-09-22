package com.binarylei.hystrix.collapser;

import com.binarylei.hystrix.collapser.service.User;
import com.binarylei.hystrix.collapser.service.UserService;
import com.netflix.hystrix.HystrixCommand;

import java.util.List;

import static com.netflix.hystrix.HystrixCommandGroupKey.Factory.asKey;

/**
 * api 编码方式
 *
 * @author binarylei
 * @version 2019-09-23
 */
public class UserBatchCommand extends HystrixCommand<List<User>> {

    UserService userService;
    List<Long> userIds;

    public UserBatchCommand(UserService userService, List<Long> userIds) {
        super(Setter.withGroupKey(asKey("userServiceCommand")));
        this.userIds = userIds;
        this.userService = userService;
    }

    @Override
    protected List<User> run() throws Exception {
        return userService.findAll(userIds);
    }

}
