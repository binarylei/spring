package com.binarylei.hystrix.collapser.service;

import java.util.List;

/**
 * @author binarylei
 * @version 2019-09-23
 */
public interface UserService {

    User find(Long id);

    List<User> findAll(List<Long> ids);
}
