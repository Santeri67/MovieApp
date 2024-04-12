package com.harjoitustyo.movieapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.harjoitustyo.movieapp.domain.Movie;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/main")
    public String main(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); // Get logged in username
        model.addAttribute("name", name);
        model.addAttribute("movie", new Movie()); // Olettaen, että haluat myös alustaa uuden elokuvan
        return "main"; // Olettaen, että sivusi nimi on main.html
    }
}

