package com.example.demoHaiyunCafe.Controller;


import com.example.demoHaiyunCafe.Bean.Cart;
import com.example.demoHaiyunCafe.Service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    CartServiceImpl cartService;

    @ResponseBody
    @PostMapping("/addToCart{iid}")
    public String addToCart(@PathVariable(required = false)Integer iid, HttpSession session){
        Integer uid =Integer.parseInt(session.getAttribute("userId").toString());
        Cart cart = cartService.findByUidAndIid(uid,iid);
        System.out.println(iid);
        if(cart!=null){
            cart.setNum(cart.getNum()+1);
            cartService.saveOrUpdateCart(cart);
        }
        else {
            Cart cart1 = new Cart(iid,uid,1);
            cartService.saveOrUpdateCart(cart1);
        }
        return "success";
    }

    @ResponseBody
    @PostMapping("/emptyCart")
    public String emptyCart(HttpSession session){
        Integer uid =Integer.parseInt(session.getAttribute("userId").toString());
        cartService.deleteAllByUid(uid);
        System.out.println("emptyCart"+uid);
        return "success";
    }
}
