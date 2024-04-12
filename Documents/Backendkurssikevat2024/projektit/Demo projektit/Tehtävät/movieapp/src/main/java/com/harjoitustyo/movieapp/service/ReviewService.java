package com.harjoitustyo.movieapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harjoitustyo.movieapp.domain.Review;
import com.harjoitustyo.movieapp.repository.ReviewRepository;

@Service
public class ReviewService {
    
    private final ReviewRepository reviewRepository;

    @Autowired // Lisätään konstruktori, joka injektoi ReviewRepositoryn
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // Riippuvuuksien injektoinnit ja konstruktori

    public Review addReview(Review review) {
        // Oletettavasti tehdään tarkistuksia ennen tallennusta
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsForMovie(Long movieId) {
        // Oletus: ReviewRepositoryssa on määritelty metodi findReviewsByMovieId
        return reviewRepository.findByMovieId(movieId);
    }
    
    // Muut tarvittavat metodit
}

