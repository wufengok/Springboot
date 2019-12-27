package com.study.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.entity.User;
import com.study.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wufeng
 * @since 2019-12-27
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("/testtest")
    @ResponseBody
    public String selectMyPage() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age", 1);

        //当前页数+每页记录数
        Page<User> page = new Page<>(1, 3);

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

        IPage<User> aaa = userService.selectUserPage(page, queryWrapper);
        List<User> records1 = aaa.getRecords();

        System.out.println("总页数:" + aaa.getPages());
        System.out.println("总记录数:" + aaa.getTotal());
        records1.forEach(System.out::println);

        return records1.toString();
    }
}

