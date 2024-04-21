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
import org.springframework.web.bind.annotation.PutMapping;
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


    @PostMapping(path = "/addMovie", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> createMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieService.saveMovie(movie);
        Map<String, Object> response = new HashMap<>();
        response.put("id", savedMovie.getId());
        response.put("title", savedMovie.getTitle());
        response.put("genre", savedMovie.getGenre());
        response.put("releaseDate", savedMovie.getReleaseDate());
        response.put("director", savedMovie.getDirector());
        response.put("duration", savedMovie.getDuration());
        response.put("description", savedMovie.getDescription());

        // ... Add other movie details as needed
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{movieId}")
    @ResponseBody
    public ResponseEntity<?> getMovieById(@PathVariable Long movieId) {
        try {
            Movie movie = movieService.findById(movieId)
                .orElseThrow(() -> new Exception("Movie not found for this id :: " + movieId));
            return ResponseEntity.ok(movie);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found for this id :: " + movieId);
        }
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
}

@PutMapping("/{movieId}")
public ResponseEntity<?> updateMovie(@PathVariable Long movieId, @RequestBody Movie movieDetails) {
    try {
        Movie movie = movieService.findById(movieId)
            .orElseThrow(() -> new Exception("Movie not found for this id :: " + movieId));
        
        // Update the movie details
        movie.setTitle(movieDetails.getTitle());
        movie.setGenre(movieDetails.getGenre());
        movie.setReleaseDate(movieDetails.getReleaseDate());
        movie.setDirector(movieDetails.getDirector());
        movie.setDuration(movieDetails.getDuration());
        movie.setDescription(movieDetails.getDescription());

        Movie updatedMovie = movieService.saveMovie(movie); // Save the updated movie
        return ResponseEntity.ok(updatedMovie);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error updating movie: " + e.getMessage());
    }
}
    
}