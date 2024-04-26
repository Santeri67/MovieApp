package com.harjoitustyo.movieapp.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harjoitustyo.movieapp.domain.Movie;
import com.harjoitustyo.movieapp.domain.Review;
import com.harjoitustyo.movieapp.repository.MovieRepository;
import com.harjoitustyo.movieapp.repository.ReviewRepository;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;
    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, ReviewRepository reviewRepository) {
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        if (movie == null || movie.getTitle() == null || movie.getTitle().isEmpty()) {
            logger.error("Attempted to save an invalid movie: {}", movie);
            throw new IllegalArgumentException("Movie and its title must not be null or empty");
        }
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

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

    @Override
    public double calculateAverageRating(Long movieId) {
        List<Review> reviews = reviewRepository.findByMovieId(movieId);
        if (reviews.isEmpty()) {
            logger.info("No reviews found for movie ID: {}", movieId);
            return 0;
        }
        double total = reviews.stream().mapToDouble(Review::getRating).sum();
        return total / reviews.size();
    }
}
