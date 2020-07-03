package com.example.demoHaiyunCafe.Methods;

import org.springframework.ui.Model;

public class CommonMethods {
    public static void setCommonModel(Model model) {
        model.addAttribute("message","Welcome");
        model.addAttribute("week",TimeMethods.getWeek());
        model.addAttribute("weekday",TimeMethods.getChineseWeekday());
    }
}
