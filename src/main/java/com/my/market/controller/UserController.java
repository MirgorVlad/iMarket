package com.my.market.controller;

import com.my.market.model.User;
import com.my.market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String login(){
        return "login";
    }

    @GetMapping("/user/{id}")
    public String userInfo(@PathVariable(name = "id") int id, Model model){
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "userInfo";
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
