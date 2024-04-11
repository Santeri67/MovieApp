package com.harjoitustyo.movieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harjoitustyo.movieapp.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); // Lisää metodi käyttäjänimen perusteella etsimiseen
}
