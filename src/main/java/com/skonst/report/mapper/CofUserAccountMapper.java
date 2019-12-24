package com.skonst.report.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skonst.report.entity.CofUserAccount;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wufeng
 * @since 2019-10-24
 */
public interface CofUserAccountMapper extends BaseMapper<CofUserAccount> {

    IPage<CofUserAccount> selectUserPage(Page<CofUserAccount> page, @Param("flag") Integer flag);
}


