package in.astro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
//    dashboard
    @GetMapping("/dashboard")
    public String dashboard(){
        return "user/dashboard";
    }

//    profile
    @GetMapping("/profile")
    public String profile(){
        return "user/profile";
    }
}
