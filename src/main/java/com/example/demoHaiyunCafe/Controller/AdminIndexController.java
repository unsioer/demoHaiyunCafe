package com.example.demoHaiyunCafe.Controller;


import com.example.demoHaiyunCafe.Bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
public class AdminIndexController {

    @RequestMapping("/dashboard")
    public static String Index(User user, Map<String, Object> map, HttpSession session) {
        return "dashboard";
    }
}