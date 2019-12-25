package com.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.entity.User;

public interface IUserService extends IService<User> {
    String getNameById(Integer i);

}


