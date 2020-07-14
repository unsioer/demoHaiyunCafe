package com.example.demoHaiyunCafe.Controller;


import com.example.demoHaiyunCafe.Bean.Cart;
import com.example.demoHaiyunCafe.Bean.Item;
import com.example.demoHaiyunCafe.Service.CartServiceImpl;
import com.example.demoHaiyunCafe.Service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

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
    @PostMapping("/emptyCart")
    public String emptyCart(HttpSession session){
        Integer uid =Integer.parseInt(session.getAttribute("userId").toString());
        cartService.deleteAllByUid(uid);
        System.out.println("emptyCart"+uid);
        return "success";
    }
}
