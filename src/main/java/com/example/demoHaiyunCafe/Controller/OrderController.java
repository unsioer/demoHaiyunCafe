package com.example.demoHaiyunCafe.Controller;

import com.example.demoHaiyunCafe.Bean.Cart;
import com.example.demoHaiyunCafe.Bean.Item;
import com.example.demoHaiyunCafe.Bean.Order;
import com.example.demoHaiyunCafe.Service.CartServiceImpl;
import com.example.demoHaiyunCafe.Service.ItemServiceImpl;
import com.example.demoHaiyunCafe.Service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    CartServiceImpl cartService;

    @Autowired
    ItemServiceImpl itemService;

    @PostMapping("/orderSubmit")
    public ModelAndView orderSubmit(HttpSession session,Model model){
        Integer uid =Integer.parseInt(session.getAttribute("userId").toString());
        List<Cart>  cartList = cartService.findAllByUid(uid);

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Cart cart:cartList){
            Date date = new Date(System.currentTimeMillis());
            Order order = new Order(cart.getUid(),cart.getIid(),cart.getItemName(),cart.getPrice(),cart.getNum()
            ,"未支付",formatter.format(date));
            orderService.saveOrUpdateOrder(order);

            Item item = itemService.findById(cart.getIid());
            if(item.getNumber()-cart.getNum()>=0){
                item.setNumber(item.getNumber()-cart.getNum());
                itemService.saveOrUpdateItem(item);
                model.addAttribute("isSuccess",true);
            }
            else
                model.addAttribute("isSuccess",false);
        }
        cartService.deleteAllByUid(uid);

        Integer num =0;
        Integer totalPrice =0;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        model.addAttribute("totalPrice","￥"+decimalFormat.format(totalPrice));
        model.addAttribute("num",num);

        return new ModelAndView("checkout","orderSubmitModel",model);
    }
}
