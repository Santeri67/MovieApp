package com.harjoitustyo.movieapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.harjoitustyo.movieapp.domain.Movie;
import com.harjoitustyo.movieapp.repository.MovieRepository;

@Controller
public class MainController {

    private final MovieRepository movieRepository;

    public MainController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/main")
public String showMainPage(Model model, Authentication authentication) {
    if (authentication != null && authentication.isAuthenticated()) {
        // 'getName()' returns the username for the current user
        String name = authentication.getName();
        model.addAttribute("name", name);
    }
    model.addAttribute("movie", new Movie());
    model.addAttribute("movies", movieRepository.findAll());
    return "main";
}
}
