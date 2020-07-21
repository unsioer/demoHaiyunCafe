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
        List<Integer> monthData=orderService.findMonthData(year,month);
        List<String> last30dayData=orderService.findLast30DayData();
        Integer mOrderNum=monthData.get(0);
        Integer mIncome=monthData.get(1);
        model.addAttribute("mIncome",mIncome);
        model.addAttribute("mOrderNum",mOrderNum);
        model.addAttribute("data2",last30dayData.get(0));
        model.addAttribute("data3",last30dayData.get(1));
        return new ModelAndView("dashboard","dashboardModel",model );
    }
}