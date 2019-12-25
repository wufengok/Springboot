package com.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.entity.CofUserAccount;
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


