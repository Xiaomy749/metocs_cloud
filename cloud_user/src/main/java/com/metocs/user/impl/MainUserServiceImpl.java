package com.metocs.user.impl;

import com.metocs.common.annotation.DataSource;
import com.metocs.common.base.BaseServiceImpl;
import com.metocs.user.entity.MainUser;

import com.metocs.user.mapper.MainUserMapper;
import com.metocs.user.service.MainUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MainUserServiceImpl extends BaseServiceImpl<MainUserMapper,MainUser> implements
        MainUserService {

    @Autowired
    private MainUserMapper userMapper;

    @Override
    @DataSource
    public MainUser getByUserName(String userName) {
        return userMapper.getByUserName(userName);
    }
}
