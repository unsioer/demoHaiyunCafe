package com.example.demoHaiyunCafe.Controller;


import com.example.demoHaiyunCafe.Bean.User;
import com.example.demoHaiyunCafe.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/userManage")
    public ModelAndView list(User user, @PathVariable(required = false) Integer pageCurrent,
                             @PathVariable(required = false) Integer pageSize,
                             @PathVariable(required = false) Integer pageCount,
                             Model model) {

        List<User> userList = userService.findAll();

        model.addAttribute("userList",userList);

        return new ModelAndView("user/userManage","userModel",model );
    }

}
