package com.example.demoHaiyunCafe.controller;

import com.example.demoHaiyunCafe.domain.Result;
import com.example.demoHaiyunCafe.domain.User;
import com.example.demoHaiyunCafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class RegistController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/regist")
    public String login(User user, Map<String,Object> map, HttpSession session)
    {
        Result result= userService.regist(user);
        if(result.isSuccess()){
            session.setAttribute("loginUser",user.getUsername());
            return "redirect:/login.html";
        }
        else{
            map.put("msg",result.getMsg());
            return "regist";
        }
    }
}
