package com.study.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.entity.CofUserAccount;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wufeng
 * @since 2019-10-24
 */
public interface ICofUserAccountService extends IService<CofUserAccount> {

    IPage<CofUserAccount> selectUserPage(Page<CofUserAccount> page, Integer flag);
}


