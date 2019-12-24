package com.skonst.report.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.skonst.report.entity.User;

public interface IUserService extends IService<User> {
    String getNameById(Integer i);

}


