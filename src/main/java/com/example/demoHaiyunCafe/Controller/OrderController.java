package com.example.demoHaiyunCafe.Controller;

import com.example.demoHaiyunCafe.Bean.Cart;
import com.example.demoHaiyunCafe.Bean.Item;
import com.example.demoHaiyunCafe.Bean.Order;
import com.example.demoHaiyunCafe.Service.CartServiceImpl;
import com.example.demoHaiyunCafe.Service.ItemServiceImpl;
import com.example.demoHaiyunCafe.Service.OrderServiceImpl;
import com.example.demoHaiyunCafe.Service.UserService;
import com.example.demoHaiyunCafe.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    CartServiceImpl cartService;

    @Autowired
    ItemServiceImpl itemService;

    @Autowired
    UserService userService;

    @PostMapping("/orderSubmit")
    public ModelAndView orderSubmit(HttpSession session,Model model){
        Integer uid =Integer.parseInt(session.getAttribute("userId").toString());
        List<Cart>  cartList = cartService.findAllByUid(uid);

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Cart cart:cartList){
            Date date = new Date(System.currentTimeMillis());
            Order order = new Order(cart.getUid(),cart.getIid(),cart.getItemName(),cart.getPrice(),cart.getNum()
            ,"未支付",formatter.format(date),userService.findById((long)uid).getAddress());
            orderService.saveOrUpdateOrder(order);

            Item item = itemService.findById(cart.getIid());
            if(item.getNumber()-cart.getNum()>=0){
                item.setNumber(item.getNumber()-cart.getNum());
                itemService.saveOrUpdateItem(item);
                model.addAttribute("isSuccess","提交成功");
            }
            else
                model.addAttribute("isSuccess","库存不足");
        }
        cartService.deleteAllByUid(uid);

        Integer num =0;
        Integer totalPrice =0;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        model.addAttribute("totalPrice","￥"+decimalFormat.format(totalPrice));
        model.addAttribute("num",num);

        return new ModelAndView("checkout","orderSubmitModel",model);
    }

    @GetMapping("/orderManage_{pageCurrent}_{pageSize}_{pageCount}")
    public ModelAndView list(Order order, @PathVariable(required = false) Integer pageCurrent,
                             @PathVariable(required = false) Integer pageSize,
                             @PathVariable(required = false) Integer pageCount,
                             Model model) {

        List<Order> temp = orderService.findAll();

        List<Order>  orderList = new ArrayList<>();
        List<Order> orderListAddress;
        List<Order> orderListUid;
        if(order.getUserAddress()!=null&&!order.getUserAddress().equals("")){
            orderListAddress = orderService.findAllByUserAddress(order.getUserAddress());
        }
        else
            orderListAddress = temp;
        if(order.getUid()!=null){
            orderListUid = orderService.findAllByUid(order.getUid());
        }
        else
            orderListUid = temp;

        orderList = orderListAddress.stream()
                .filter(t->orderListUid.contains(t))
                .collect(Collectors.toList());
        model.addAttribute("orderList",orderList);
        String pageHTML = PageUtil.getPageContent("orderManage_{pageCurrent}_{pageSize}_{pageCount}?title=" + order.getItemname() + "&Price", pageCurrent, pageSize, pageCount);
        model.addAttribute("pageHTML", pageHTML);
        model.addAttribute("order", order);
        return new ModelAndView("order/orderManage","orderModel",model );
    }
}
