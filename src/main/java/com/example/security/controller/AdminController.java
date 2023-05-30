package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.security.model.Role;
import com.example.security.model.User;
import com.example.security.service.RoleService;
import com.example.security.service.RoleServiceImpl;
import com.example.security.service.UserService;
import com.example.security.service.UserServiceImpl;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("titleTable", "All users:");
        return "admin";
    }

    @GetMapping("/{id}")
    public String showUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }

    @GetMapping("/addUser")
    public String addNewUser(Model model, @ModelAttribute("user") User user) {
        List<Role> roles = roleService.getUniqAllRoles();
        model.addAttribute("rolesAdd", roles);
        return "newUser";
    }

    @PostMapping
    public String addCreateNewUser(@ModelAttribute("user") User user) {
        userService.createNewUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/editUser")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUser(id));
        List<Role> roles = roleService.getUniqAllRoles();
        model.addAttribute("rolesAdd", roles);
        return "edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
