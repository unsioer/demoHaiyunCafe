package com.example.demoHaiyunCafe.Controller;

import com.example.demoHaiyunCafe.Bean.Cart;
import com.example.demoHaiyunCafe.Service.CartServiceImpl;
import com.example.demoHaiyunCafe.Service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.List;

@Controller
public class UserIndexController {
    @Autowired
    private ItemServiceImpl itemService;

    @Autowired
    private CartServiceImpl cartService;

    @GetMapping("/index")
    public ModelAndView userIndex(HttpSession session, Model model ){
        Integer uid =Integer.parseInt(session.getAttribute("userId").toString());
        System.out.println(uid);
        int totalPrice = 0;
        int num = 0;
        List<Cart> cartList = cartService.findAllByUid(uid);
        for(Cart c : cartList){
            totalPrice+= c.getNum()*(itemService.findById(c.getIid()).getPrice());
            num+=c.getNum();
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        model.addAttribute("totalPrice","￥"+decimalFormat.format(totalPrice));
        model.addAttribute("num",num);
        return new ModelAndView("index");
    }
}   