package com.example.demoHaiyunCafe.Controller;

import com.example.demoHaiyunCafe.Bean.Result;
import com.example.demoHaiyunCafe.Bean.User;
import com.example.demoHaiyunCafe.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserProfileController {
    @Autowired
    private UserService userService;

    @GetMapping("/userProfileEdit")
    public ModelAndView userEditGet(Model model, User user, HttpServletRequest request){
        user=(User)request.getSession().getAttribute("user");
        if(user.getId()!=null){
            User user1 = userService.findById(user.getId());
            if(user1!=null){

            }
            model.addAttribute("user",user1);
        }
        return new ModelAndView("user/userEdit","userEditModel",model );
    }

    @PostMapping("/userProfileEdit")
    public ModelAndView userEditPost(Model model,User user){
        userService.saveOrUpdateUser(user);
        return new ModelAndView("user/userEdit","userEditModel",model );
    }
}
