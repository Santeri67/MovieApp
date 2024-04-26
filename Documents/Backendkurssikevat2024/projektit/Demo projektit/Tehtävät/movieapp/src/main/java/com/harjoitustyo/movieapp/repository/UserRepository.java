package com.harjoitustyo.movieapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harjoitustyo.movieapp.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
