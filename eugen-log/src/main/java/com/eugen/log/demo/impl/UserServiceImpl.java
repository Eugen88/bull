package com.eugen.log.demo.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import com.eugen.log.demo.UserEntity;
import com.eugen.log.demo.UserRepository;
import com.eugen.log.demo.UserService;


@Service("userService")
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> get(UserEntity entity)
    {
        // 创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() // 构建对象
            .withMatcher("userName", GenericPropertyMatchers.contains())
            .withIgnorePaths("id", "userSex", "userAge");
        Example<UserEntity> ex = Example.of(entity, matcher);
        return userRepository.findAll(ex);
    }

}
