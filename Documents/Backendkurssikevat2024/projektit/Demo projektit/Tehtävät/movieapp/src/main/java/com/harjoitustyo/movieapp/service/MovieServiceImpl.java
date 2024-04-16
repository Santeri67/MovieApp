package com.harjoitustyo.movieapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harjoitustyo.movieapp.domain.Movie;
import com.harjoitustyo.movieapp.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
public Movie saveMovie(Movie movie) {
    if (movie == null || movie.getTitle() == null || movie.getTitle().isEmpty()) {
        throw new IllegalArgumentException("Movie and its title must not be null or empty");
    }
    return movieRepository.save(movie);
}

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // Other methods...
}
