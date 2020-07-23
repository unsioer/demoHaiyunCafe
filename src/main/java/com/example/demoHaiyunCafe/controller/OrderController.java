package com.example.demoHaiyunCafe.controller;

import com.example.demoHaiyunCafe.domain.Cart;
import com.example.demoHaiyunCafe.domain.Item;
import com.example.demoHaiyunCafe.domain.Order;
import com.example.demoHaiyunCafe.service.CartServiceImpl;
import com.example.demoHaiyunCafe.service.ItemServiceImpl;
import com.example.demoHaiyunCafe.service.OrderServiceImpl;
import com.example.demoHaiyunCafe.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ModelAndView orderSubmit(HttpSession session, Model model) {
        Integer uid = Integer.parseInt(session.getAttribute("userId").toString());
        List<Cart> cartList = cartService.findAllByUid(uid);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Cart cart : cartList) {
            Date date = new Date(System.currentTimeMillis());
            Order order = new Order(cart.getUid(), cart.getIid(), cart.getItemName(), cart.getPrice(), cart.getNum()
                    , "未支付", formatter.format(date), userService.findById((long) uid).getAddress());
            orderService.saveOrUpdateOrder(order);

            Item item = itemService.findById(cart.getIid());
            if (item.getNumber() - cart.getNum() >= 0) {
                item.setNumber(item.getNumber() - cart.getNum());
                itemService.saveOrUpdateItem(item);
                model.addAttribute("isSuccess", "提交成功");
            } else
                model.addAttribute("isSuccess", "库存不足");
        }
        cartService.deleteAllByUid(uid);

        Integer num = 0;
        Integer totalPrice = 0;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        model.addAttribute("totalPrice", "￥" + decimalFormat.format(totalPrice));
        model.addAttribute("num", num);

        return new ModelAndView("checkout", "orderSubmitModel", model);
    }

    @GetMapping("/orderManage")
    public ModelAndView list(Order order,
                             Integer pageNum,
                             Model model) {

//        PageHelper.startPage(pageNum,pageSize);

//        System.out.println(order.getUserAddress());
//        System.out.println(userAddress);

        List<Order> temp = orderService.findAll();

        List<Order> orderList = new ArrayList<>();
        List<Order> orderListAddress;
        List<Order> orderListUid;
        if (order.getUserAddress() != null && !order.getUserAddress().equals("")) {
            orderListAddress = orderService.findAllByUserAddress(order.getUserAddress());
        } else
            orderListAddress = temp;
        if (order.getUid() != null) {
            orderListUid = orderService.findAllByUid(order.getUid());
        } else
            orderListUid = temp;

        orderList = orderListAddress.stream()
                .filter(t -> orderListUid.contains(t))
                .collect(Collectors.toList());

        if (pageNum == null) {
            pageNum = 1;
        }
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(pageNum - 1, 20, sort);

        Page<Order> page = listConvertToPage(orderList, pageable);
//        Page<Order> pageInfo = orderService.findAll(pageable);
        model.addAttribute("pageInfo", page);

        model.addAttribute("order", order);
        return new ModelAndView("order/orderManage", "orderModel", model);
    }

    public <T> Page<T> listConvertToPage(List<T> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
        return new PageImpl<T>(list.subList(start, end), pageable, list.size());
    }

}
