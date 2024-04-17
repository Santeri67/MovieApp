package com.harjoitustyo.movieapp.service;

import java.util.List;

import com.harjoitustyo.movieapp.domain.Movie;

public interface MovieService {
    Movie saveMovie(Movie movie);
    List<Movie> getAllMovies();
    void deleteMovieById(Long movieId);
    // Other service methods...
}
