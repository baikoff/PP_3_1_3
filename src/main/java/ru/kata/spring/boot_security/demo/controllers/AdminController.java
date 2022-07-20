package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
    private UserService service;


    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("users", service.allUsers());
        model.addAttribute("thisUser", service.getUserByName(principal.getName()));
        model.addAttribute("newUser", new User());
        return "/admin/admin_panel";
    }

    @GetMapping("/index/{id}")
    public String show(@PathVariable("id") Long id, Model model, Principal principal) {
        if (!service.isAllowed(id, principal)) {
            id = service.getUserByName(principal.getName()).getId();
            model.addAttribute(service.getUserByName(principal.getName()));
        } else {
            model.addAttribute(service.readUser(id));
        }
        return "/index";
    }

    @GetMapping("/index")
    public void userInfo(Principal principal, Model model) {
        User user = service.getUserByName(principal.getName());
        model.addAttribute(user);
        show(user.getId(), model, principal);
    }

    @GetMapping("/admin/admin_new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "/admin/admin_new";
    }

    @PostMapping("/")
    public String create(@ModelAttribute("user") User user) {
        service.createUser(user);
        return "redirect:/admin/admin_panel";
    }

    @GetMapping("/index/{id}/edit")
    public String updateUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", service.readUser(id));
        return "/admin/admin_update";
    }

    @PatchMapping("/index/update")
    public String update(@ModelAttribute("user") User newUser) {
        service.updateUser(newUser);
        return "redirect:/admin/admin_panel";
    }

    @DeleteMapping("/index/delete")
    public String delete(@ModelAttribute("user") User user) {
        service.deleteUser(user.getId());
        return "redirect:/admin/admin_panel";
    }
}