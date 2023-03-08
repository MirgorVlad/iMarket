package com.my.market.controller;

import com.my.market.model.User;
import com.my.market.model.enums.Role;
import com.my.market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String admin(Model model){
        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);
        return "admin";
    }

    @GetMapping("/admin/user/edit/{id}")
    public String userEdit(@PathVariable(name = "id") int id, Model model){
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", List.of(Role.values()));
        return "userEdit";
    }

    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam(name = "id") int id, @RequestParam Map<String, String> form){
        User user = userService.findUserById(id);
        userService.changeUserRoles(user, form);
        return "redirect:/admin";
    }

    @PostMapping("/admin/ban/{id}")
    public String banUser(@PathVariable(name = "id") int id){
        userService.banUser(id);
        return "redirect:/admin";
    }

}
