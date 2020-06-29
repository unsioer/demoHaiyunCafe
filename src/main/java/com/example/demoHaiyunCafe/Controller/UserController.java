package com.example.demoHaiyunCafe.Controller;


import com.example.demoHaiyunCafe.Bean.Result;
import com.example.demoHaiyunCafe.Bean.User;
import com.example.demoHaiyunCafe.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     * @param user 参数封装
     * @return Result
     */
    @PostMapping("/regist")
    public Result regist(User user){
        return userService.regist(user);
    }

    /**
     * 登录
     * @param user 参数封装
     * @return Result
     */
    @PostMapping("/login")
    public Result login(User user){
        return userService.login(user);
    }
}