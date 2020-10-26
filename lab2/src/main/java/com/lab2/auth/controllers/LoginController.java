package com.lab2.auth.controllers;

import com.lab2.auth.models.Login;
import com.lab2.auth.models.User;
import com.lab2.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    public UserService userService;

    @PostMapping(value="/loginProcess")
    public String login(@ModelAttribute("login") Login login, BindingResult bindingResult, ModelMap model){
        User user = userService.validateUser(login);

        boolean isValidUser = false;

        if (user != null && user.getUsername().equals(login.getUsername())
            && user.getPassword().equals(login.getPassword())){
            isValidUser = true;
            model.addAttribute("username", user.getUsername());
        }

        return isValidUser ? "welcome" : "login";
    }

}
