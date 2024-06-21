package in.astro.controller;

import in.astro.entity.User;
import in.astro.helper.Message;
import in.astro.helper.MessageType;
import in.astro.model.UserForm;
import in.astro.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PageController {
    @Autowired
    private UserService userService;


    @RequestMapping("/home")
    public String home(Model model){
        model.addAttribute("title", "Home - Blogging App");
        model.addAttribute("name", "Astro");
        model.addAttribute("currentYear", "2021");
        model.addAttribute("description", "Welcome to the Blogging App");
        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model){
        model.addAttribute("title", "About - Blogging App");
        return "about";
    }

    @RequestMapping("/contact")
    public String contact(Model model){
        model.addAttribute("title", "Contact - Blogging App");
        return "contact";
    }

    @GetMapping("/register")
    public String register(Model model){
        UserForm userForm = new UserForm();
        model.addAttribute("user",userForm);
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/home";
    }

    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("user") UserForm user, BindingResult result, HttpSession session){
        System.out.println(user);
        if (result.hasErrors()){
            return "register";
        }
        User user1 = userService.saveUser(user);
        if (user1 != null){
            Message message = Message.builder().content("User registered successfully").type(MessageType.GREEN).build();
            session.setAttribute("message", message);
            return "redirect:/login";
        }else {
            Message message = Message.builder().content("User already exists").type(MessageType.RED).build();
            session.setAttribute("message", message);
            return "redirect:/register";
        }
    }
}
