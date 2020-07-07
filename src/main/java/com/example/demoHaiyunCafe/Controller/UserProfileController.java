package com.example.demoHaiyunCafe.Controller;

import com.example.demoHaiyunCafe.Bean.User;
import com.example.demoHaiyunCafe.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserProfileController {
    @Autowired
    private UserService userService;

    @GetMapping("/userProfileEdit")
    public ModelAndView userEditGet(Model model, User user, HttpServletRequest request){
        System.out.println(user.getId());
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
