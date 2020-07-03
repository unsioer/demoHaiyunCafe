package com.example.demoHaiyunCafe.Controller;

import com.example.demoHaiyunCafe.Bean.User;
import com.example.demoHaiyunCafe.Bean.Result;
import com.example.demoHaiyunCafe.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public String login(User user, String identify, Map<String,Object> map, HttpSession session)
    {
        if(!identify.equals("admin")) {
            Result result = userService.login(user);
            if (result.isSuccess()) {
                session.setAttribute("loginUser", user.getUsername());
                return "redirect:/dashboard";
            } else {
                map.put("msg", result.getMsg());
                return "login";
            }
        }
        else{
            if(user.getUsername().equals("admin")&&user.getPassword().equals("admin"))
                return "redirect:/dashboard";
            else {
                map.put("msg", "用户名或密码错误");
                return "login";
            }
        }
    }
}
