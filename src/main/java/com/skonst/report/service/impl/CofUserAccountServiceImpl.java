package com.skonst.report.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skonst.report.entity.CofUserAccount;
import com.skonst.report.mapper.CofUserAccountMapper;
import com.skonst.report.service.ICofUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wufeng
 * @since 2019-10-24
 */
@Service
public class CofUserAccountServiceImpl extends ServiceImpl<CofUserAccountMapper, CofUserAccount> implements ICofUserAccountService {

    @Autowired
    CofUserAccountMapper cofUserAccountMapper;

    @Override
    public IPage<CofUserAccount> selectUserPage(Page<CofUserAccount> page, Integer flag) {
        //return cofUserAccountMapper.selectPage(page,queryWrapper);
        return cofUserAccountMapper.selectUserPage(page,flag);
    }
}
