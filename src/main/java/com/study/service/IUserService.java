package com.study.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wufeng
 * @since 2019-12-27
 */
public interface IUserService extends IService<User> {

    IPage<User> selectUserPage(Page<User> page, QueryWrapper queryWrapper);

    void insert(List list);
}




