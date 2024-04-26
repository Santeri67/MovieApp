package com.harjoitustyo.movieapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harjoitustyo.movieapp.domain.Movie;
import com.harjoitustyo.movieapp.domain.Review;
import com.harjoitustyo.movieapp.domain.User;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByUserAndMovie(User user, Movie movie);
    // In ReviewRepository.java
    List<Review> findByMovieId(Long movieId);

}