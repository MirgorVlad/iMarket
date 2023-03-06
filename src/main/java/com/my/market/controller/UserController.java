package com.my.market.controller;

import com.my.market.model.User;
import com.my.market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @GetMapping("/login")
    public String login(Principal principal){
        System.out.println(principal);
        return "login";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model){
        if(!userService.createUser(user)){
            model.addAttribute("error", "User with email " + user.getEmail() + " already exist.");
            return "registration";
        }
        userService.createUser(user);
        return "redirect:/login";
    }
}
