package com.harjoitustyo.movieapp.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harjoitustyo.movieapp.domain.Movie;
import com.harjoitustyo.movieapp.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * Saves a movie to the repository.
     * @param movie the movie to save
     * @return the saved movie
     * @throws IllegalArgumentException if the movie or its title is null or empty
     */
    @Override
    public Movie saveMovie(Movie movie) {
        if (movie == null || movie.getTitle() == null || movie.getTitle().isEmpty()) {
            logger.error("Attempted to save an invalid movie: {}", movie);
            throw new IllegalArgumentException("Movie and its title must not be null or empty");
        }
        return movieRepository.save(movie);
    }

    /**
     * Retrieves all movies from the repository.
     * @return a list of movies
     */
    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    @Override
public Optional<Movie> findById(Long id) {
    return movieRepository.findById(id);
}


    /**
     * Deletes a movie by its ID if it exists.
     * @param movieId the ID of the movie to delete
     * @throws IllegalArgumentException if the movie does not exist
     */
    @Override
    public void deleteMovieById(Long movieId) {
        movieRepository.findById(movieId).ifPresentOrElse(
            movie -> {
                movieRepository.delete(movie);
                logger.info("Deleted movie with ID: {}", movieId);
            },
            () -> {
                logger.error("Attempted to delete a movie that does not exist with ID: {}", movieId);
                throw new IllegalArgumentException("Movie with ID " + movieId + " does not exist");
            }
        );
    }

    // Other methods...
}
