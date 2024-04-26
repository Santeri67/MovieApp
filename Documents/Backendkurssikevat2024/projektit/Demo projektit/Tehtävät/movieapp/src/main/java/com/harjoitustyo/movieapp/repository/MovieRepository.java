package com.harjoitustyo.movieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harjoitustyo.movieapp.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
