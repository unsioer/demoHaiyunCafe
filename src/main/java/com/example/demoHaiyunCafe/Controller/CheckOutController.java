package com.example.demoHaiyunCafe.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CheckOutController {
    @RequestMapping("/checkout")
    public ModelAndView checkout(){
        return new ModelAndView("checkout");
    }
}