package com.example.demoHaiyunCafe.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserIndexController {
    @RequestMapping("/index")
    public ModelAndView userIndex(){
        return new ModelAndView("index");
    }
}