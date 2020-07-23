package com.example.demoHaiyunCafe.controller;


import com.example.demoHaiyunCafe.domain.Cart;
import com.example.demoHaiyunCafe.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.List;

@Controller
public class CheckOutController {


    @Autowired
    private CartServiceImpl cartService;


    @GetMapping("/checkout")
    public ModelAndView userIndex(HttpSession session, Model model) {
        //购物车
        Integer uid = Integer.parseInt(session.getAttribute("userId").toString());
        System.out.println(uid);
        int totalPrice = 0;
        int num = 0;
        List<Cart> cartList = cartService.findAllByUid(uid);
        for (Cart c : cartList) {
            totalPrice += c.getNum() * c.getPrice();
            num += c.getNum();
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        model.addAttribute("totalPrice", "￥" + decimalFormat.format(totalPrice));
        model.addAttribute("num", num);

        model.addAttribute("cartList", cartService.findAllByUid(uid));

        return new ModelAndView("checkout");
    }

    @ResponseBody
    @PostMapping("/cartEditState")
    public String deleteCart(Integer id) {
        cartService.deleteById(id);
        return "success";
    }
}