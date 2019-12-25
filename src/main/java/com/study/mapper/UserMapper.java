package com.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.entity.User;

public interface UserMapper extends BaseMapper<User> {

    String getNameById(Integer i);
}

