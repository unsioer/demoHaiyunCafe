package com.example.demoHaiyunCafe.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class menuController {
    @RequestMapping("/menuManage")
    public String menuManage(){
        return "/Common/menuManage";
    }
}
