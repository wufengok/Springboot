package com.study.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wufeng
 * @since 2019-10-24
 */
@Controller
@RequestMapping("/cof")
public class CofUserAccountController {

    @Autowired
    ICofUserAccountService cofUserAccountService;

    @GetMapping("/testtest")
    @ResponseBody
    public String selectMyPage() {
        QueryWrapper<CofUserAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("USER_ACCOUNT_ID", 1052);

        //总页数+总记录数
        Page<CofUserAccount> page = new Page<>(2, 8);

        //调用自定义sql
//        IPage<CofUserAccount> iPage = cofUserAccountService.page(page, queryWrapper);
//
//        List<CofUserAccount> records = iPage.getRecords();
//        System.out.println("总页数:" + iPage.getPages());
//        System.out.println("总记录数:" + iPage.getTotal());
//        System.out.println("当前页数:" + iPage.getCurrent());
//        System.out.println("每页的数量:" + iPage.getSize());
//        System.out.println(page.hasPrevious());
//        System.out.println(page.hasNext());
//        System.out.println("总页数:" + page.getPages());
//        System.out.println("总记录数:" + page.getTotal());
//        System.out.println("当前页数:" + page.getCurrent());
//        System.out.println("每页的数量:" + page.getSize());

        Integer flag = 1052;
        IPage<CofUserAccount> aaa = cofUserAccountService.selectUserPage(page, flag);
        List<CofUserAccount> records1 = aaa.getRecords();

        System.out.println("总页数:" + aaa.getPages());
        System.out.println("总记录数:" + aaa.getTotal());
        records1.forEach(System.out::println);

        //iPage.getRecords().forEach(System.out::println);
        //return records.toString();
        return records1.toString();
    }

}

