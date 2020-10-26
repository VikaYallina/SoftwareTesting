package com.lab2.auth.controllers;

import com.lab2.auth.models.Login;
import com.lab2.auth.models.User;
import com.lab2.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    public UserService userService;

    @GetMapping("/")
    public String home(Map<String, Object> map){
        return "home";
    }

    @GetMapping("/showLoginPage")
    public String showLoginPage(ModelMap model){
        model.addAttribute(new Login());
        return "login";
    }

    @GetMapping("/showRegistrationPage")
    public String showRegistrationPage(ModelMap model){
        model.addAttribute(new User());
        return "registration";
    }
}
