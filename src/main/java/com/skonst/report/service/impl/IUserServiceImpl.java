package com.skonst.report.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skonst.report.entity.User;
import com.skonst.report.mapper.UserMapper;
import com.skonst.report.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String getNameById(Integer i) {
        System.out.println(i+ "========================");
        return userMapper.getNameById(i);
    }
}
