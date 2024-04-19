package com.harjoitustyo.movieapp.service;

import java.util.List;
import java.util.Optional;

import com.harjoitustyo.movieapp.domain.Movie;

public interface MovieService {
    Movie saveMovie(Movie movie);
    List<Movie> getAllMovies();
    void deleteMovieById(Long movieId);
    Optional<Movie> findById(Long id);
    // Other service methods...
}
