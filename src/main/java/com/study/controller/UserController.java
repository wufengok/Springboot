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

import java.util.ArrayList;
import java.util.Collections;
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
@RequestMapping("")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("/select")
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

    @GetMapping("/insert")
    @ResponseBody
    public String insert() {

        List list = new ArrayList();
        User user1 = new User();
        user1.setName("wufeng1");
        user1.setAge(11);

        User user2 = new User();
        user2.setName("wufeng2");
        user2.setAge(11);

        User user3 = new User();
        user3.setName("wufeng3");
        user3.setAge(11);

        User user4 = new User();
        user4.setName("wufeng4");
        user4.setAge(11);

        Collections.addAll(list,user1,user2,user3,user4);

        userService.insert(list);
        return "";

    }
}























