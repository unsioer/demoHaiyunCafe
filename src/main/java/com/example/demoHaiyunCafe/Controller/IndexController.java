package com.example.demoHaiyunCafe.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import com.example.demoHaiyunCafe.Methods.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

    private static void setModel(Model model) {
        model.addAttribute("message","最近消息");
        //model.addAttribute("tasks",loadTaskData());
    }
    @GetMapping("/index")
    public static String Index(Model model) {
        CommonMethods.setCommonModel(model);
        setModel(model);
        return "index";
    }
}