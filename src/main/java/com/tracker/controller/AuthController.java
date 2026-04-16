package com.tracker.controller;

import com.tracker.model.Role;
import com.tracker.model.User;
import com.tracker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        
        if (userService.register(user, Role.USER)) {
            return "redirect:/login?registered=true";
        } else {
            return "redirect:/register?error=email-exists";
        }
    }
}
