package com.kaede.database.service.impl;

import com.kaede.database.mapper.UserMapper;
import com.kaede.database.pojo.User;
import com.kaede.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kaede
 * @create 2022-08-29 15:59
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

}
