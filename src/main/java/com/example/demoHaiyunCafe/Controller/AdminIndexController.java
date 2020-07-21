package com.example.demoHaiyunCafe.Controller;


import com.example.demoHaiyunCafe.Bean.Cart;
import com.example.demoHaiyunCafe.Bean.Item;
import com.example.demoHaiyunCafe.Bean.Order;
import com.example.demoHaiyunCafe.Bean.User;
import com.example.demoHaiyunCafe.Service.OrderService;
import com.example.demoHaiyunCafe.Service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class AdminIndexController {
    @Autowired
    OrderService orderService;

    @RequestMapping("/dashboard")
    public ModelAndView Index(Model model, User user, Map<String, Object> map, HttpSession session) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        List<Integer> data=orderService.findMonthData(year,month);
        Integer mIncome=data.get(1);
        Integer mOrderNum=data.get(0);
        model.addAttribute("mIncome",mIncome);
        model.addAttribute("mOrderNum",mOrderNum);
        return new ModelAndView("dashboard","dashboardModel",model );
    }
}