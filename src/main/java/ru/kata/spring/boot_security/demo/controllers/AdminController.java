package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class AdminController {

    private final UserService userService;

      public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user")
    public String index(Principal principal, Model model) {
        User user = userService.findBiName(principal.getName());
          model.addAttribute("userInfo", user);
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
          model.addAttribute("allUsers", userService.getAllUsers());
        return "admin/users";
    }

    @GetMapping("admin/{id}")
    public String show(@PathVariable("id") Long id, Model model){
          model.addAttribute("userById", userService.findById(id));
          return "admin/show";
    }

    @GetMapping("admin/new")
    public String newUser(@ModelAttribute("newUser") User user){
          return "admin/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("redirectAll") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("admin/{id}/update")
    public String update(Model model, @PathVariable("id") Long id) {
        model.addAttribute("updateUser", userService.findById(id));
        return "admin/edit";
    }

    @PatchMapping("admin/{id}")
    public String updated(@ModelAttribute("redirectAll") User user, @PathVariable("id") Long id) {
        userService.update(user, id);
        return "redirect:/admin";
    }

    @DeleteMapping("admin/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/admin";
    }
}