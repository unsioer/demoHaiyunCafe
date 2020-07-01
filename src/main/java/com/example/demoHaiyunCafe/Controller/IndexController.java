package com.example.demoHaiyunCafe.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import com.example.demoHaiyunCafe.Methods.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    private static void setModel(Model model) {
        model.addAttribute("message","最近消息");
        //model.addAttribute("tasks",loadTaskData());
    }
    @RequestMapping("/dashboard")
    public static String Index() {
        //CommonMethods.setCommonModel(model);
        //setModel(model);
        return "dashboard";
    }
}