package com.example.demoHaiyunCafe.controller;

import com.example.demoHaiyunCafe.domain.User;
import com.example.demoHaiyunCafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LogoutController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/logout")
    public String loginGet(User user, Map<String, Object> map, HttpSession session)
    {
        session.invalidate();
        return "/login";
    }
}
