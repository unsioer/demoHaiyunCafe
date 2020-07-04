package com.example.demoHaiyunCafe.Controller;

import com.example.demoHaiyunCafe.Bean.User;
import com.example.demoHaiyunCafe.Bean.Result;
import com.example.demoHaiyunCafe.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/login")
    public String login()
    {
        return "login";
    }


    @PostMapping(value = "/login")
    public String login(User user, Map<String, Object> map, HttpSession session) {
        Result result = userService.login(user);

        if (result.isSuccess()) {
            session.setAttribute("loginUser", user.getUsername());
            System.out.println(user.getAuthority());
            session.setAttribute("user",user);
            if (user.getAuthority().equals("administrator"))
            {
                return "redirect:/dashboard";
            }
            else
                return "redirect:/index?userid="+user.getId();
        } else {
            map.put("msg", result.getMsg());
            return "login";
        }

    }
}
