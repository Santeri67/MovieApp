package com.harjoitustyo.movieapp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.harjoitustyo.movieapp.domain.User;
import com.harjoitustyo.movieapp.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUserAccount(User user) {
        // Tarkista, ettei samalla käyttäjänimellä ole jo luotu tiliä
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already in use");
            // Heitä poikkeus tai käsittele tilanne, jos käyttäjänimi on jo käytössä
        }
        // Hashaa salasana ennen tallennusta
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Tallenna käyttäjä tietokantaan
        return userRepository.save(user);
    }

    // Voit lisätä täällä muita käyttäjään liittyviä palveluita
}