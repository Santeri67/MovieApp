package com.harjoitustyo.movieapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.harjoitustyo.movieapp.domain.Movie;
import com.harjoitustyo.movieapp.service.MovieService;

@Controller
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieService.saveMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @PostMapping(path = "/addMovie", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> createMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieService.saveMovie(movie);
        Map<String, Object> response = new HashMap<>();
        response.put("id", savedMovie.getId());
        response.put("title", savedMovie.getTitle());
        // ... Add other movie details as needed
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<Iterable<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }
    
    @GetMapping("/list")
    public String listMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "main"; // Assumes that 'main' is the template where movies are listed
    }
    @DeleteMapping("/{movieId}")
public ResponseEntity<?> deleteMovie(@PathVariable Long movieId) {
    try {
        movieService.deleteMovieById(movieId);
        return ResponseEntity.ok().build(); // Send a successful response
    } catch (Exception e) {
        e.printStackTrace(); // Log the exception for debugging
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting movie");
    }
}}
