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
    public String login(User user, String identify ,Map<String, Object> map, HttpSession session) {
        Result result = userService.login(user);

        if (result.isSuccess() && identify.equals("administrator")&& user.getAuthority().equals("administrator")) {
            session.setAttribute("loginUser", user.getUsername());
            session.setAttribute("userId",user.getId());
            session.setAttribute("user",user);
            return "redirect:/dashboard";
        }
        else if(result.isSuccess()&& identify.equals("user") && user.getAuthority().equals("user")){
            session.setAttribute("loginUser", user.getUsername());
            session.setAttribute("userId",user.getId());
            session.setAttribute("user",user);
            return "redirect:/index";
        }
        else {
            map.put("msg", "用户名或密码错误");
            return "login";
        }

    }
}
