package com.example.demoHaiyunCafe.controller;


import com.example.demoHaiyunCafe.domain.Result;
import com.example.demoHaiyunCafe.domain.User;
import com.example.demoHaiyunCafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/userManage")
    public ModelAndView list(User user, Integer pageNum, Model model) {

        List<User> userList = userService.findAll();

        if (pageNum == null){
            pageNum = 1;
        }
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(pageNum - 1, 20, sort);

        Page<User> page = listConvertToPage(userList,pageable);
        model.addAttribute("pageInfo",page);

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

    @GetMapping("/userAdd")
    public ModelAndView userAddGet(Model model, User user){
        if(user.getId()!=null){
            User user1 = userService.findById(user.getId());
            if(user1!=null){

            }
            model.addAttribute("user",user1);
            //新增之后跳转至用户列表界面
            ModelAndView modelAndView = new ModelAndView("redirect:/userManage");
            return modelAndView;
        }
        return new ModelAndView("user/userAdd","userAddModel",model );
    }


    @PostMapping("/userAdd")
    public ModelAndView userAddPost(Model model,User user){
        // 借用regist
        Result result= userService.regist(user);
        if(!result.isSuccess()) {
            model.addAttribute("msg", result.getMsg());
        }
        else
        {
            //新增之后跳转至用户列表界面
            ModelAndView modelAndView = new ModelAndView("redirect:/userManage_0_0_0");
            return modelAndView;
        }
        return new ModelAndView("user/userAdd","userAddModel",model );
    }


    @ResponseBody
    @PostMapping("/userEditState")
    public String userDelete(Long id){
        userService.deleteById(id);
        return "success";
    }

    public <T> Page<T> listConvertToPage(List<T> list, Pageable pageable) {
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : ( start + pageable.getPageSize());
        return new PageImpl<T>(list.subList(start, end), pageable, list.size());
    }
}
