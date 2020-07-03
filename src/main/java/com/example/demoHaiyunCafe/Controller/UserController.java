package com.example.demoHaiyunCafe.Controller;


import com.example.demoHaiyunCafe.Bean.Item;
import com.example.demoHaiyunCafe.Bean.User;
import com.example.demoHaiyunCafe.Service.UserService;
import com.example.demoHaiyunCafe.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/userManage_{pageCurrent}_{pageSize}_{pageCount}")
    public ModelAndView list(User user, @PathVariable(required = false) Integer pageCurrent,
                             @PathVariable(required = false) Integer pageSize,
                             @PathVariable(required = false) Integer pageCount,
                             Model model) {

        List<User> userList = userService.findAll();

        model.addAttribute("userList",userList);
        String pageHTML = PageUtil.getPageContent("itemManage_{pageCurrent}_{pageSize}_{pageCount}", pageCurrent, pageSize, pageCount);
        model.addAttribute("pageHTML", pageHTML);

        return new ModelAndView("user/userManage","userModel",model );
    }

    @GetMapping("/userEdit")
    public ModelAndView userEditGet(Model model, User user){
        if(user.getId()!=null){
            User user1 = userService.findById(user.getId());
            if(user1!=null){

            }
            model.addAttribute("user",user1);
        }
        return new ModelAndView("user/userEdit","userEditModel",model );
    }

    @PostMapping("/userEdit")
    public ModelAndView userEditPost(Model model,User user){
        userService.saveOrUpdateUser(user);
        return new ModelAndView("user/userEdit","userEditModel",model );
    }

    @ResponseBody
    @PostMapping("/userEditState")
    public String userDelete(Long id){
        userService.deleteById(id);
        return "success";
    }

}
