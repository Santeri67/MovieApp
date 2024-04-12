package com.harjoitustyo.movieapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harjoitustyo.movieapp.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Oletetaan, että Movie-entiteetillä on yksilöllinen id-kenttä ja Review-entiteetissä on viite Movie-entiteettiin.
    List<Review> findByMovieId(Long movieId);
}
