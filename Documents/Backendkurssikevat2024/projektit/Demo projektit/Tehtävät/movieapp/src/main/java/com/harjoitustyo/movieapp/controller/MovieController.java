package com.harjoitustyo.movieapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.harjoitustyo.movieapp.domain.Movie;
import com.harjoitustyo.movieapp.repository.MovieRepository;

@Controller // Change this to @Controller
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Change this method to return a redirection String
    @PostMapping
    @ResponseBody // This ensures the response body is JSON
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
    Movie savedMovie = movieRepository.save(movie);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
}

    @PostMapping("/addMovie")
    @ResponseBody // Ensure that this method returns a JSON response
    public ResponseEntity<?> createMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        Map<String, Object> response = new HashMap<>();
        response.put("id", savedMovie.getId());
        response.put("title", savedMovie.getTitle());
        // ... Add other movie details as needed
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<Iterable<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieRepository.findAll());
    }
    
    @GetMapping("/list")
    public String listMovies(Model model) {
    model.addAttribute("movies", movieRepository.findAll());
    return "main"; // Assumes that 'main' is the template where movies are listed
}

}

