package com.example.demoHaiyunCafe.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuController {
    @RequestMapping("/menuManage")
    public String menuManage(){
        return "/Common/menuManage";
    }
}
