package com.harjoitustyo.movieapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.harjoitustyo.movieapp.domain.User;
import com.harjoitustyo.movieapp.service.UserService;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
public String showRegistrationForm(Model model) {
    model.addAttribute("user", new User());
    return "register";
}


@PostMapping("/register")
public String registerUserAccount(User user, RedirectAttributes redirectAttributes) {
    try {
        userService.registerNewUserAccount(user);
        return "redirect:/login";
    } catch (IllegalArgumentException e) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return "redirect:/register";
    }
}}
