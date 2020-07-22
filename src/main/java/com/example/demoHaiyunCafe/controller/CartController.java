package com.example.demoHaiyunCafe.controller;


import com.example.demoHaiyunCafe.domain.Cart;
import com.example.demoHaiyunCafe.domain.Item;
import com.example.demoHaiyunCafe.service.CartServiceImpl;
import com.example.demoHaiyunCafe.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {

    @Autowired
    CartServiceImpl cartService;

    @Autowired
    ItemServiceImpl itemService;

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
            Item item = itemService.findById(iid);
            Cart cart1 = new Cart(iid,uid,1,item.getItemname(),item.getPrice(),item.getPicturepath());
            cartService.saveOrUpdateCart(cart1);
        }
        return "success";
    }

    @ResponseBody
    @PostMapping("/setCartItemNum{iid},{num}")
    public String setCartItemNum(@PathVariable(required = true)Integer iid, @PathVariable(required = true)Integer num, HttpSession session){
        Integer uid =Integer.parseInt(session.getAttribute("userId").toString());
        Cart cart = cartService.findByUidAndIid(uid,iid);
        System.out.println(iid+','+num);
        if(cart!=null){
            cart.setNum(num);
            cartService.saveOrUpdateCart(cart);
        }
        else{

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
