package com.example.demoHaiyunCafe.Controller;

import com.example.demoHaiyunCafe.Bean.Result;
import com.example.demoHaiyunCafe.Bean.User;
import com.example.demoHaiyunCafe.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
