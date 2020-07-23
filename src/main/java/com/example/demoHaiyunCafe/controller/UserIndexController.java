package com.example.demoHaiyunCafe.controller;

import com.example.demoHaiyunCafe.domain.Cart;
import com.example.demoHaiyunCafe.domain.Item;
import com.example.demoHaiyunCafe.service.CartServiceImpl;
import com.example.demoHaiyunCafe.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserIndexController {
    @Autowired
    private ItemServiceImpl itemService;

    @Autowired
    private CartServiceImpl cartService;

    @GetMapping("/index")
    public ModelAndView userIndex(Item item, HttpSession session, Model model) {
        //购物车
        Integer uid = Integer.parseInt(session.getAttribute("userId").toString());
        System.out.println(uid);
        int totalPrice = 0;
        int num = 0;
        List<Cart> cartList = cartService.findAllByUid(uid);
        for (Cart c : cartList) {
            totalPrice += c.getNum() * (itemService.findById(c.getIid()).getPrice());
            num += c.getNum();
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        model.addAttribute("totalPrice", "￥" + decimalFormat.format(totalPrice));
        model.addAttribute("num", num);

        //查询显示
        List<String> itemTypeList = new ArrayList<String>();
        itemTypeList.add("饮料");
        itemTypeList.add("小吃");
        itemTypeList.add("主食");
        model.addAttribute("itemTypeList", itemTypeList);
        model.addAttribute("item", item);
        //菜单显示
        List<Item> temp = itemService.findAll();

        List<Item> itemList;
        List<Item> itemListName;
        List<Item> itemListType;

        if (item.getItemname() != null && !item.getItemname().equals("")) {
            itemListName = (itemService.findAllByItemname(item.getItemname()));
        } else
            itemListName = temp;
        if (item.getType() != null && !item.getType().equals("0")) {
            itemListType = itemService.findAllByType(item.getType());
        } else
            itemListType = temp;

        itemList = itemListName.stream()
                .filter(t -> itemListType.contains(t))
                .collect(Collectors.toList());

        model.addAttribute("itemList", itemList);
        return new ModelAndView("index");
    }
}   