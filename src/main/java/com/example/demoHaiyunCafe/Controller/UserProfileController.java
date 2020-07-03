package com.example.demoHaiyunCafe.Controller;

import com.example.demoHaiyunCafe.Bean.Result;
import com.example.demoHaiyunCafe.Bean.User;
import com.example.demoHaiyunCafe.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserProfileController {
    @Autowired
    private UserService userService;
    @GetMapping("/userProfileEdit")
    //@PostMapping(value = "/userProfileEdit")
    public String userProfileEdit(User user, Map<String,Object> map, HttpSession session) {
        Result result= userService.login(user);
        if(result.isSuccess()){
            session.setAttribute("loginUser",user.getUsername());
            return "redirect:/dashboard";
        }
        else{
            map.put("msg",result.getMsg());
            return "user/userProfileEdit";
        }
        //return "userProfileEdit";
    }
}
