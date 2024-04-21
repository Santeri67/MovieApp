package com.harjoitustyo.movieapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harjoitustyo.movieapp.domain.Review;
import com.harjoitustyo.movieapp.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    // Riippuvuuksien injektoinnit ja konstruktori

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        // Luo uusi arvostelu
        Review savedReview = reviewService.addReview(review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<List<Review>> getReviewsForMovie(@PathVariable Long movieId) {
        // Hae arvostelut tietylle elokuvalle
        List<Review> reviews = reviewService.getReviewsForMovie(movieId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // Muut tarvittavat käsittelijämetodit
}