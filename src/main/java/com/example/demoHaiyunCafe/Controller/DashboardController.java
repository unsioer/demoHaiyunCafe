package com.example.demoHaiyunCafe.Controller;


import antlr.ASTNULLType;
import com.example.demoHaiyunCafe.Bean.Cart;
import com.example.demoHaiyunCafe.Bean.Item;
import com.example.demoHaiyunCafe.Bean.Order;
import com.example.demoHaiyunCafe.Bean.User;
import com.example.demoHaiyunCafe.Service.ItemService;
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
public class DashboardController {
    @Autowired
    OrderService orderService;

    @Autowired
    ItemService itemService;

    @RequestMapping("/dashboard")
    public ModelAndView Index(Model model, User user, Map<String, Object> map, HttpSession session) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        List<Integer> monthData=orderService.findMonthData(year,month);
        List<Integer> yearData=orderService.findYearData(year);
        List<String> last30DayData=orderService.findLast30DayData();
        List<Item> itemList = itemService.findTop5PopularItems();
        String itemNameString="",itemPopularityString="";
        for(int i=0;i<itemList.size();i++)
        {
            itemNameString+=itemList.get(i).getItemname()+",";
            itemPopularityString+=itemList.get(i).getPopularity()+",";
        }
        System.out.println(itemNameString);
        System.out.println(itemPopularityString);
        model.addAttribute("mOrderNum",monthData.get(0));
        model.addAttribute("mIncome",monthData.get(1));
        model.addAttribute("yOrderNum",yearData.get(0));
        model.addAttribute("yIncome",yearData.get(1));
        model.addAttribute("dayOrderNumStr",last30DayData.get(0));
        model.addAttribute("dayIncomeStr",last30DayData.get(1));
        model.addAttribute("itemNameStr",itemNameString);
        model.addAttribute("itemPopularityStr",itemPopularityString);
        return new ModelAndView("dashboard","dashboardModel",model );
    }
}