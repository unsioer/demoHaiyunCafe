package com.example.demoHaiyunCafe.Controller;

import com.example.demoHaiyunCafe.Bean.Cart;
import com.example.demoHaiyunCafe.Bean.User;
import com.example.demoHaiyunCafe.Service.CartServiceImpl;
import com.example.demoHaiyunCafe.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.List;

@Controller
public class UserProfileController {
    @Autowired
    private UserService userService;

    @Autowired
    private CartServiceImpl cartService;
    @GetMapping("/userProfileEdit")
    public ModelAndView userEditGet(Model model, User user, HttpSession session, HttpServletRequest request){
        //购物车
        Integer uid =Integer.parseInt(session.getAttribute("userId").toString());
        System.out.println(uid);
        int totalPrice = 0;
        int num = 0;
        List<Cart> cartList = cartService.findAllByUid(uid);
        for(Cart c : cartList){
            totalPrice+= c.getNum()*c.getPrice();
            num+=c.getNum();
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        model.addAttribute("totalPrice","￥"+decimalFormat.format(totalPrice));
        model.addAttribute("num",num);

        user=(User)request.getSession().getAttribute("user");
        if(user.getId()!=null){
            User user1 = userService.findById(user.getId());
            if(user1!=null){

            }
            model.addAttribute("user",user1);
        }
        return new ModelAndView("user/userProfileEdit","userEditModel",model );
    }

    @PostMapping("/userProfileEdit")
    public ModelAndView userEditPost(Model model,User user){
        System.out.println(user.getId());
        userService.saveOrUpdateUser(user);
        return new ModelAndView("user/userProfileEdit","userEditModel",model );
    }
}
