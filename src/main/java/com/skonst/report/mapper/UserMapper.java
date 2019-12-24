package com.skonst.report.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skonst.report.entity.User;

public interface UserMapper extends BaseMapper<User> {

    String getNameById(Integer i);
}

